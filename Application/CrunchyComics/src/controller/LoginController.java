package controller;

import javafx.scene.input.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utility.LoginService;
import javafx.fxml.*;
import javafx.scene.paint.Color;
import javafx.event.*;
import manager.ControllerManager;
import utility.Timer;

/**
 * Controls the initial security for the login scene.
 */
public class LoginController implements Initializable {

    @FXML
    private Label errorMessage = new Label("");
    @FXML
    private PasswordField passwordFieldInput;

    @FXML
    private Button btnLogin;
    
    /**
     * Loads the password fields and event handlers for the login scene.
     * @param location 
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passwordFieldInput.setOnKeyPressed(new javafx.event.EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyboardEnter) {
                if (keyboardEnter.getCode() == KeyCode.ENTER) {
                    loginEnterBtnClicked(new ActionEvent(btnLogin, null));
                }
            }
        });
    }

    /**
     * When this method is called by clicking "Enter" in Login (assuming correct
     * credentials inputted), it will change the Scene to Main Screen.
     *
     * @param event event reference.
     */
    public void loginEnterBtnClicked(ActionEvent event) {
        try {

            LoginService ls = new LoginService();

            if (ls.checkPassword(passwordFieldInput.getText())) {

                ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getLastScene());
                ControllerManager.getInstance().restorePopup();
                Timer.createTimer();
                //Reset error message.
                errorMessage.setText("");

            } else {
                errorMessage.setText("Your password is incorrect.");
                errorMessage.setTextFill(Color.rgb(210, 39, 30));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        passwordFieldInput.clear();
    }
}
