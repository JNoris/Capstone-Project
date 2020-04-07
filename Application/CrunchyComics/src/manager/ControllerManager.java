/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius Smith
 */
public class ControllerManager {

    private static ControllerManager cManager = null;
    private Stage window;
    private Scene mainScreen;
    private Scene loginScreen;
    private Scene managementScreen;
    private Scene lastScene;
    private Popup popup; //Only one popup can be open at any time.

    private ControllerManager() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));
            this.mainScreen = new Scene((Parent) loader.load());
            this.mainScreen.setUserData(loader);
            this.lastScene = mainScreen; //Defaults lastScene to the MainScreen
            
            loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            this.loginScreen = new Scene((Parent) loader.load());
            
            loader = new FXMLLoader(getClass().getResource("/fxml/Management.fxml"));
            this.managementScreen = new Scene((Parent) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static ControllerManager getInstance() {
        if (cManager == null) {
            cManager = new ControllerManager();
            return cManager;

        } else {
            return cManager;
        }
    }

    public void changeScene(Scene scene) {
        window.setScene(scene);
        if (scene != loginScreen) {
            lastScene = scene;
        }
        if(scene == mainScreen){
            popup = null;
        }
    }

    public Scene getMainScreen() {
        return mainScreen;
    }

    public Scene getLoginScreen() {
        return loginScreen;
    }
    
    public Scene getLastScene(){
        return lastScene;
    }
    
    public Scene getManagementScene(){
        return managementScreen;
    }

    public Stage getWindow() {
        return this.window;
    }
    
    public Popup getPopup(){
        return popup;
    }
    public void setPopup(Popup popup){
        this.popup = popup;
    }
    public void setWindow(Stage window) {
        this.window = window;
    }
    
    public void hidePopup(){
        if(popup != null){
            popup.hide();
        }
    }
    
    public void restorePopup(){
        if(popup != null){
            popup.show(window);
        }
    }
}
