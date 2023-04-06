package gui.addingForms;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AddPartForm extends Application {

    /**To handle RadioButton input used event*/
    @FXML
    private Label ChangingLabel;
    private final StringProperty text = new SimpleStringProperty();

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/addPart.fxml"));
        /**Initial value of the label for In-House field*/
        ChangingLabel.textProperty().bind(text);
        text.set("Test");

/**Loading FXML and setting Scene for UI*/

        primaryStage.setTitle("Add Part");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
}
