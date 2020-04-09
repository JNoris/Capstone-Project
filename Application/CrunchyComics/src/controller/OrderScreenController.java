package controller;

import broker.ItemBroker;
import broker.TransactionBroker;
import domain.Item;
import domain.Transaction;
import domain.TransactionItem;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import manager.ControllerManager;
import manager.DatabaseManager;
import ui.TransactionUIElement;

/**
 * Controls the logic within the Order Screen to create transactions.
 */
public class OrderScreenController implements Initializable {

    @FXML
    private ScrollPane searchDisplay;
    @FXML
    private Label saleIDDisplay;
    @FXML
    private TextField searchField;
    @FXML
    private Label subtotalDisplay;
    @FXML
    private Label taxDisplay;
    @FXML
    private Label finalPriceDisplay;
    @FXML
    private Label itemNameDisplay;
    @FXML
    private Label itemPriceDisplay;
    @FXML
    private Label itemQuantityDisplay;
    @FXML
    private VBox saleListDisplay;
    @FXML
    private VBox resultContainer;

    private TransactionBroker tb;
    private Button result;
    private ItemBroker itemBroker;
    private Transaction transaction;
//    private Popup popup;

    /**
     * Loads all previous transactions in the database and opens resources to add to the database on startup.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBroker = new ItemBroker(DatabaseManager.getInstance(), DatabaseManager.getEntityManager());
        tb = new TransactionBroker(DatabaseManager.getInstance());

        List<Item> items = itemBroker.getAllItems();

        for (Item item : items) {
            addItemToSearch(item);
        }

        //Create new transaction object.
        transaction = new Transaction();
        transaction.setTransactionItemList(new ArrayList<TransactionItem>());
        //Set transaction date
        transaction.setTransactionID(tb.getHighestID() + 1);

    }

    /**
     * This method is called by clicking "Logout" which logs the user out of the
     * application and redirect to login screen
     *
     * @param event
     * @throws IOException
     */
    public void logoutBtnClicked(ActionEvent event) throws IOException {

        Scene logout = ControllerManager.getInstance().getLoginScreen();
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        loginWindow.setScene(logout);
        loginWindow.show();
    }
    
