package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import manager.ControllerManager;
import utility.Settings;
import utility.Timer;

/**
 * Creates the configuration for the application. Changes password and timeout.
 *
 * @author Noris
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
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeoutCheckBox.setSelected(Settings.getInstance().getTimerEnable());
        timeoutTimeTextField.setDisable(!timeoutCheckBox.isSelected());
        timeoutTimeTextField.setText(Settings.getInstance().getTimeoutTimer() / 1000 + "");

        timeoutTimeTextField.setOnKeyTyped(e -> {
            if (!e.getCharacter().matches("[0-9]")) {
                Timer.resetTimer();
                e.consume();
            }
        });
    }

    /**
     * Validates correct user input. Saves the new settings to a file.
     */
    public void confirmBtnClicked() {
        Timer.resetTimer();
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
     * Cancels the action and closes the pop-up.
     */
    public void cancelBtnClicked() {
        Timer.resetTimer();
        ControllerManager.getInstance().hidePopup();
    }

    /**
     * Allows the user to enable or disable the timeout feature.
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
