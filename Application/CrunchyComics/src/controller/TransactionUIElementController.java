package controller;

import domain.TransactionItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Popup;
import ui.TransactionUIElement;
import utility.Timer;

/**
 * Controls the modification of price within the transaction sale pop-up.
 *
 * @author Vinicius Smith
 */
public class TransactionUIElementController implements Initializable {

    private Popup popup;
    private TransactionUIElement node;

    @FXML
    private Label itemName;
    @FXML
    private Label itemOriginalPrice;
    @FXML
    private TextField itemQuantity;
    @FXML
    private TextField discountFlatAmount;
    @FXML
    private TextField discountPercentageAmount;
    @FXML
    private CheckBox discountEnableCheck;
    @FXML
    private ToggleGroup discount;

    /**
     * Loads default values into the pop-up and sets discount to false until
     * needed.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Default 
        discountFlatAmount.disableProperty().set(true);
        discountPercentageAmount.disableProperty().set(true);
        discount.getToggles().forEach(t -> {
            ((Node) t).setDisable(true);
        });

        //Add an event for when the discount checkbox is clicked
        discountEnableCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (discountEnableCheck.isSelected()) {
                    discountFlatAmount.disableProperty().set(false);
                    discountPercentageAmount.disableProperty().set(false);
                    discount.getToggles().forEach(t -> {
                        ((Node) t).setDisable(false);
                    });
                } else {
                    discountFlatAmount.disableProperty().set(true);
                    discountPercentageAmount.disableProperty().set(true);
                    discount.getToggles().forEach(t -> {
                        ((Node) t).setDisable(true);
                    });
                }
            }
        });
    }

    /**
     * Fills fields with the information from a TrasactionItem.
     *
     * @param item target TransactionItem.
     */
    public void fill(TransactionItem item) {
        itemName.setText(item.getItem().getName());
        itemOriginalPrice.setText(String.format("%.2f", item.getItem().getPrice()));
        itemQuantity.setText(item.getTransactionItemPK().getQuantity() + "");
    }

    /**
     * Saves the changes to the transaction by changing the value of the price.
     */
    public void applyChanges() {
        Timer.getInstance().resetTimer();
        node.getTransactionItem().getTransactionItemPK().setQuantity(Integer.parseInt(itemQuantity.getText()));

        //Apply discount if enabled
        if (discountEnableCheck.isSelected()) { //Flat
            String selectedToggle = ((RadioButton) discount.getSelectedToggle()).getText();
            if (selectedToggle.equals("Flat")) {
                node.getTransactionItem().getTransactionItemPK().setSoldPrice(node.getTransactionItem().getTransactionItemPK().getSoldPrice() - Float.parseFloat(discountFlatAmount.getText()));
            } else { //Percentage
                node.getTransactionItem().getTransactionItemPK().setSoldPrice(node.getTransactionItem().getTransactionItemPK().getSoldPrice()
                        - node.getTransactionItem().getTransactionItemPK().getSoldPrice() * Float.parseFloat(discountPercentageAmount.getText()) / 100.0f);
            }
        }
        node.refresh();

        //Look for duplicate
        node.fixDuplicates();

        closePopup();
    }

    /**
     * Removes the selected item from the transaction.
     */
    public void requestDeleteItem() {
        node.selfDelete();
        closePopup();
    }

    /**
     * Removes the pop-up from view and redirects control to the
     * OrderScreenController.
     */
    public void closePopup() {
        Timer.getInstance().resetTimer();
        this.popup.hide();
    }

    /**
     * Sets the pop-up to the TransactionUIElement pop-up.
     *
     * @param popup new pop-up.
     */
    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    /**
     * Sets the node that called the controller.
     *
     * @param node new node.
     */
    public void setNode(TransactionUIElement node) {
        this.node = node;
    }
}
