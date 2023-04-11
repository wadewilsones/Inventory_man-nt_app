package gui.controllers;

import functionality.*;
import gui.mainform.MainForm_controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**RUNTIME or LOGICAL ERRORS didn't occur here.*/

public class AddProductFormController implements Initializable{

    /*For generating ID*/
    @FXML
    public TextField GeneratedID;
    static public int generatedIdValue;

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

    public void handleFormSubmission(MouseEvent mouseEvent) {

        /*Get input and Validate it*/
        Validation validateInput = new Validation(name.getText(), price.getText(), inv.getText(), min.getText(), max.getText(), "none");
        if(validateInput.getValidationValue()){

            /*Converting input to right type*/
            double priceConverted = Double.parseDouble(price.getText());
            int invConverted = Integer.parseInt(inv.getText());
            int minConverted = Integer.parseInt(min.getText());
            int maxConverted = Integer.parseInt(max.getText());
            Product newProduct = new Product(generatedIdValue, name.getText(), priceConverted, invConverted, minConverted, maxConverted);
            Inventory.addProduct(newProduct);
            /*Close form after click*/
            MainForm_controller.getStage().close();
        }
        else{
            ErrorHolder.setText(validateInput.getErrorMessage());
        }


    }

    /**Handle adding a Product*/

}
