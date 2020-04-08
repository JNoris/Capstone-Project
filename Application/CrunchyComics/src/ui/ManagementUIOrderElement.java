/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ManagementController;
import domain.Orders;
import java.text.SimpleDateFormat;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 *
 * @author Vinicius Smith
 */
public class ManagementUIOrderElement extends HBox {

    private ManagementController mgntController;
    private Orders order;
    private SimpleDateFormat format;

    private Label orderNumber;
    private Label orderDate;
    private Label orderArrivalDate;
    private Label orderVendor;

    public ManagementUIOrderElement(ManagementController mgntController, Orders order) {
        this.mgntController = mgntController;
        this.order = order;
        this.format = new SimpleDateFormat("yyyy-dd-MM");

        orderNumber = new Label(order.getOrderNo() + "");
        orderDate = new Label(format.format(order.getOrderDate()));
        orderArrivalDate = new Label(format.format(order.getArrivalDate()));
        orderVendor = new Label(order.getVendorID().getVendorName());

        //CSS
        this.getStylesheets().add("/fxml/management.css");
        this.getStyleClass().add("items");
        
        //Fonts
        orderNumber.setFont(new Font("Arial Black", 15));
        orderDate.setFont(new Font("Arial Black", 15));
        orderArrivalDate.setFont(new Font("Arial Black", 15));
        orderVendor.setFont(new Font("Arial Black", 15));

        //Sizes
        orderNumber.setMinWidth(20);
        orderNumber.setMaxWidth(20);

        orderDate.setMinWidth(110);
        orderDate.setMaxWidth(110);

        orderArrivalDate.setMinWidth(110);
        orderArrivalDate.setMaxWidth(110);

        this.setOnMouseReleased(e -> {
//            System.out.println("Order: " + order);
            mgntController.createOrderManagementPopup(this, order);
        });

        this.getChildren().addAll(orderNumber, orderDate, orderArrivalDate, orderVendor);
    }
}
