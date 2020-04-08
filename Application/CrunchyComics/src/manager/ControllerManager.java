package manager;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * This class contains a reference to all to most scenes used in the
 * application. Makes it easier to change between scenes also manages pop-ups
 * used throughout the application life cycle.
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

    /**
     * Default constructor. Loads all the common scenes for later use.
     */
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

    /**
     * Gets the instance of the ControllerManager. Creates one if it doesn't
     * exist.
     *
     * @return ControllerManager singleton object.
     */
    public static ControllerManager getInstance() {
        if (cManager == null) {
            cManager = new ControllerManager();
            return cManager;

        } else {
            return cManager;
        }
    }

    /**
     * Changes to a new scene. Before changing scenes the this object will close
     * an open pop-up if it exists.
     *
     * @param scene target scene.
     */
    public void changeScene(Scene scene) {
        window.setScene(scene);
        hidePopup();
        if (scene != loginScreen) {
            lastScene = scene;
        }
        if (scene == mainScreen) {
            popup = null;
        }
    }

    /**
     * Gets the main screen.
     *
     * @return the main screen.
     */
    public Scene getMainScreen() {
        return mainScreen;
    }

    /**
     * Gets the login screen.
     *
     * @return the login screen.
     */
    public Scene getLoginScreen() {
        return loginScreen;
    }

    /**
     * Gets the last scene.
     *
     * @return the last scene.
     */
    public Scene getLastScene() {
        return lastScene;
    }

    /**
     * Gets the management scene.
     *
     * @return the management scene.
     */
    public Scene getManagementScene() {
        return managementScreen;
    }

    /**
     * Gets the window.
     *
     * @return the window.
     */
    public Stage getWindow() {
        return this.window;
    }

    /**
     * Gets the current pop-up. May return null.
     *
     * @return the current or last active pop-up.
     */
    public Popup getPopup() {
        return popup;
    }

    /**
     * Sets the active pop-up. Hides the previous opened pop-up.
     *
     * @param popup the target pop-up.
     */
    public void setPopup(Popup popup) {
        if (this.popup != null) {
            hidePopup();
        }
        this.popup = popup;
    }

    /**
     * Sets the window.
     *
     * @param window the new window.
     */
    public void setWindow(Stage window) {
        this.window = window;
    }

    /**
     * If there is a pop-up it will hide it.
     */
    public void hidePopup() {
        if (popup != null) {
            popup.hide();
        }
    }

    /**
     * Shows the previous opened pop-up.
     */
    public void restorePopup() {
        if (popup != null) {
            popup.show(window);
        }
    }
}
