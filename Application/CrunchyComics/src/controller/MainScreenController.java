package controller;

import broker.TransactionBroker;
import broker.TransactionItemBroker;
import domain.Transaction;
import domain.TransactionItem;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import manager.ControllerManager;
import manager.DatabaseManager;

/**
 * Controls all of the logic for the main screen.
 */
public class MainScreenController implements Initializable {

    @FXML
    private VBox mainScreenDisplay;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label loginTime;
    @FXML
    private Label mostPopularItem;
    @FXML
    private Label dailySales;
    @FXML
    private Label lastWeekSales;
    @FXML
    private Label averageSales;

    private List<Transaction> transactions;

    /**
     * Loads all of the transactions within the database as well as the
     * daily/weekly records.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing MainScreenController");

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        currentTimeLabel.setText(format.format(new Date()));

        TransactionItemBroker tiBroker = new TransactionItemBroker(DatabaseManager.getInstance());
        TransactionItem ti = tiBroker.getMostSold();
        if (ti != null) {
            mostPopularItem.setText(ti.getItem().getName());
        }

        showTransactions();
    }

    /**
     * Populates the transaction container with existent transactions. Orders by
     * newest first.
     */
    public void showTransactions() {
        mainScreenDisplay.getChildren().clear();

        TransactionBroker tb = new TransactionBroker(DatabaseManager.getInstance());
        transactions = tb.getAllTransactions();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            addOrderToMainScreen(transactions.get(i));
        }
    }

    /**
     * This method is called by clicking "Logout" and will redirect user to
     * login screen.
     *
     * @param event event reference.
     */
    public void logoutBtnClicked(ActionEvent event) throws IOException {
        ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getLoginScreen());

    }

    /**
     * Transfers control to the ManagementController to change to the Management
     * scene.
     */
    public void managementBtnClicked() {
        ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getManagementScene());
    }

    /**
     * Transfers control to the OrderController as well as changes to the Orders
     * scene.
     *
     * @param event event reference.
     * @throws IOException thrown if OrderScreen.fxml can't be loaded.
     */
    public void orderBtnClicked(ActionEvent event) throws IOException {
        Parent orderParent = FXMLLoader.load(getClass().getResource("/fxml/OrderScreen.fxml"));
        Scene order = new Scene(orderParent);

        ControllerManager.getInstance().changeScene(order);
    }

    /**
     * Grabs the loaded transactions from the database and places it onto the
     * scene.
     *
     * @param t transcation reference.
     */
    public void addOrderToMainScreen(Transaction t) {
        HBox transactionContainer = new HBox(10);
        Label itemName = new Label("Transaction #" + t.getTransactionID() + "");
        Label itemPrice = new Label("$" + String.format("%.2f", t.getFinalPrice()) + "");

        transactionContainer.setMinWidth(563);
        transactionContainer.setMinHeight(45);
        transactionContainer.setPrefSize(563, 45);

        itemName.setMinHeight(45);
        itemName.setMinWidth(410);

        itemPrice.setMinHeight(45);
        itemPrice.setMinWidth(112);

        itemName.setTextFill(Color.web("#D3D1D1"));
        itemPrice.setTextFill(Color.web("#D3D1D1"));

        itemName.setFont(new Font("Arial Black", 25));
        itemPrice.setFont(new Font("Arial Black", 25));

        transactionContainer.getChildren().addAll(itemName, itemPrice);
        mainScreenDisplay.getChildren().addAll(transactionContainer);
    }

    /**
     * Opens a pop-up that allows the modification of certain settings like
     * password and timeout.
     */
    public void settingsBtnClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsPopup.fxml"));
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);
        try {
            SettingsController controller = new SettingsController();
            loader.setController(controller);
            popup.getContent().add((Parent) loader.load());
            popup.show(ControllerManager.getInstance().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
