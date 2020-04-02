/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import manager.DatabaseManager;

/**
 *
 * @author 695553
 */
public class SplashController {

    @FXML
    private BorderPane mainPane;

    public void initializeApp() {
//Create a database connection.
        System.out.println("Connecting to database...");
        DatabaseManager.getInstance();
        System.out.println("Connecting to database... done.");
        //Show login screen
        try {
            Parent loginScreen = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            Stage stage = (Stage) mainPane.getScene().getWindow();
            
            stage.setScene(new Scene(loginScreen));
            stage.show();
        } catch (IOException e) {
            System.err.println("Login FXML not found! Error: " + e.getMessage());
            System.exit(0);
        }
    }
}
