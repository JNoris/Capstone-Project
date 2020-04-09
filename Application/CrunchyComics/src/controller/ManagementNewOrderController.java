package controller;

import broker.ItemBroker;
import broker.OrderBroker;
import broker.VendorBroker;
import domain.Item;
import domain.OrderItem;
import domain.Orders;
import domain.Vendor;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import manager.ControllerManager;

/**
 * Controls the logic for the popup of the ManagementNewOrder.
 * @author Vinicius Smith
 */
public class ManagementNewOrderController implements Initializable {

    @FXML
    Label currentDateLabel;
    @FXML
    ComboBox monthComboBox;
    @FXML
    ComboBox dayComboBox;
    @FXML
    ComboBox yearComboBox;
    @FXML
    ComboBox vendorComboBox;
    @FXML
    VBox itemListContainer;
    @FXML
    VBox orderListContainer;
    @FXML
    TextField itemSearchField;

    private Orders order;
    private EventHandler<MouseEvent> itemClicked;
    private ManagementController mgntController;
    
    /**
     * Loads the event handlers to add the order items to the <code>orderListContainer</code>.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Label) event.getSource()).getUserData();
                addToOrderList((Item) ((Label) event.getSource()).getUserData());
            }
        };

        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
        currentDateLabel.setText(format.format(new Date()));
        
        
        monthComboBox.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
        monthComboBox.setValue(monthComboBox.getItems().get(0));
        dayComboBox.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
        dayComboBox.setValue(dayComboBox.getItems().get(0));

        String year = currentDateLabel.getText().substring(0, 4);
        yearComboBox.setItems(FXCollections.observableArrayList(year, (Integer.parseInt(year) + 1) + ""));
        yearComboBox.setValue(yearComboBox.getItems().get(0));
        
        VendorBroker vendorBroker = new VendorBroker();

        vendorComboBox.setItems(FXCollections.observableList(vendorBroker.getAllVendors()));

        ItemBroker itemBroker = new ItemBroker();
        List<Item> items = itemBroker.getAllItems();
        fillItemList(items);
    }
    
    /**
     * Sets the order to inputted value.
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

    public void monthChanged() {
        String month = (String) monthComboBox.getValue();
        if (month.equals("January") || month.equals("March") || month.equals("May") || month.equals("July") || month.equals("August") || month.equals("October") || month.equals("December")) {
            dayComboBox.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                    "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
        } else if (month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")) {
            if (Integer.parseInt(dayComboBox.getValue().toString()) > 30) {
                dayComboBox.setValue("30");
            }
            dayComboBox.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                    "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"));
        } else {
            if (Integer.parseInt(dayComboBox.getValue().toString()) > 28) {
                dayComboBox.setValue("28");
            }
            dayComboBox.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                    "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"));
        }
    }
    
    /**
     * Loads the items from the database and displays the items list.
     * @param items 
     */
    public void fillItemList(List<Item> items) {
        itemListContainer.getChildren().clear();
        for (Item i : items) {
            Label name = new Label(i.getName());
            name.getStylesheets().add("/fxml/managementscreen.css");
            name.getStyleClass().add("itemListContainer");
            name.setFont(Font.font("Arial Black",12));
            name.setUserData(i);
            name.setOnMouseClicked(itemClicked);
            itemListContainer.getChildren().add(name);
        }
    }

    /**
     * Traverses through the items database until the input from the front-end matches with the item in the database
     */
    public void searchItems() {
        ItemBroker itemBroker = new ItemBroker();
        fillItemList(itemBroker.getMatchingItems(itemSearchField.getText()));
    }

    /**
     * Loads the specific item into the <code>HBox</code> container and displays the name,price, and quantity.
     * @param item 
     */
    public void addToOrderList(Item item) {
        HBox container = new HBox();
        Label name = new Label(item.getName());
        TextField price = new TextField();
        TextField quantity = new TextField();
        Button removeBtn = new Button("Remove");
        container.getStylesheets().add("/fxml/managementscreen.css");
        container.getStyleClass().add("itemListContainer");

        name.setMaxWidth(120);
        name.setMinWidth(120);
        name.setFont(Font.font("Arial Black",12));

        price.setMaxWidth(70);
        price.setMinWidth(70);
        price.setPromptText("Price");
        price.setFont(Font.font("Arial Black",12));

        quantity.setMaxWidth(45);
        quantity.setMinWidth(45);
        quantity.setText("0");
        quantity.setFont(Font.font("Arial Black",12));
        
        removeBtn.setFont(Font.font("Arial Black",12));

        container.setUserData(item);
        container.getChildren().addAll(name, price, quantity, removeBtn);
        orderListContainer.getChildren().add(container);

        removeBtn.setOnMouseReleased(e -> {
            orderListContainer.getChildren().remove(container);
        });
    }

    /**
     * The button to validate correct user input.
     */
    public void confirmBtnClicked() {
        //Error checking
        if(vendorComboBox.getValue() == null || vendorComboBox.getValue().toString().isEmpty()){
            vendorComboBox.requestFocus();
            return;
        }
        if(orderListContainer.getChildren().size() == 0){
            orderListContainer.requestFocus();
            return;
        }
        //Check if vendor exists
        int vendorid = -1;
        String vendorName = vendorComboBox.getValue().toString();
        VendorBroker vendorBroker = new VendorBroker();
        for (Vendor v : vendorBroker.getAllVendors()) {
            if (vendorName.equalsIgnoreCase(v.getVendorName())) {
                vendorid = v.getVendorID();
            }
        }
        //Vendor does not exist. Adds new vendor to database.
        if (vendorid == -1) {
            System.out.println("Adding vendor to database.");
            vendorid = vendorBroker.getLastID() + 1;
            vendorBroker.insert(new Vendor(vendorid, vendorName));
        }       
        OrderBroker orderBroker = new OrderBroker();
        int orderID = orderBroker.getLastID() + 1;

        List<OrderItem> orderItems = new ArrayList<>();
        for (Node n : orderListContainer.getChildren()) {
            HBox hbox = (HBox) n;
            Item item = (Item) hbox.getUserData();

            OrderItem oi = new OrderItem(item.getItemID(), orderID);
            oi.setPurchasePrice(Float.parseFloat(((TextField) hbox.getChildren().get(1)).getText()));
            oi.setOrderQuantity(Integer.parseInt(((TextField) hbox.getChildren().get(2)).getText()));
            oi.setItem(item);
            orderItems.add(oi);
        }

        order.setOrderNo(orderID);
        order.setVendorID(new Vendor(vendorid, vendorName));
        order.setOrderItemList(orderItems);
        String arrivalDate = yearComboBox.getValue().toString() + "-" + monthComboBox.getValue().toString() + "-" + dayComboBox.getValue().toString();

        try {
            order.setArrivalDate(new SimpleDateFormat("yyyy-MMMMM-dd").parse(arrivalDate));
            order.setOrderDate(new Date());
        } catch (ParseException e) {
            System.out.println("Error parsing date. Error: " + e.getMessage());
            return;
        }

        orderBroker.insert(order);
        mgntController.populateOrders();
        ControllerManager.getInstance().hidePopup();
    }

    /**
     * Controls the many ManagementControllers and sets the value to the specific ManagementController.
     * @param mgntController 
     */
    public void setManagementController(ManagementController mgntController) {
        this.mgntController = mgntController;
    }
}
