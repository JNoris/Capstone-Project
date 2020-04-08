/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.OrderItem;
import domain.Orders;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import manager.ControllerManager;

/**
 * Controls the logic to modify data within the ManagementUIOrder container.
 * @author Vinicius Smith
 */
public class ManagementUIOrderController {

    @FXML
    private Label labelOrderID;
    @FXML
    private Label labelOrderDate;
    @FXML
    private Label labelOrderArrivalDate;
    @FXML
    private Label labelOrderVendor;
    @FXML
    private VBox orderItemContainer;

    private Orders order;

    /**
     * Loads the dates and order information into the <code>orderItemContainer</code>
     */
    public void populate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");

        labelOrderID.setText(order.getOrderNo() + "");
        labelOrderDate.setText(format.format(order.getOrderDate()));
        labelOrderArrivalDate.setText(format.format(order.getArrivalDate()));
        labelOrderVendor.setText(order.getVendorID().getVendorName());

        //OrderItems list logic
        
        List<OrderItem> list = order.getOrderItemList();
        System.out.println(list);
        for (OrderItem oi : list) {
            HBox itemContainer = new HBox();
            Label itemName = new Label(oi.getItem().getName());
            Label itemPurchasePrice = new Label(String.format("$%.2f", oi.getPurchasePrice()));
            Label itemQuantity = new Label(oi.getOrderQuantity() + "");
            Label itemTotal = new Label(String.format("$%.2f", oi.getOrderQuantity() * oi.getPurchasePrice()));
            
            //Sizes and Font
            itemName.setMinWidth(150);
            itemName.setMaxWidth(150);
            itemName.setFont(Font.font("Arial Black",12));
            
            itemPurchasePrice.setMinWidth(45);
            itemPurchasePrice.setMaxWidth(45);
            itemPurchasePrice.setFont(Font.font("Arial Black",12));
            
            itemQuantity.setMinWidth(30);
            itemQuantity.setMaxWidth(30);
            itemQuantity.setFont(Font.font("Arial Black",12));
            
            itemTotal.setMinWidth(45);
            itemTotal.setMaxWidth(45);
            itemTotal.setFont(Font.font("Arial Black",12));
            
            itemContainer.getChildren().addAll(itemName, itemPurchasePrice, itemQuantity, itemTotal);
            orderItemContainer.getChildren().add(itemContainer);
        }
    }

    /**
     * Sets the order to the inputted values
     * @param order 
     */
    public void setOrder(Orders order) {
        this.order = order;
    }

    /**
     * The button used to validate incorrect user input.
     */
    public void cancelBtnClicked() {
        ControllerManager.getInstance().hidePopup();
    }
}
