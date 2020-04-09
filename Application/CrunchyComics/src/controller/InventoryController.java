package controller;

import javafx.scene.input.*; // For dealing with input
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.scene.paint.Color; // Good for dealing with error messages and setting it to red if somethings wrong, refer to LoginController for more details
import javafx.event.*;

/**
 * This creates the controller for the Inventory Stage.
 * 
 */
public class InventoryController implements Initializable {

    @FXML
    private ComboBox comboBox;
    
    /**
     * This method loads any resources before the startup of this stage.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * comboBox to differentiate between Food & Books - only partially implemented.
         */
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("Book", "Food", "Other");
    }

    /***
     * 
     * Redirects user to Main Screen when user clicks "Home".
     * 
     * @Notes Is it supposed to be Main Screen? Or is it supposed to redirect to
     *        management screen? Someone clear this up.
     * 
     * @param event
     * @throws IOException
     */
    public void homeBtnClicked(ActionEvent event) throws IOException {
        Parent homeScreenParent = FXMLLoader.load(getClass().getResource("/fxml/MainScreen.fxml")); // ("/fxml/Management.fxml")
        Scene home = new Scene(homeScreenParent);

        // This line grabs the Stage information
        Stage homeScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        homeScreenWindow.setScene(home);
        homeScreenWindow.show();
    }
}