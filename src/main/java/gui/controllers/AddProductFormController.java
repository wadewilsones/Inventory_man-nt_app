package gui.controllers;

import functionality.*;
import gui.mainform.MainForm_controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static gui.mainform.MainForm_controller.selectedProduct;

/**RUNTIME or LOGICAL ERRORS didn't occur here.*/

public class AddProductFormController implements Initializable{

    /*For generating ID*/
    @FXML
    public TextField GeneratedID;
    static public int generatedIdValue;

    @FXML
    public TextField SearchByIDField; // search Parts

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

    public Part selectedPartForAssociation;

    public Product newProduct;
    @FXML
    public Text ErrorHolder;

    /**Input values*/

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


    /**Handle screens of Modify and Add*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*Setting up initial value for ID*/
        generatedIdValue += 1;
        String convertedValue = Integer.toString(generatedIdValue);
        GeneratedID.setText(convertedValue);

        /**Fill out the table with Part data*/
        //Populate Part table with Parts from Inventory
        PartTable.setItems(Inventory.getAllParts());
        PartId.setCellValueFactory(data -> data.getValue().getIntId().asObject());
        PartName.setCellValueFactory(data -> data.getValue().getStringName());
        PartPrice.setCellValueFactory(data -> data.getValue().getDoublePropertyPrice().asObject());
        PartInv.setCellValueFactory(data -> data.getValue().getIntStock().asObject());

    }
    /**Close opened window*/
    public void handleCancelClick(MouseEvent mouseEvent) {

        MainForm_controller.getStage().close();
    }

/** Handle adding product*/
    public void handleFormSubmission(MouseEvent mouseEvent) {
        ErrorHolder.setText(""); // clear any errors if user try again
        try {
            /*Get input and Validate it*/
            Validation validateInput = new Validation(name.getText(), price.getText(), inv.getText(), min.getText(), max.getText(), "none");
            if (validateInput.getValidationValue()) {
                /*Converting input to right type*/
                double priceConverted = Double.parseDouble(price.getText());
                int invConverted = Integer.parseInt(inv.getText());
                int minConverted = Integer.parseInt(min.getText());
                int maxConverted = Integer.parseInt(max.getText());
                /*If product was already created in attached part option*/
                if(newProduct == null){
                    newProduct = new Product(generatedIdValue, name.getText(), priceConverted, invConverted, minConverted, maxConverted);
                    Inventory.addProduct(newProduct);
                }
                /*Add product to inventory*/
                else{
                    Inventory.addProduct(newProduct);
                }
                /*Close form after click*/
                MainForm_controller.getStage().close();
            } else {
                ErrorHolder.setText(validateInput.getErrorMessage());
            }
        }
        catch(Exception e){
            ErrorHolder.setText("Error!Product was not added! " + e.getMessage());
        }

    }

    /**Attach selected part */
    public void handlePartAttach(MouseEvent mouseEvent) {
        ErrorHolder.setText(""); // clear any errors if user try again
        try{
        // get selected part
        Part selectedPartForAssociation = PartTable.getSelectionModel().getSelectedItem();
        // Create new product
        /*Converting input to right type*/
        double priceConverted = Double.parseDouble(price.getText());
        int invConverted = Integer.parseInt(inv.getText());
        int minConverted = Integer.parseInt(min.getText());
        int maxConverted = Integer.parseInt(max.getText());
        if(newProduct == null){
            newProduct = new Product(generatedIdValue, name.getText(), priceConverted, invConverted, minConverted, maxConverted);
        }
        newProduct.addAssociatedPart(selectedPartForAssociation);
        // add to list of associated parts
        AssociatedPartTable.setItems(newProduct.getAllAssociatedParts());
        //Display rows
        AssociatedPartId.setCellValueFactory(data -> data.getValue().getIntId().asObject());
        AssociatedPartName.setCellValueFactory(data -> data.getValue().getStringName());
        AssociatedPartPrice.setCellValueFactory(data -> data.getValue().getDoublePropertyPrice().asObject());
        AssociatedPartInv.setCellValueFactory(data -> data.getValue().getIntStock().asObject());}
        catch (Exception e){
            ErrorHolder.setText("Error!Part was not attached! Fill out all fields.");
        }
    }

    /**Disassociate part */
    public void handleRemoveAssociatedPart() {
        ErrorHolder.setText(""); // clear any errors if user try again
        if(newProduct == null){
            newProduct = new Product();
        };
        if (newProduct.getAllAssociatedParts().size() == 0) {
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
                    selectedPartForAssociation = PartTable.getSelectionModel().getSelectedItem();
                    newProduct.deleteAssociatedPart(selectedPartForAssociation);
                }

            } catch (Exception err) {
                //System.out.println(err);
                ErrorHolder.setText("Deletion is failed! Try again!");
            }
        }


    }

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


}
