package controller;

import broker.ItemBroker;
import broker.TransactionBroker;
import domain.Item;
import domain.Transaction;
import domain.TransactionItem;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import manager.DatabaseManager;

/**
 *
 * @author Noris. UMM I MEAN: CAPSTONE GROUP, OF COURSE, TIS NOT MY WORK BUT OUR
 * WORK.
 *
 * @Notes Please make sure to correct this code. Namely: Password as well as
 * validation.
 *
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBroker = new ItemBroker(DatabaseManager.getInstance());
        List<Item> items = itemBroker.getAllItems();

        for (Item item : items) {
            addItemToSearch(item);
        }
        transaction = new Transaction();
        transaction.setTransactionItemList(new ArrayList<>());
//        addItemToSale(items.get(0));
//        addItemToSale(items.get(1));
//        addItemToSale(items.get(2));

    }

    /**
     * This method is called by clicking "Logout" which logs the user out of the
     * application and redirect to login screen
     *
     * @param event
     * @throws IOException
     */
    public void logoutBtnClicked(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene logout = new Scene(loginParent);

        // This line grabs the Stage information
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        loginWindow.setScene(logout);
        loginWindow.show();
    }

    /**
     * When TextField searchField is initiated (by clicking on the search field
     * in the order screen), whatever is typed in the field will call
     * textfield.getText(); which grabs the fields input and outputs query
     * underneath in the results pane.
     *
     * @param event
     * @param item
     * @throws IOException
     */
    public void initiateSearch(ActionEvent event, Item item) throws IOException {
        // searchField is the fxid
        result = new Button();

        HBox itemContainer = new HBox(50);

        EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (searchField.getText().equals(item.getName())) {
                    itemContainer.getChildren().addAll(new Label(item.getName()),
                            new Label(Integer.toString(item.getItemID())),
                            new Label(Float.toString(item.getPrice())),
                            result);
                    //No idea how to add the button correctly

                }
            }
        };

        /**
         * When the enter key is pressed
         */
        searchField.setOnAction(e);

    }

    public void addItemToSearch(Item item) {
//        Button base = new Button();
        HBox hbox = new HBox(20);
        Label name = new Label();
        Label price = new Label();
        Label id = new Label();

        name.setMaxHeight(100);
        name.setMinWidth(500);
        name.setMaxWidth(500);

        price.setMaxHeight(100);
        price.setMinWidth(100);
        price.setMaxWidth(100);

        //label1.setTextFill(Color.web("#0076a3"));
        //label.setFont(new Font("Arial", 30));
        name.setTextFill(Color.web("#FFFFFF"));
        price.setTextFill(Color.web("#FFFFFF"));
        name.setFont(new Font("Arial Black", 25));
        price.setFont(new Font("Arial Black", 25));

        id.setPrefSize(0, 0);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        name.setText(item.getName());
        price.setText(item.getPrice() + "");
        id.setText(item.getItemID() + "");

        hbox.getChildren().addAll(name, price);
        hbox.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("HBox clicked.");
                addItemToSale(itemBroker.getItemByID(Integer.parseInt(id.getText())));
            }
        });
        resultContainer.getChildren().add(hbox);
    }

    /**
     * Grab an item to the sale side
     * @param item
     */
    public void addItemToSale(Item item) {
        boolean exist = false;
        if (saleListDisplay.getChildren().size() > 0) {
            for (Node n : saleListDisplay.getChildren()) {
                HBox box = (HBox) n;
                System.out.println(box.getChildren().get(1));
                Label labelID = (Label) box.getChildren().get(0);
                int id = Integer.parseInt(labelID.getText());
                if (id == item.getItemID()) {
                    exist = true;
                    Label quantityLabel = (Label) box.getChildren().get(2);
                    int quant = Integer.parseInt(quantityLabel.getText());
                    quant++;
                    quantityLabel.setText(quant + "");
                }
            }
        }
        if (!exist) {
            Label name = new Label(item.getName());
            Label price = new Label(Float.toString(item.getPrice()));
            Label quantity = new Label(1 + "");
            Label id = new Label(item.getItemID() + "");
            
            id.setPrefSize(0, 0);
            id.setMaxWidth(0);
            id.setMinWidth(0);                        
            id.setMaxHeight(0);
            id.setMinWidth(0);

            HBox itemContainer = new HBox(10);
            itemContainer.setPadding(new Insets(10, 10, 10, 10));

            itemContainer.setMinWidth(522);
            itemContainer.setMinHeight(43);
            itemContainer.setPrefSize(522, 43);

            name.setMinHeight(41);
            name.setMinWidth(320);
            name.setMaxWidth(320);

            price.setMinHeight(41);
            price.setMinWidth(115);
            price.setMaxWidth(115);

            quantity.setMinHeight(41);
            quantity.setMinWidth(65);
            quantity.setMaxWidth(65);

            //label1.setTextFill(Color.web("#0076a3"));
            //label.setFont(new Font("Arial", 30));
            name.setTextFill(Color.web("#FFFFFF"));
            price.setTextFill(Color.web("#FFFFFF"));
            quantity.setTextFill(Color.web("#FFFFFF"));

            name.setFont(new Font("Arial Black", 25));
            price.setFont(new Font("Arial Black", 25));
            quantity.setFont(new Font("Arial Black", 25));

            itemContainer.getChildren().addAll(id, name, quantity, price);

            /**
             * Add the item container to the itemListDisplay
             */
            saleListDisplay.getChildren().addAll(itemContainer);
            System.out.println(saleListDisplay.getChildren());
        }

        TransactionItem tItem = new TransactionItem();
        tItem.setItem(item);
        transaction.getTransactionItemList().add(tItem);
        calculateSubtotal(item);
    }

    public void calculateSubtotal(Item item) {
        float total = 0f;
        for (TransactionItem tItem : transaction.getTransactionItemList()) {
            total += tItem.getItem().getPrice();
        }
//        total = Float.parseFloat(subtotalDisplay.getText());
//        total = total + (item.getPrice());
        calculateTax(total);
        subtotalDisplay.setText(String.format("%.2f", total));
    }

    public void calculateTax(float value) {
        float tax = value * 0.05f;
        taxDisplay.setText(String.format("$%.2f", tax));
        updateTotal(value + tax);
    }

    public void updateTotal(float value) {
        finalPriceDisplay.setText(String.format("$%.2f", value));
    }
}
