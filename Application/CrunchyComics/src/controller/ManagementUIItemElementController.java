package controller;

import broker.ItemBroker;
import broker.TypeBroker;
import domain.Item;
import domain.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.ControllerManager;
import manager.DatabaseManager;
import ui.ManagementUIItemElement;

/**
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
    private ComboBox comboItemType;

    private Item item;
    private ManagementUIItemElement element;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populate() {
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

    public void cancelBtnClicked() {
        ControllerManager.getInstance().hidePopup();
    }

    public void confirmBtnClicked() {
        ItemBroker itemBroker = new ItemBroker(DatabaseManager.getInstance(), DatabaseManager.getEntityManager());
        item.setName(fieldItemName.getText());
        item.setPrice(Float.parseFloat(fieldItemPrice.getText()));
        item.setUpc(fieldItemUPC.getText());
        item.setDescription(fieldItemDescription.getText());
        item.setQuantity(Integer.parseInt(fieldItemQuantity.getText()));

        itemBroker.insert(item);
        element.refresh();
        ControllerManager.getInstance().hidePopup();
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setManagementUIItemElement(ManagementUIItemElement element) {
        this.element = element;
    }
}
