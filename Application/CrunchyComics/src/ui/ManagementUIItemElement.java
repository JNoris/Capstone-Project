/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ManagementController;
import domain.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 *
 * @author Vinicius Smith
 */
public class ManagementUIItemElement extends HBox {

    private ManagementController controller;

    private Item item;
    private Label itemName;
    private Label itemQuantity;
    private Label itemPrice;
    private Label itemType;
    
    private Label edit;

    public ManagementUIItemElement(ManagementController controller, Item item) {
        this.controller = controller;
        this.item = item;

        this.itemName = new Label(item.getName());
        this.itemQuantity = new Label(item.getQuantity() + "");
        this.itemPrice = new Label(String.format("$%.2f", item.getPrice()));
        this.itemType = new Label(item.getItemType().getItemType());
        
        //CSS
        this.getStylesheets().add("/fxml/management.css");
        this.getStyleClass().add("items");
        //Fonts
        itemName.setFont(new Font("Arial Black", 15));
        itemQuantity.setFont(new Font("Arial Black", 15));
        itemPrice.setFont(new Font("Arial Black", 15));
        itemType.setFont(new Font("Arial Black", 15));

        //Sizes
        itemName.setMinWidth(200);
        itemName.setMaxWidth(175);

        itemQuantity.setMinWidth(70);
        itemQuantity.setMaxWidth(70);

        itemPrice.setMinWidth(70);
        itemPrice.setMaxWidth(70);

        //Layout
        itemQuantity.setAlignment(Pos.CENTER);

        //Add event on click
        this.setOnMouseClicked((event) -> {
            controller.createItemManagementPopup(this, item);
        });

        //Add all elements to the screen.
        this.getChildren().addAll(itemName, itemQuantity, itemPrice, itemType);
    }

    public void refresh() {
        this.itemName.setText(item.getName());
        this.itemQuantity.setText(item.getQuantity() + "");
        this.itemPrice.setText(String.format("$%.2f", item.getPrice()));
        this.itemType.setText(item.getItemType().getItemType());
    }
}
