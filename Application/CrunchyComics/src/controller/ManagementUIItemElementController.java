package controller;

import broker.ItemBroker;
import broker.TypeBroker;
import domain.Item;
import domain.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.ControllerManager;
import manager.DatabaseManager;
import ui.ManagementUIItemElement;
import utility.Timer;

/**
 * Controls the ManagementUIElement pop-up logic to modify data in the database.
 *
 * @author Vinicius Smith
 */
public class ManagementUIItemElementController implements Initializable {

    @FXML
    private TextField fieldItemName;
    @FXML
    private TextField fieldItemPrice;
    @FXML
    private TextField fieldItemUPC;
    @FXML
    private TextField fieldItemDescription;
    @FXML
    private TextField fieldItemQuantity;
    @FXML
    private Label labelItemID;
    @FXML
    private Label labelError;
    @FXML
    private ComboBox comboItemType;

    private Item item;
    private ManagementUIItemElement element;
    private ManagementController mgntController;

    /**
     * Checks the value of the price if there is invalid input (not a number) on
     * load.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Input check for price.
        fieldItemPrice.setOnKeyTyped(e -> {
            if (e.getCharacter().matches("[^0-9.]")) {
                Timer.getInstance().resetTimer();
                e.consume();
            }
        });

        fieldItemUPC.setOnKeyTyped(e -> {
            if (e.getCharacter().matches("[^0-9]")) {
                Timer.getInstance().resetTimer();
                e.consume();
            }
        });
    }

    /**
     * Loads the data from the database and displays the values onto the scene.
     */
    public void populate() {
        //New Item creation.
        if (element == null) {
            TypeBroker typeBroker = new TypeBroker(DatabaseManager.getInstance());
            List types = typeBroker.getAllTypes();
            comboItemType.setItems(FXCollections.observableArrayList(types));
            if (types.size() > 0) {
                comboItemType.setValue(types.get(0));
            }

            ItemBroker itemBroker = new ItemBroker(DatabaseManager.getInstance(), DatabaseManager.getEntityManager());
            labelItemID.setText((itemBroker.getLastID() + 1) + "");

        } else { //Existing item edit.
            fieldItemName.setText(item.getName());
            fieldItemPrice.setText(String.format("%.2f", item.getPrice()));
            fieldItemUPC.setText(item.getUpc());
            fieldItemDescription.setText(item.getDescription());
            fieldItemQuantity.setText(item.getQuantity() + "");
            labelItemID.setText(item.getItemID() + "");

            TypeBroker typeBroker = new TypeBroker(DatabaseManager.getInstance());

            comboItemType.setItems(FXCollections.observableArrayList(typeBroker.getAllTypes()));
            comboItemType.setValue(item.getItemType().toString());
        }
    }

    /**
     * The button used to validate incorrect user input.
     */
    public void cancelBtnClicked() {
        Timer.getInstance().resetTimer();
        ControllerManager.getInstance().hidePopup();
    }

    /**
     * The button used to validate correct user input.
     */
    public void confirmBtnClicked() {
        Timer.getInstance().resetTimer();
        ItemBroker itemBroker = new ItemBroker(DatabaseManager.getInstance(), DatabaseManager.getEntityManager());

        //Error checking
        if (fieldItemName.getText().isEmpty()) {
            labelError.setVisible(true);
            labelError.setText("Name field can't be empty!");
            fieldItemName.requestFocus();
            return;
        }
        if (fieldItemPrice.getText().isEmpty()) {
            labelError.setVisible(true);
            labelError.setText("Price field can't be empty!");
            fieldItemPrice.requestFocus();
            return;
        }

        item.setName(fieldItemName.getText());
        item.setPrice(Float.parseFloat(fieldItemPrice.getText()));
        item.setUpc(fieldItemUPC.getText());
        item.setDescription(fieldItemDescription.getText());
        item.setQuantity(Integer.parseInt(fieldItemQuantity.getText()));
        item.setItemType(new Type(comboItemType.getValue().toString()));
        //New item creation.
        if (element == null) {
            item.setItemID(Integer.parseInt(labelItemID.getText()));
            itemBroker.insert(item);
            mgntController.populateItems();

        } else { //Existing item edit.
            element.refresh();
            itemBroker.insert(item);
        }

        ControllerManager.getInstance().hidePopup();
    }

    /**
     * Sets the item to the inputted value.
     *
     * @param item new item.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Sets the ManagementUIItemElement to the parameter pop-up.
     *
     * @param element new element.
     */
    public void setManagementUIItemElement(ManagementUIItemElement element) {
        this.element = element;
    }

    /**
     * Controls which ManagementController is needed.
     *
     * @param mgntController new management controller.
     */
    public void setManagementController(ManagementController mgntController) {
        this.mgntController = mgntController;
    }
}
