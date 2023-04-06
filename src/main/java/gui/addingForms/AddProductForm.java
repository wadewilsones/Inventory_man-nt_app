package gui.addingForms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddProductForm extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/addProduct.fxml"));

/**Loading FXML and setting Scene for UI*/

        primaryStage.setTitle("Add Product");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
