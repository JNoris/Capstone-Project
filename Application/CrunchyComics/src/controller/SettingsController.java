package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import manager.ControllerManager;
import utility.Settings;

/**
 * Creates the configuration for the application. Changes password and timeout.
 * @author Noris.
 */
public class SettingsController implements Initializable {
    
    @FXML
    private CheckBox timeoutCheckBox;
    @FXML
    private CheckBox passwordCheckBox;
    @FXML
    private TextField timeoutTimeTextField;
    @FXML
    private TextField newPasswordTextField;
    
    /**
     * Loads previous settings from the application.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        timeoutCheckBox.setSelected(Settings.getInstance().getTimerEnable());
        timeoutTimeTextField.setDisable(!timeoutCheckBox.isSelected());
        timeoutTimeTextField.setText(Settings.getInstance().getTimeoutTimer() / 1000 + "");
    }
    
    /**
     * Validates correct user input.
     */
    public void confirmBtnClicked() {
        Settings.getInstance().setTimerEnable(timeoutCheckBox.isSelected());
        if (timeoutCheckBox.isSelected()) {
            if (timeoutTimeTextField.getText().isEmpty()) {
                timeoutTimeTextField.requestFocus();
                return;
            }
            Settings.getInstance().setTimeoutTimer(Integer.parseInt(timeoutTimeTextField.getText()) * 1000);
        }
        if (passwordCheckBox.isSelected()) {
            if (newPasswordTextField.getText().isEmpty()) {
                newPasswordTextField.requestFocus();
                return;
            }
            Settings.getInstance().setPassword(newPasswordTextField.getText());
        }
        
        Settings.saveSettings();
        ControllerManager.getInstance().hidePopup();
    }
    
    /**
     * Validates incorrect user input and allows for a reset of the task.
     */
    public void cancelBtnClicked() {
        ControllerManager.getInstance().hidePopup();
    }
    
    /**
     * Allows the user to change the timeout time.
     */
    public void enableTimeoutClicked() {
        if (timeoutCheckBox.isSelected()) {
            timeoutTimeTextField.setDisable(false);
        } else {
            timeoutTimeTextField.setDisable(true);
        }
    }
    
    /**
     * Allows the user to change password.
     */
    public void enablePasswordClicked() {
        if (passwordCheckBox.isSelected()) {
            newPasswordTextField.setDisable(false);
        } else {
            newPasswordTextField.setDisable(true);
        }
    }
}
