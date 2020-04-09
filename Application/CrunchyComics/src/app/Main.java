package app;

import controller.SplashController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import manager.ControllerManager;

/**
 * Main - Starting point for the Crunchy Comics UI
 *
 * @author Jatin Noris Buriac
 */
public class Main extends Application {

    /**
     * Executes the application.
     * @param args arguments.
     * @throws Exception in case something goes really bad.
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * Loads and shows the initial scene.
     *
     * @param stage windows reference.
     * @throws Exception also in case something goes really bad.
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SplashScreen.fxml"));
        Parent root = loader.load();
        SplashController controller = (SplashController) loader.getController();
        Scene splashScene = new Scene(root);

        stage.setOnShown((WindowEvent event) -> {
            controller.initializeApp();
        });

        stage.setScene(splashScene);
        stage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        stage.show();
        ControllerManager.getInstance().setWindow(stage);

    }
}
