package gui.mainform;

import functionality.Inventory;
import functionality.Part;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**RUNTIME or LOGICAL ERRORS didn't occur here.*/
public class MainForm_controller implements Initializable {

    @FXML
    private Button addPartBtn;
    private static Stage stage;

    /** Bind table with Inventory List*/
    @FXML
    private TableView <Part> PartTable; //Part table
    @FXML
    private TableColumn <Part, Integer> PartId;
    @FXML
    private TableColumn<Part, String> PartName;
    @FXML
    private TableColumn<Part, String> PartInv;
    @FXML
    private TableColumn<Part, String> PartPrice;

    /** Getter for stage*/
    public static Stage getStage() {
        return stage;
    }

    /**Open Add Part Form*/
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

    /**Open Modify Part Form*/
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

    /**Open Add Product Form*/
    public void handleAddProductClick(MouseEvent mouseEvent) {
        try{
            /**Load new FXML */
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addProduct.fxml"));
            stage = new Stage();
            stage.setTitle("Add Product");
            Parent root = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch(IOException err){
            throw new RuntimeException(err);
        }
    }

    /**Open Modify Product Form*/
    public void handleModifyProductClick(MouseEvent mouseEvent) {
        try{
            /**Load new FXML */
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addProduct.fxml"));
            stage = new Stage();
            stage.setTitle("Modify Product");
            Parent root = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch(IOException err){
            throw new RuntimeException(err);
        }
    }

    /**Close entire Application*/
    public void ExitClickHandle(MouseEvent mouseEvent) {

        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populate Part table with Parts from Inventory
        PartTable.setItems(Inventory.getAllParts());
        PartId.setCellValueFactory(data -> data.getValue().getId());
    }
}
