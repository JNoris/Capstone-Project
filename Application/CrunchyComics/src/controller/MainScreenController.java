package controller;

import broker.TransactionBroker;
import domain.Transaction;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import manager.ControllerManager;
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
public class MainScreenController implements Initializable {

    @FXML
    private VBox mainScreenDisplay;
    @FXML
    private Label currentTimeLabel;

    private List<Transaction> transactions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing MainScreenController");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        currentTimeLabel.setText(format.format(new Date()));
        showTransactions();
    }

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
     * login screen
     */
    public void logoutBtnClicked(ActionEvent event) throws IOException {
//        Parent loginParent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
//        Scene logout = new Scene(loginParent);
//        Scene logout = ControllerManager.getInstance().getLoginScreen();
//
//        // This line grabs the Stage information
//        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        loginWindow.setScene(logout);
//        loginWindow.show();

        ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getLoginScreen());

    }

    public void managementBtnClicked() {
        ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getManagementScene());
    }

    public void orderBtnClicked(ActionEvent event) throws IOException {
        Parent orderParent = FXMLLoader.load(getClass().getResource("/fxml/OrderScreen.fxml"));
        Scene order = new Scene(orderParent);

        // This line grabs the Stage information
//        Stage orderWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerManager.getInstance().changeScene(order);
//        orderWindow.setScene(order);
//        orderWindow.show();
    }

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

    public void settingsBtnClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsPopup.fxml"));
        Popup popup = new Popup();
        ControllerManager.getInstance().setPopup(popup);
        try {
//            itemPopup.getContent().add((Parent) loader.load());
//            itemPopup.show(ControllerManager.getInstance().getMainScreen().getWindow());
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
