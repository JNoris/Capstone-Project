package app;

import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
// import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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