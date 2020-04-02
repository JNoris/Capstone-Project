/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.OrderScreenController;
import controller.TransactionUIElementController;
import domain.Item;
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

    public TransactionUIElement(TransactionItem item, OrderScreenController controller) {
        this.transactionItem = item;
        this.controller = controller;
        this.name = new Label(item.getItem().getName());
        this.price = new Label(Float.toString(item.getTransactionItemPK().getSoldPrice() * item.getTransactionItemPK().getQuantity()));
        this.quantity = new Label(1 + "");
        this.id = new Label(item.getItem().getItemID() + "");

        //Make ID invisible
        id.setPrefSize(0, 0);
        id.setMaxWidth(0);
        id.setMinWidth(0);
        id.setMaxHeight(0);
        id.setMinWidth(0);

//            HBox itemContainer = new HBox(10);
        this.spacingProperty().set(10);
        this.setPadding(new Insets(10, 10, 10, 10));

        this.setMinWidth(522);
        this.setMinHeight(43);
        this.setPrefSize(522, 43);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createPopup(TransactionUIElement.this.transactionItem);
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

        //label1.setTextFill(Color.web("#0076a3"));
        //label.setFont(new Font("Arial", 30));
        this.name.setTextFill(Color.web("#FFFFFF"));
        this.price.setTextFill(Color.web("#FFFFFF"));
        this.quantity.setTextFill(Color.web("#FFFFFF"));

        this.name.setFont(new Font("Arial Black", 25));
        this.price.setFont(new Font("Arial Black", 25));
        this.quantity.setFont(new Font("Arial Black", 25));

        this.getChildren().addAll(id, name, quantity, price);
    }

    public String getName() {
        return name.getText();
    }

    public int getQuantity() {
        return Integer.parseInt(quantity.getText());
    }

    public int getItemId() {
        return Integer.parseInt(id.getText());
    }

    public float getPrice() {
        return Float.parseFloat(price.getText());
    }

    public TransactionItem getTransactionItem() {
        return transactionItem;
    }

    /**
     * Refreshes the UI elements information with updated information from the
     * TransactionItem object.
     */
    public void refresh() {
        this.quantity.setText(transactionItem.getTransactionItemPK().getQuantity() + "");
        this.price.setText(transactionItem.getTransactionItemPK().getSoldPrice() * transactionItem.getTransactionItemPK().getQuantity() + "");
        this.controller.calculateSubtotal();
    }

    //Pop up
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

    public TransactionUIElement lookForDuplicate() {
        System.out.println("Looking for duplicate...");
        for (TransactionUIElement other : controller.getAllSaleElements()) {
            if (this.equals(other) && other != this) {
                System.out.println("Found duplicate");
                //Add current this quantity to the other quantity
                //refresh() other
                //Remove this.TransactionUI from transactionList
                //Remove this from UI
            }
        }
        return null;
    }

    public boolean equals(TransactionUIElement other) {
        if (this.transactionItem.getTransactionItemPK().equals(other.getTransactionItem().getTransactionItemPK())) {
            return true;
        }
        return false;
    }
}
