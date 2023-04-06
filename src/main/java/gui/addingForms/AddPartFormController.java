package gui.addingForms;

import gui.mainform.MainForm;
import gui.mainform.MainForm_controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import gui.mainform.MainForm;
import javafx.stage.Stage;

/**To use variables values in FXML*/
public class AddPartFormController implements Initializable {

    @FXML
    private Text LabelData; // text for Machine ID/Company name
    @FXML
    private Text HeadingForPart; // text for Heading in Add/Modify Form
    @FXML
    public RadioButton OutsourcedRBtn; // used to switch between label text

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

    public void HandleRadioOutButtonChange(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(true);
    }
    public void HandleRadioInHouseButtonChange(javafx.scene.input.MouseEvent mouseEvent) {
        setLabelData(false);
    }
}
