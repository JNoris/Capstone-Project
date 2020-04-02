/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Popup;
import ui.TransactionUIElement;

/**
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
    private TextField percentageFlatAmount;
    @FXML
    private CheckBox discountEnableCheck;
    @FXML
    private ToggleGroup discount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Default 
        discountFlatAmount.disableProperty().set(true);
        percentageFlatAmount.disableProperty().set(true);
        discount.getToggles().forEach(t -> {
            ((Node) t).setDisable(true);
        });

        //Add an event for when the discount checkbox is clicked
        discountEnableCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (discountEnableCheck.isSelected()) {
                    discountFlatAmount.disableProperty().set(false);
                    percentageFlatAmount.disableProperty().set(false);
                    discount.getToggles().forEach(t -> {
                        ((Node) t).setDisable(false);
                    });
                } else {
                    discountFlatAmount.disableProperty().set(true);
                    percentageFlatAmount.disableProperty().set(true);
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
     * @param item - target TransactionItem
     */
    public void fill(TransactionItem item) {
        itemName.setText(item.getItem().getName());
        itemOriginalPrice.setText(String.format("%.2f", item.getItem().getPrice()));
        itemQuantity.setText(item.getTransactionItemPK().getQuantity() + "");
    }

    public void applyChanges() {
        node.getTransactionItem().getTransactionItemPK().setQuantity(Integer.parseInt(itemQuantity.getText()));

        //Apply discount if enabled
        if (discountEnableCheck.isSelected()) {
            String selectedToggle = ((RadioButton) discount.getSelectedToggle()).getText();
            if (selectedToggle.equals("Flat")) {
                node.getTransactionItem().getTransactionItemPK().setSoldPrice(node.getTransactionItem().getTransactionItemPK().getSoldPrice() - Float.parseFloat(discountFlatAmount.getText()));
            } else {

            }
        }
        node.refresh();
        
        //Look for duplicate
        node.lookForDuplicate();
        
        closePopup();
    }

    public void requestDeleteItem() {

    }

    public void closePopup() {
        this.popup.hide();
    }

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    public void setNode(TransactionUIElement node) {
        this.node = node;
    }
}
