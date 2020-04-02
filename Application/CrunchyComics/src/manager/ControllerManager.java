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

    private ControllerManager() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));
            this.mainScreen = new Scene((Parent)loader.load());
            this.mainScreen.setUserData(loader);
            
            loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            this.loginScreen = new Scene((Parent)loader.load(getClass().getResource("/fxml/Login.fxml")));
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
    
    public Scene getMainScreen(){
        return mainScreen;
    }
    
    public Scene getLoginScreen(){
        return loginScreen;
    }
    
    public Stage getWindow(){
        return this.window;
    }
    
    public void setWindow(Stage window){
        this.window = window;
    }
}
