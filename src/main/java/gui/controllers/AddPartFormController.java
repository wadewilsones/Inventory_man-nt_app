package gui.controllers;

import functionality.*;

import gui.mainform.MainForm_controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This controller class is used in AddPart FXML, to handle Events
 */
public class AddPartFormController implements Initializable {

    @FXML
    private Text LabelData; // text for Machine ID/Company name
    @FXML
    public RadioButton OutsourcedRBtn; // used to switch between label text
    @FXML
    public TextField GeneratedID;
    static public int generatedIdValue; // increasing id value

    @FXML
    public Text ErrorHolder;

    /**
     * Input fields
     * */

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
    @FXML
    public TextField additionalInfo;


    /**
     * The method used when radio button toggled
     * */
    public void setLabelData(boolean isOutsource) {
        if(isOutsource){
            LabelData.setText("Company Name");
        }
        else{
            LabelData.setText("Machine ID");
        }
    }

    /**
     * Assigning Initial Values for changing components
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

            LabelData.setText("Machine ID");
            /**Generate ID*/
            generatedIdValue += 1;
            String convertedValue = Integer.toString(generatedIdValue);
            GeneratedID.setText(convertedValue);

    }

    /**
     * RadioButton toggle changes
     * */
    public void HandleRadioOutButtonChange(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(true);
    }
    public void HandleRadioInHouseButtonChange(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(false);
    }

    /**
     * Close opened window
     * */
    public void handleCancelClick(MouseEvent mouseEvent) {

        MainForm_controller.getStage().close();
    }

    /**
     * Adding a new Part
     * */
    public void handleFormSubmission(MouseEvent mouseEvent) {
        ErrorHolder.setText("");
        /*Get input and Validate it*/
        Validation validateInput = new Validation(name.getText(), price.getText(), inv.getText(), min.getText(), max.getText(), additionalInfo.getText());
        if(validateInput.getValidationValue()){

            /*Converting input to right type*/
            double priceConverted = Double.parseDouble(price.getText());
            int invConverted = Integer.parseInt(inv.getText());
            int minConverted = Integer.parseInt(min.getText());
            int maxConverted = Integer.parseInt(max.getText());

            /*Check additional Info and create a part*/
            if(LabelData.getText().equals("Machine ID")){

                int additionalInfoConverted = Integer.parseInt(additionalInfo.getText());
                InHouse newPart = new InHouse(generatedIdValue, name.getText(), priceConverted,invConverted,minConverted,maxConverted,additionalInfoConverted);
                Inventory.addPart(newPart);

            }
            else{
                Outsourced newPart = new Outsourced(generatedIdValue, name.getText(), priceConverted,invConverted,minConverted,maxConverted, additionalInfo.getText());
                Inventory.addPart(newPart);
            }

            /*Close form after click*/
            MainForm_controller.getStage().close();
        }
        else{
            ErrorHolder.setText(validateInput.getErrorMessage());
        }



    }

}
