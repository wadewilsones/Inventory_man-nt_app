package gui.mainform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainForm_controller {

    @FXML
    private Button addPartBtn;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    /**Change Screen to Add Part*/
    public void handleAddPartClick(MouseEvent mouseEvent) {

        try{
            /**Load new FXML */
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addPart.fxml"));
            stage = new Stage();
            stage.setTitle("Add Part");
            Parent root = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch(IOException err){
            throw new RuntimeException(err);
        }

    }

    /**Change Screen to Modify Part*/
    public void handleModifyPartClick(MouseEvent mouseEvent) {
        try{
            /**Load new FXML */
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addPart.fxml"));
            stage = new Stage();
            stage.setTitle("Modify Part");
            Parent root = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch(IOException err){
            throw new RuntimeException(err);
        }
    }
}
