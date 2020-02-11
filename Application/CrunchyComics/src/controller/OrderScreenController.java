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
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class OrderScreenController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

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
     * When TextField searchField is initiated (by clicking on the search field in
     * the order screen), whatever is typed in the field will call
     * textfield.getText(); which grabs the fields input and outputs query
     * underneath in the results pane.
     * 
     * @param event
     * @throws IOException
     */
    public void initiateSearch(ActionEvent event) throws IOException {
        // searchField is the fxid

    }
}