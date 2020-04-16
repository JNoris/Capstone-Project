/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.OrderScreenController;
import controller.TransactionUIElementController;
import domain.TransactionItem;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import manager.ControllerManager;

/**
 * This class is an UI element that contains information used for the management
 * of transactions in the database.
 *
 * @author Vinicius Smith
 */
public class TransactionUIElement extends HBox {

    private Label name;
    private Label quantity;
    private Label id;
    private Label price;
    private TransactionItem transactionItem;
    private OrderScreenController controller;

    /**
     * Constructs a new TransactionUIElement with information from the
     * TransactionItem item.
     *
     * @param item transactionItem reference
     * @param controller controller reference
     */
    public TransactionUIElement(TransactionItem item, OrderScreenController controller) {
        this.transactionItem = item;
        this.controller = controller;
        this.name = new Label(item.getItem().getName());
        this.price = new Label(String.format("%.2f", item.getTransactionItemPK().getSoldPrice() * item.getTransactionItemPK().getQuantity()));
        this.quantity = new Label(1 + "");
        this.id = new Label(item.getItem().getItemID() + "");

        //Make ID invisible
        id.setPrefSize(0, 0);
        id.setMaxWidth(0);
        id.setMinWidth(0);
        id.setMaxHeight(0);
        id.setMinWidth(0);

        this.spacingProperty().set(10);
        this.setPadding(new Insets(10, 10, 10, 10));

        this.setMinWidth(522);
        this.setMinHeight(43);
        this.setPrefSize(522, 43);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.createPopup(TransactionUIElement.this, TransactionUIElement.this.transactionItem);
            }
        });

        this.name.setMinHeight(41);
        this.name.setMinWidth(320);
        this.name.setMaxWidth(320);

        this.price.setMinHeight(41);
        this.price.setMinWidth(115);
        this.price.setMaxWidth(115);

        this.quantity.setMinHeight(41);
        this.quantity.setMinWidth(65);
        this.quantity.setMaxWidth(65);

        this.name.setTextFill(Color.web("#FFFFFF"));
        this.price.setTextFill(Color.web("#FFFFFF"));
        this.quantity.setTextFill(Color.web("#FFFFFF"));

        this.name.setFont(new Font("Arial Black", 25));
        this.price.setFont(new Font("Arial Black", 25));
        this.quantity.setFont(new Font("Arial Black", 25));

        this.getChildren().addAll(id, name, quantity, price);
    }

    /**
     * Gets the name from UI.
     *
     * @return the name.
     */
    public String getName() {
        return name.getText();
    }

    /**
     * Gets the quantity from the UI.
     *
     * @return the quantity.
     */
    public int getQuantity() {
        return Integer.parseInt(quantity.getText());
    }

    /**
     * Gets the item id from the UI.
     *
     * @return the item id.
     */
    public int getItemId() {
        return Integer.parseInt(id.getText());
    }

    /**
     * Gets the price from the UI.
     *
     * @return the price.
     */
    public float getPrice() {
        return Float.parseFloat(price.getText());
    }

    /**
     * Gets the transaction item.
     *
     * @return the transaction item.
     */
    public TransactionItem getTransactionItem() {
        return transactionItem;
    }

    /**
     * Refreshes the UI elements information with updated information from the
     * TransactionItem object.
     */
    public void refresh() {
        this.quantity.setText(transactionItem.getTransactionItemPK().getQuantity() + "");
        this.price.setText(String.format("%.2f", transactionItem.getTransactionItemPK().getSoldPrice() * transactionItem.getTransactionItemPK().getQuantity()) + "");
        this.controller.calculateSubtotal();
    }

    /**
     * Creates a pop-up that allows the user to modify transaction item
     * information easily.
     *
     * @param item reference to the transaction item.
     */
    public void createPopup(TransactionItem item) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrderScreenItemPopup.fxml"));
        Popup popup = new Popup();
        try {
            TransactionUIElementController controller = new TransactionUIElementController();
            loader.setController(controller);
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
            controller.fill(item);
            controller.setPopup(popup);
            controller.setNode(this);
        } catch (IOException e) {
            System.exit(0);
        }
    }

    /**
     * Check for UI elements with same information as this object. If a match is
     * found certain informations are combined and the quantity of the match is
     * increased. This object is then remove from the UI.
     */
    public void fixDuplicates() {
        System.out.println("Looking for duplicate...");
        for (TransactionUIElement other : controller.getAllSaleElements()) {
            if (this.equals(other) && other != this) {
                System.out.println("Found duplicate");
                //Add current this quantity to the other quantity
                other.getTransactionItem().getTransactionItemPK().setQuantity(this.transactionItem.getTransactionItemPK().getQuantity()
                        + other.getTransactionItem().getTransactionItemPK().getQuantity());
                //refresh() other
                other.refresh();
                //Remove this.TransactionUI from transactionList
                controller.getTransaction().getTransactionItemList().remove(this.transactionItem);
                //Remove this TransactionUIElement from UI
                controller.removeFromSale(this);
                return;
            }
        }
    }

    /**
     * Delete this element from the list and removes the transaction item
     * associated with it from the transaction.
     */
    public void selfDelete() {
        controller.getTransaction().getTransactionItemList().remove(this.transactionItem);
        controller.removeFromSale(this);
    }

    /**
     * Compares the UI element with another base on the TranscationItem.
     *
     * @param other the other UI element.
     * @return True if they have TransactionItems with the same information.
     * False otherwise.
     */
    public boolean equals(TransactionUIElement other) {
        if (this.transactionItem.getTransactionItemPK().equals(other.getTransactionItem().getTransactionItemPK())) {
            return true;
        }
        return false;
    }
}
