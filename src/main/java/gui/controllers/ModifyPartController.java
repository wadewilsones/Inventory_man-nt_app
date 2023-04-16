package gui.controllers;

import functionality.InHouse;
import functionality.Inventory;
import functionality.Outsourced;
import functionality.Validation;
import gui.mainform.MainForm_controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import static gui.mainform.MainForm_controller.*;


/**
 * This controller class is used in ModifyPart FXML, to handle Events and etc
 */
public class ModifyPartController implements Initializable {


    /**
     * Input fields
     * */
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
    @FXML
    public TextField additionalInfo;

    @FXML
    public Text LabelData; // text for Machine ID/Company name

    @FXML
    public RadioButton OutsourcedRBtn;
    public String InitialLabelData; // for checking if Radio Buttons were switched
    @FXML
    public RadioButton InHouse;

    public boolean isOutsourcePart;

    /**
     * Switch between In-House and Outsourced
     */
    public void setLabelData(boolean isOutsource) {
        if (isOutsource) {
            LabelData.setText("Company Name");
            isOutsourcePart = true;
        } else {
            LabelData.setText("Machine ID");
            isOutsourcePart = false;
        }
    }


    /**
     * handle Radio Button Change in Modify Form
     */
    public void HandleRadioInHouseButtonChangeMod(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(false);
    }

    public void HandleRadioOutButtonChangeMod(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(true);
    }

    /**
     * Assigning Initial Values for components
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /**Get values from Inventory*/
        name.setText(selectedPart.getName());
        GeneratedID.setText(String.valueOf(selectedPart.getId()));
        inv.setText(String.valueOf(selectedPart.getStock()));
        price.setText(String.valueOf(selectedPart.getPrice()));
        min.setText(String.valueOf(selectedPart.getMin()));
        max.setText(String.valueOf(selectedPart.getMax()));

        /*Set up values for Machine ID/Outsourced*/
        if (selectedPart instanceof InHouse) {
            setLabelData(false);
            InHouse.setSelected(true);
            additionalInfo.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        } else {
            setLabelData(true);
            OutsourcedRBtn.setSelected(true);
            additionalInfo.setText(((Outsourced) selectedPart).getCompanyName());
        }

        InitialLabelData = String.valueOf(LabelData.getText()); // assign initial label value to label for Outsourced/machine ID

    }

    /**
     * Modifying and saving Part
     */
    public void handleFormSubmission(MouseEvent mouseEvent) {
        System.out.println("Submit...");
        /*Get input and Validate it*/
        Validation validateInput = new Validation(name.getText(), price.getText(), inv.getText(), min.getText(), max.getText(), additionalInfo.getText());
        if (validateInput.getValidationValue()) {

            /*Converting input to right type*/
            double priceConverted = Double.parseDouble(price.getText());
            int invConverted = Integer.parseInt(inv.getText());
            int minConverted = Integer.parseInt(min.getText());
            int maxConverted = Integer.parseInt(max.getText());

            /*Get is Outsource or not*/
            if (!isOutsourcePart) {
                int additionalInfoConverted = Integer.parseInt(additionalInfo.getText());
                InHouse updatePart = new InHouse(Integer.valueOf(String.valueOf(GeneratedID.getText())), name.getText(), priceConverted, invConverted, minConverted, maxConverted, additionalInfoConverted);
                Inventory.updatePart(Inventory.getAllParts().indexOf(selectedPart), updatePart);
            } else {
                Outsourced updateOutPart = new Outsourced(Integer.valueOf(String.valueOf(GeneratedID.getText())), name.getText(), priceConverted, invConverted, minConverted, maxConverted, additionalInfo.getText());
                Inventory.updatePart(Inventory.getAllParts().indexOf(selectedPart), updateOutPart);
            }
        }

    /*Close form after click*/
            MainForm_controller.getStage().close();

}


    /**Close opened window*/
    public void handleCancelClick(MouseEvent mouseEvent) {

        MainForm_controller.getStage().close();
    }

}
