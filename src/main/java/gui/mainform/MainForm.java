package gui.mainform;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainForm extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

Parent root = FXMLLoader.load(getClass().getResource("/main_form.fxml"));

/**Loading FXML and setting Scene for UI*/

    primaryStage.setTitle("Inventory Management System");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

    }
}
