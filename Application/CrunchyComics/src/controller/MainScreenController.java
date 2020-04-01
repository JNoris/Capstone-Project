package controller;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    VBox mainScreenDisplay;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        addOrderToMainScreen();
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

    public void orderBtnClicked(ActionEvent event) throws IOException {
        Parent orderParent = FXMLLoader.load(getClass().getResource("/fxml/OrderScreen.fxml"));
        Scene order = new Scene(orderParent);

        // This line grabs the Stage information
        Stage orderWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        orderWindow.setScene(order);
        orderWindow.show();
    }
    
    public void addOrderToMainScreen()
    {
        HBox transactionContainer = new HBox(10);
        Label itemName = new Label("Ex");
        Label itemPrice = new Label("1");
        
        transactionContainer.setMinWidth(563);
        transactionContainer.setMinHeight(45);
        transactionContainer.setPrefSize(563, 45);
        
        itemName.setMinHeight(45);
        itemName.setMinWidth(439);
        
        itemPrice.setMinHeight(45);
        itemPrice.setMinWidth(112);
        
        itemName.setTextFill(Color.web("#D3D1D1"));
        itemPrice.setTextFill(Color.web("#D3D1D1"));

        itemName.setFont(new Font("Arial Black", 25));
        itemPrice.setFont(new Font("Arial Black", 25));
        
        
        
        
        transactionContainer.getChildren().addAll(itemName,itemPrice);
        mainScreenDisplay.getChildren().addAll(transactionContainer);
    }
}