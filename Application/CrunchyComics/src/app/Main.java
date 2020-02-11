package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Main- Starting point for the Crunchy Comics UI
 *
 * @author Jatin Noris Buriac
 * @date February 3, 2020
 */
public class Main extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene loginScene = new Scene(root);

        stage.setScene(loginScene);
        stage.show();
    }
}