package gui.addingForms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**To use variables values in FXML*/
public class AddPartFormController implements Initializable {

    @FXML
    private Text LabelData; // label for Machine ID/Company name
    @FXML
    public RadioButton OutsourcedRBtn; // used to switch between label text

    public Text getText() {
        return LabelData;
    }
    public void setText(boolean isOutsource) {
        if(isOutsource){
            LabelData.setText("Company Name");
        }
        else{
            LabelData.setText("Machine ID");
        }
    }

    /**Assigning Initial Value for a Machine ID label*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelData.setText("Machine ID");
    }

    public void HandleRadioButtonChange(javafx.scene.input.MouseEvent mouseEvent) {
        setText(true);
        System.out.println(getText());
    }
}
