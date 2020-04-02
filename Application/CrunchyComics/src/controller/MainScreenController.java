package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Noris. UMM I MEAN: CAPSTONE GROUP, OF COURSE, TIS NOT MY WORK BUT OUR
 *         WORK.
 * 
 * @Notes Please make sure to correct this code. Namely: Password as well as
 *        validation.
 * 
 */
public class MainScreenController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    /**
     * This method is called by clicking "Logout" and will redirect user to login
     * screen
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
     * This method is called when user clicks on the "Order Entry" button on the
     * main screen, this button will redirect you to the order screen.
     */
    public void orderBtnClicked(ActionEvent event) throws IOException {
        Parent orderParent = FXMLLoader.load(getClass().getResource("/fxml/OrderScreen.fxml"));
        Scene order = new Scene(orderParent);

        // This line grabs the Stage information
        Stage orderWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        orderWindow.setScene(order);
        orderWindow.show();
    }
}