    /**
     * Adds the requested item to the search container and displays the value.
     * @param item 
     */
    public void addItemToSearch(Item item) {
        HBox hbox = new HBox(20);
        Label name = new Label();
        Label price = new Label();
        Label id = new Label();

        hbox.getStylesheets().add("/fxml/orderscreen.css");
        hbox.getStyleClass().add("searchBox");

        name.setMaxHeight(100);
        name.setMinWidth(500);
        name.setMaxWidth(500);

        price.setMaxHeight(100);
        price.setMinWidth(100);
        price.setMaxWidth(100);

        name.setTextFill(Color.web("#FFFFFF"));
        price.setTextFill(Color.web("#FFFFFF"));
        name.setFont(new Font("Arial Black", 25));
        price.setFont(new Font("Arial Black", 25));

        id.setPrefSize(0, 0);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        name.setText(item.getName());
        price.setText(String.format("%.2f", item.getPrice()) + "");
        id.setText(item.getItemID() + "");

        hbox.getChildren().addAll(name, price);
        hbox.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                addItemToSale(itemBroker.getItemByID(Integer.parseInt(id.getText())));
            }
        });
        resultContainer.getChildren().add(hbox);
    }

    /**
     * Grab an item to the sale side.
     *
     */
    public void addItemToSale(Item item) {
        boolean exist = false;
        if (saleListDisplay.getChildren().size() > 0) {
            for (Node n : saleListDisplay.getChildren()) {
                TransactionUIElement e = (TransactionUIElement) n;
                for (TransactionItem t : transaction.getTransactionItemList()) {
                    if (e.getTransactionItem().getItem() == item) {
                        if (e.getTransactionItem().getTransactionItemPK().getSoldPrice() == item.getPrice()) {
                            System.out.println("Updating existing line.");
                            exist = true;

                            e.getTransactionItem().getTransactionItemPK().setQuantity(e.getTransactionItem().getTransactionItemPK().getQuantity() + 1);
                            e.refresh();
                            System.out.println(e.getTransactionItem());
                            calculateSubtotal();
                            return;
                        }
                    }
                }
            }
        }
        if (!exist) {
            System.out.println("Creating new line.");
            //Create a TransactionItem
            TransactionItem tItem = new TransactionItem(item.getItemID(), transaction.getTransactionID(), 1, item.getPrice());
            tItem.setItem(item);
            tItem.getTransactionItemPK().setSoldPrice(item.getPrice()); //Sets price of the item as the original price of the item.
            transaction.getTransactionItemList().add(tItem);

            TransactionUIElement t = new TransactionUIElement(tItem, this);
            t.getStylesheets().add("/fxml/orderscreen.css");
            t.getStyleClass().add("transactionItem");
            saleListDisplay.getChildren().addAll(t);
        }
        calculateSubtotal();
    }

    /**
     * Calculates current price total, based on item price; disregarding extra fees and values.
     */
    public void calculateSubtotal() {
        float total = 0f;
        for (TransactionItem tItem : transaction.getTransactionItemList()) {
            total += tItem.getTransactionItemPK().getSoldPrice() * tItem.getTransactionItemPK().getQuantity();
        }
        calculateTax(total);
        subtotalDisplay.setText(String.format("$%.2f", total));
    }

    /**
     * Calculates government tax of the item by itself.
     * @param value 
     */
    public void calculateTax(float value) {
        float tax = value * 0.05f;
        taxDisplay.setText(String.format("%.2f", tax));
        updateTotal(value + tax);
    }
    
    /**
     * Adds the value of the subtotal and tax together, creating the final price.
     * @param value 
     */
    public void updateTotal(float value) {
        finalPriceDisplay.setText(String.format("%.2f", value));
    }

    /**
     * Searches the database for the item requested by the user and matches the item in the database.
     */
    public void searchItems() {
        resultContainer.getChildren().clear();
        List<Item> items = itemBroker.getMatchingItems(searchField.getText());
        for (Item i : items) {
            addItemToSearch(i);
        }
    }

    /**
     * When the Finish Order is clicked this method called. It checks if the
     * transaction list is not empty.
     */
    public void completeTransaction() {
        //Check if there is anything on the transaction list.
        if (transaction.getTransactionItemList().size() == 0) {
            returnToMainScreen();
            return;
        }

        //Set transaction date.
        transaction.setTransactionDate(new Date());
        transaction.setFinalPrice(Float.parseFloat(finalPriceDisplay.getText()));
        tb.insert(transaction);

        returnToMainScreen();
    }

    /**
     * This method changes the screen to the MainScreen.
     */
    public void returnToMainScreen() {
        //Get the controller for the MainScreen.
        MainScreenController c = (MainScreenController) ((FXMLLoader) ControllerManager.getInstance().getMainScreen().getUserData()).getController();
        //Updates the transactions in the MainScreen.
        c.showTransactions();
        //Set scene to main screen.
        ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getMainScreen());

    }

    /**
     * Displays the current sale items on the database.
     * @return 
     */
    public ArrayList<TransactionUIElement> getAllSaleElements() {
        ArrayList<TransactionUIElement> list = new ArrayList<TransactionUIElement>();
        for (Node n : saleListDisplay.getChildren()) {
            list.add((TransactionUIElement) n);
        }
        return list;
    }

    /**
     * Creates the popup when the modification of the transaction needs to be made and transfers control to the TransactionUIElementController.
     * @param element
     * @param item 
     */
    public void createPopup(TransactionUIElement element, TransactionItem item) {
        if (ControllerManager.getInstance().getPopup() != null) {
            System.out.println("Hiding old popup");
            ControllerManager.getInstance().getPopup().hide();
        }
        System.out.println("Creating popup");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrderScreenItemPopup.fxml"));
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);
        try {
            TransactionUIElementController controller = new TransactionUIElementController();
            loader.setController(controller);
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
            controller.fill(item);
            controller.setPopup(popup);
            controller.setNode(element);
        } catch (IOException e) {
            System.exit(0);
        }
    }

    /**
     * Removes requested item from the sale list .
     * @param e 
     */
    public void removeFromSale(TransactionUIElement e) {
        saleListDisplay.getChildren().remove(e);
        calculateSubtotal();
    }

    /**
     * Gets the currently created transaction.
     * @return the transaction currently made
     */
    public Transaction getTransaction() {
        return this.transaction;
    }
}
