package gui.mainform;

import functionality.InHouse;
import functionality.Inventory;
import functionality.Outsourced;
import functionality.Part;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private TableColumn<Part, Integer> PartInv;
    @FXML
    private TableColumn<Part, Double> PartPrice;

    @FXML
    public Text ErrorHolder;
    public static Part selectedPart;



    /** Getter for stage*/
    public static Stage getStage() {
        return stage;
    }

    /**Initial Population*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populate Part table with Parts from Inventory
        PartTable.setItems(Inventory.getAllParts());
        PartId.setCellValueFactory(data -> data.getValue().getIntId().asObject());
        PartName.setCellValueFactory(data -> data.getValue().getStringName());
        PartPrice.setCellValueFactory(data -> data.getValue().getDoublePropertyPrice().asObject());
        PartInv.setCellValueFactory(data -> data.getValue().getIntStock().asObject());
    }


    /**PART TABLE HANDLE*/

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

        /**Get  values from Selected Record*/
        selectedPart = PartTable.getSelectionModel().getSelectedItem();

        try{
            /**Load new FXML */
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/modifyPart.fxml"));
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

    /**Delete part*/


    public void handleDeletePart(MouseEvent mouseEvent) {

        selectedPart = PartTable.getSelectionModel().getSelectedItem();
        if(Inventory.getAllParts().size() == 0){
            ErrorHolder.setText("Inventory is empty");
        }
        else{
            try{
                /*Create Dialog*/
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Confirm Deletion");
                ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.setContentText("Confirmation of Deletion");
                dialog.getDialogPane().getButtonTypes().add(confirm);
                dialog.getDialogPane().getButtonTypes().add(cancel);
                Optional<ButtonType> result = dialog.showAndWait();
                if(result.get() == confirm){
                    //Delete Record
                    Inventory.deletePart(selectedPart);
                }

            }
            catch (Exception err){
                System.out.println(err);
                ErrorHolder.setText("Deletion is failed! Try again!");
            }

        }


    }

    /**PRODUCT TABLE HANDLE*/

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

        /*Get selected TableView Field*/

        try{
            /**Load new FXML */
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/modifyProduct.fxml"));
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



}
