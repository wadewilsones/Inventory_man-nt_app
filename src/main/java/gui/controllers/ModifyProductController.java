package gui.controllers;

import functionality.Inventory;
import functionality.Part;
import functionality.Product;
import functionality.Validation;
import gui.mainform.MainForm_controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static gui.mainform.MainForm_controller.selectedPart;
import static gui.mainform.MainForm_controller.selectedProduct;

public class ModifyProductController implements Initializable {

    /**Input values*/

    @FXML
    public TextField GeneratedID;

    @FXML
    public TextField name;
    @FXML
    public TextField inv;
    @FXML
    public TextField price;
    @FXML
    public TextField max;
    @FXML
    public TextField min;

    /** Bind table with Part List in Add Product From*/
    @FXML
    private TableView<Part> PartTable; //Part table
    @FXML
    private TableColumn<Part, Integer> PartId;
    @FXML
    private TableColumn<Part, String> PartName;
    @FXML
    private TableColumn<Part, Integer> PartInv;
    @FXML
    private TableColumn<Part, Double> PartPrice;

    /** Associated Parts*/
    public Part selectedPartForAssociation;
    @FXML
    private TableView<Part> AssociatedPartTable; //Part table
    @FXML
    private TableColumn<Part, Integer> AssociatedPartId;
    @FXML
    private TableColumn<Part, String> AssociatedPartName;
    @FXML
    private TableColumn<Part, Integer> AssociatedPartInv;
    @FXML
    private TableColumn<Part, Double> AssociatedPartPrice;

    /*Error display*/

    @FXML
    public Text ErrorHolder;

    /*Display initial values for text fields*/

    @Override
    public void initialize(URL location, ResourceBundle resources){
        /**Get values from Inventory*/
        name.setText(selectedProduct.getName());
        GeneratedID.setText(String.valueOf(selectedProduct.getId()));
        inv.setText(String.valueOf(selectedProduct.getStock()));
        price.setText(String.valueOf(selectedProduct.getPrice()));
        min.setText(String.valueOf(selectedProduct.getMin()));
        max.setText(String.valueOf(selectedProduct.getMax()));

        /**Fill out the table with Part data*/
        //Populate Part table with Parts from Inventory
        PartTable.setItems(Inventory.getAllParts());
        PartId.setCellValueFactory(data -> data.getValue().getIntId().asObject());
        PartName.setCellValueFactory(data -> data.getValue().getStringName());
        PartPrice.setCellValueFactory(data -> data.getValue().getDoublePropertyPrice().asObject());
        PartInv.setCellValueFactory(data -> data.getValue().getIntStock().asObject());

        /**Display associated parts*/
            AssociatedPartTable.setItems(selectedProduct.getAllAssociatedParts());
            System.out.println("Size of part row: " + selectedProduct.getAllAssociatedParts().size());
            //Display rows
            AssociatedPartId.setCellValueFactory(data -> data.getValue().getIntId().asObject());
            AssociatedPartName.setCellValueFactory(data -> data.getValue().getStringName());
            AssociatedPartPrice.setCellValueFactory(data -> data.getValue().getDoublePropertyPrice().asObject());
            AssociatedPartInv.setCellValueFactory(data -> data.getValue().getIntStock().asObject());

    }

    //Attach Part to Product

    public void handlePartAttach(MouseEvent mouseEvent) {
        ErrorHolder.setText(""); // clear any errors if user try again
        try{
            // get selected part
            Part selectedPartForAssociation = PartTable.getSelectionModel().getSelectedItem();
            // Create new product
            selectedProduct.addAssociatedPart(selectedPartForAssociation);
            // add to list of associated parts
            AssociatedPartTable.setItems(selectedProduct.getAllAssociatedParts());
            //Display rows
            AssociatedPartId.setCellValueFactory(data -> data.getValue().getIntId().asObject());
            AssociatedPartName.setCellValueFactory(data -> data.getValue().getStringName());
            AssociatedPartPrice.setCellValueFactory(data -> data.getValue().getDoublePropertyPrice().asObject());
            AssociatedPartInv.setCellValueFactory(data -> data.getValue().getIntStock().asObject());}
        catch (Exception e){
            ErrorHolder.setText("Error!Part was not attached! Make sure to fill all fields for Product!");
        }
    }
//Remove attached part
    public void handleRemoveAssociatedPart(MouseEvent mouseEvent) {
        ErrorHolder.setText(""); // clear any errors if user try again
        if (selectedProduct.getAllAssociatedParts().size() == 0) {
            ErrorHolder.setText("There is nothing to remove");
        }
        else {
            try {
                /*Create Dialog for asking confirmation*/
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Confirm Deletion");
                ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.setContentText("Confirmation of Removal");
                dialog.getDialogPane().getButtonTypes().add(confirm);
                dialog.getDialogPane().getButtonTypes().add(cancel);
                Optional<ButtonType> result = dialog.showAndWait();
                if (result.get() == confirm) {
                    //Delete Record
                    selectedPartForAssociation = AssociatedPartTable.getSelectionModel().getSelectedItem();
                    selectedProduct.deleteAssociatedPart(selectedPartForAssociation);
                    System.out.println("Delete this:" + selectedPartForAssociation.getName());
                }

            } catch (Exception err) {
                //System.out.println(err);
                ErrorHolder.setText("Deletion is failed! Try again!");
            }
        }
    }

    //Save modification
    public void handleFormSubmission(MouseEvent mouseEvent) {
        try{
            //Ask for confirmation
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirm  Modifications");
            ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.setContentText("Confirm Update?");
            dialog.getDialogPane().getButtonTypes().add(confirm);
            dialog.getDialogPane().getButtonTypes().add(cancel);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == confirm) {
               /**Submit modifications*/
                //Validate
                Validation validateInput = new Validation(name.getText(), price.getText(), inv.getText(), min.getText(), max.getText(), "none");
                if(validateInput.getValidationValue()){
                    System.out.println("After validation");
                    /*Converting input to right type*/
                    double priceConverted = Double.parseDouble(price.getText());
                    int invConverted = Integer.parseInt(inv.getText());
                    int minConverted = Integer.parseInt(min.getText());
                    int maxConverted = Integer.parseInt(max.getText());
                    //Populate selected product with new data
                   selectedProduct.setName(name.getText());
                    selectedProduct.setPrice(priceConverted);
                    selectedProduct.setMin(minConverted);
                    selectedProduct.setMax(maxConverted);
                    selectedProduct.setStock(invConverted);
                    Inventory.updateProduct(selectedProduct.getId()-1, selectedProduct);
                    System.out.println("New name of product:"+ selectedProduct.getName());//r
                    MainForm_controller.getStage().close(); // close  modification window
                }
                else{
                    ErrorHolder.setText("Wrong inout, please check entered information");
                }
            }

        }
        catch(Exception e){
            ErrorHolder.setText("Can't update Product! Error: " + e.getMessage());
        }
    }
//Cancel modifications
    public void handleCancelClick(MouseEvent mouseEvent) {
        try {
            /*Create Dialog for asking confirmation*/
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirm Canceling Modifications");
            ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.setContentText("Are you sure you want to cancel?");
            dialog.getDialogPane().getButtonTypes().add(confirm);
            dialog.getDialogPane().getButtonTypes().add(cancel);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == confirm) {
                MainForm_controller.getStage().close();
            }

    }
        catch (Exception err) {
            //System.out.println(err);
            ErrorHolder.setText("Can't close window.");
        }
}
}
