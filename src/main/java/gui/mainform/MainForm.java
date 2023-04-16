package gui.mainform;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This is a Main program starter
 * Path to Javadoc files: inventory_magnt/javadoc
 *
 * There were a few runtime Exceptions, that were connected with NullPointers.
 * Search for the product did cause the exception because the method getAllProducts() was used on a null Product object.
 * Was fixed by initialization of the product before call methods.
 *
 *
 * For future program enhancement, binding it with a database to save all entered data was recommended.
 * */
public class MainForm extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/main_form.fxml"));

/**
 * Loading FXML and setting Scene for UI
 * */

    primaryStage.setTitle("Inventory Management System");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

    }
}
