package gui.mainform;


import functionality.Inventory;
import functionality.Part;
import functionality.Product;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**RUNTIME or LOGICAL ERRORS didn't occur here.*/
public class MainForm_controller implements Initializable {

    private static Stage stage;

    /** Bind table with Inventory List for Parts*/
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

    /** Table for Products*/

    @FXML
    private TableView <Product> ProductTable; //Product table
    @FXML
    private TableColumn <Product, Integer> ProdId;
    @FXML
    private TableColumn<Product, String> ProdName;
    @FXML
    private TableColumn<Product, Integer> ProdInv;
    @FXML
    private TableColumn<Product, Double> ProdPrice;

    @FXML
    public Text ErrorHolder; //Hold errors
    @FXML
    public TextField SearchByIDField; // holds id
    @FXML
    public TextField productSearchField;
    public static Part selectedPart;
    public static Product selectedProduct;

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

        //Populate Product table with Parts from Inventory

        ProductTable.setItems(Inventory.getAllProducts());
        ProdId.setCellValueFactory(data -> data.getValue().getIntId().asObject());
        ProdName.setCellValueFactory(data -> data.getValue().getStringName());
        ProdPrice.setCellValueFactory(data -> data.getValue().getDoublePropertyPrice().asObject());
        ProdInv.setCellValueFactory(data -> data.getValue().getIntStock().asObject());

    }

    /**PART TABLE HANDLE*/


    /**Handle search by Part ID*/
    public void SearchByID(KeyEvent keyEvent) {
        try{
            if(keyEvent.getCode().toString().equals("ENTER")){
                /**Is search by Index*/
                if(SearchByIDField.getText().matches("[0-9]+")){
                    // Assign new created List to table
                    Part LookedPart = Inventory.lookupPart(Integer.parseInt(SearchByIDField.getText()));
                    if(LookedPart == null){
                        ErrorHolder.setText("No matching parts were found");
                    }
                    else{
                        ObservableList<Part> matchingList = FXCollections.observableArrayList();
                        matchingList.add(LookedPart);
                        PartTable.setItems(matchingList);
                    }
                }
                else{
                    /**Search by Name*/
                    ObservableList<Part> matchingList = Inventory.lookupPart(SearchByIDField.getText());
                    PartTable.setItems(matchingList);
                }

            }
            else if(SearchByIDField.getText().equals("")){
                PartTable.setItems(Inventory.getAllParts());
            }
        }
        catch (Exception e){
            if(SearchByIDField.getText().equals("")){
                ErrorHolder.setText("ID/Name field is empty");
            }
            else if(Inventory.getAllParts().size() == 0){
                ErrorHolder.setText("Table is empty!");

            }
        }
    }


    /**Open Add Part Form*/
    public void handleAddPartClick(MouseEvent mouseEvent) {
        ErrorHolder.setText("");
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
        ErrorHolder.setText("");
        /**Get  values from Selected Record*/
        try{
            /*Set up a selected item*/
            selectedPart = PartTable.getSelectionModel().getSelectedItem();
            if(selectedPart == null){
                ErrorHolder.setText("Nothing is selected");
            }
            else{
                /**Load new FXML */
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/modifyPart.fxml"));
                stage = new Stage();
                stage.setTitle("Modify Part");
                Parent root = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(root));
                stage.show();
            }


        }
        catch(IOException err){

            throw new RuntimeException(err);
        }
    }

    /**Delete part*/
    public void handleDeletePart(MouseEvent mouseEvent) {
        ErrorHolder.setText("");

        if(Inventory.getAllParts().size() == 0){
            ErrorHolder.setText("No parts to delete");
        }
        else{
            selectedPart = PartTable.getSelectionModel().getSelectedItem();
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
        ErrorHolder.setText("");
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
        ErrorHolder.setText("");
        /*Get selected TableView Field*/


        try{
            /**Get  values from Selected Record*/
            selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
            if(selectedProduct == null){
                ErrorHolder.setText("Nothing is selected");
            }
            else{
                /**Load new FXML */
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/modifyProduct.fxml"));
                stage = new Stage();
                stage.setTitle("Modify Product");
                Parent root = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(root));
                stage.show();
            }


        }
        catch(IOException err){
            throw new RuntimeException(err);
        }


    }

    /**Delete Product*/

    public void handleProductDeletion(MouseEvent mouseEvent){
        ErrorHolder.setText("");
        /*Get selected TableView Field*/
        /**Get  values from Selected Record*/
        if(Inventory.getAllProducts().size() == 0){
            ErrorHolder.setText("No products to delete");
        }
        else{
            selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
            try{
                if(selectedProduct.getAllAssociatedParts().size() > 0){
                    ErrorHolder.setText("Can't delete Product that have attached parts. Remove all parts and try again.");
                }
                else{
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
                        Inventory.deleteProduct(selectedProduct);
                    }
                    System.out.println("Deleted: " + selectedProduct.getName());
                }
            }
            catch (Exception e){
                ErrorHolder.setText("Can't delete Product" + e.getMessage());
            }
        }


    }

    //Search Product by ID/Name

    public void SearchProduct(KeyEvent keyEvent) {
        try{
            if(keyEvent.getCode().toString().equals("ENTER")){
                /**Is search by Index*/
                if(productSearchField.getText().matches("[0-9]+")){
                    // Assign new created List to table
                    Product LookedProduct = Inventory.lookupProduct(Integer.parseInt(productSearchField.getText()));
                    if(LookedProduct != null){
                        ObservableList<Product> matchingList = FXCollections.observableArrayList();
                        matchingList.add(LookedProduct);
                        ProductTable.setItems(matchingList);
                    }
                    else{
                        ErrorHolder.setText("No matching products were found");
                    }
                }
                else{
                    /**Search by Name*/
                    ObservableList<Product> matchingList = Inventory.lookupProduct(productSearchField.getText());
                    ProductTable.setItems(matchingList);
                }

            }
            else if(SearchByIDField.getText().equals("")){
                PartTable.setItems(Inventory.getAllParts());
            }
        }
        catch (Exception e){

            if(SearchByIDField.getText().equals("")){
                ErrorHolder.setText("ID/Name field is empty");
            }
            else if(Inventory.getAllProducts().size() == 0){
                ErrorHolder.setText("Table is empty!");
            }
        }
    }


    /**Close entire Application*/
    public void ExitClickHandle(MouseEvent mouseEvent) {

        Platform.exit();
    }



}
