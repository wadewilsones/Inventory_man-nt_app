package gui.addingForms;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddPartForm extends Application {

    /**To handle RadioButton input used event*/
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/addPart.fxml"));

/**Loading FXML and setting Scene for UI*/

        primaryStage.setTitle("Add Part");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
}
