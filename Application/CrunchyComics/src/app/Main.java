package app;

import controller.SplashController;
import java.time.Duration;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import manager.DatabaseManager;

/**
 * Main- Starting point for the Crunchy Comics UI
 *
 * @author Jatin Noris Buriac
 * 
 * @date Feb 03, 2020
 */
public class Main extends Application {

    public static void main(String[] args) throws Exception {

        //TODO: Create a splash screen.
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
//        Scene loginScene = new Scene(root);
//
//        stage.setScene(loginScene);
//        stage.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SplashScreen.fxml"));
        Parent root = loader.load();
        SplashController controller = (SplashController) loader.getController();
        Scene splashScene = new Scene(root);

        stage.setOnShown((WindowEvent event) -> {
            controller.initializeApp();
        });

        stage.setScene(splashScene);
        stage.show();

    }
}
