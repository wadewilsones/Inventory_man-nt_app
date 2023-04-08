package gui.addingForms;

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


/**To use variables values in FXML*/
/**RUNTIME ERROR occurred when initialize function was empty,
 * so was added necessary initial settings such as LabelData */

public class AddPartFormController implements Initializable {

    @FXML
    private Text LabelData; // text for Machine ID/Company name
    @FXML
    private Text HeadingForPart; // text for Heading in Add/Modify Form
    @FXML
    public RadioButton OutsourcedRBtn; // used to switch between label text
    @FXML
    public TextField GeneratedID;
    static public int generatedIdValue; // increasing id value

    @FXML
    public Text ErrorHolder;

    /*Input fields*/


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


    public Text getLabelData() {
        return LabelData;
    }

    public void setLabelData(boolean isOutsource) {
        if(isOutsource){
            LabelData.setText("Company Name");
        }
        else{
            LabelData.setText("Machine ID");
        }
    }

    /**Assigning Initial Values for changing components */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LabelData.setText("Machine ID");
        generatedIdValue += 1;
        String convertedValue = Integer.toString(generatedIdValue);
        GeneratedID.setText(convertedValue);

        /** Setting value according to chosen Form (modify or add part)*/
        if(MainForm_controller.getStage().getTitle().equals("Add Part")){
            /**Set heading to Add*/
            HeadingForPart.setText("Add Part");
        }
        else{
            /**Set heading to Modify*/
            HeadingForPart.setText("Modify Part");
        }

    }

    /**RadioButton toggle changes*/
    public void HandleRadioOutButtonChange(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(true);
    }
    public void HandleRadioInHouseButtonChange(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(false);
    }

    /**Close opened window*/
    public void handleCancelClick(MouseEvent mouseEvent) {

        MainForm_controller.getStage().close();
    }

    /**Adding a new Part*/

    public void handleFormSubmission(MouseEvent mouseEvent) {

        /*Get input and Validate it*/
        Validation validateInput = new Validation(name.getText(), price.getText(), inv.getText(), min.getText(), max.getText(), additionalInfo.getText());
        if(validateInput.getValidationValue()){

            /*Converting input to right type*/
            double priceConverted = Double.parseDouble(price.getText());
            int invConverted = Integer.parseInt(inv.getText());
            int minConverted = Integer.parseInt(min.getText());
            int maxConverted = Integer.parseInt(max.getText());
            int additionalInfoConverted = Integer.parseInt(additionalInfo.getText());

            /*Check additional Info and create a part*/
            if(additionalInfo.getText().equals("Machine ID")){
                Part newPart = new InHouse(generatedIdValue, name.getText(), priceConverted,invConverted,minConverted,maxConverted,additionalInfoConverted);
                Inventory.addPart(newPart);

            }
            else{
                Part newPart = new Outsourced(generatedIdValue, name.getText(), priceConverted,invConverted,minConverted,maxConverted,additionalInfo.getText());
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
