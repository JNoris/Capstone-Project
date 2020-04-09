package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import manager.ControllerManager;
import manager.DatabaseManager;
import utility.Settings;

/**
 * The Initial Loading of the Database before the application GUI is present.
 *
 * @author Vinicius
 */
public class SplashController {

    @FXML
    private BorderPane mainPane;

    /**
     * Creates connection with database and calls the first scene.
     */
    public void initializeApp() {
        //Create a database connection.
        System.out.println("Connecting to database...");
        DatabaseManager.getInstance();
        System.out.println("Connecting to database... done.");
        //Show login screen
        Stage stage = (Stage) mainPane.getScene().getWindow();

        Settings.getInstance();

        stage.setScene(ControllerManager.getInstance().getLoginScreen());
        stage.show();
        stage.centerOnScreen();
    }
}
