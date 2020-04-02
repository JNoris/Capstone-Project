package controller;

import javafx.scene.input.*;
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
import javafx.scene.paint.Color;
import javafx.event.*;

/**
 *
 * @author Noris.
 * 
 * @Notes This is probably the most incomplete GUI as of Feb 17th. Change the
 *        font to Arial Black, set a divider between tabs and the content they
 *        show, etc.
 * @Events will have to be handled. My main question is is in the interaction
 *         model we have things like the Edit button and all that, isn't this
 *         repetitive? We already have Add/Edit Inventory on the left side.
 * 
 */
public class ManagementController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /***
     * 
     * Redirects user to Main Screen when user clicks "Home".
     * 
     * @param event
     * @throws IOException
     */
    public void homeBtnClicked(ActionEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/fxml/MainScreen.fxml"));
        Scene main = new Scene(mainScreenParent);

        // This line grabs the Stage information
        Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        mainScreenWindow.setScene(main);
        mainScreenWindow.show();
    }

}