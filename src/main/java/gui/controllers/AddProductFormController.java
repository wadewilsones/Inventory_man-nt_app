package gui.controllers;

import gui.mainform.MainForm_controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**RUNTIME or LOGICAL ERRORS didn't occur here.*/

public class AddProductFormController implements Initializable{

    @FXML
    private Text HeadingForProduct; // text for Heading in Add/Modify Form

    /*For generating ID*/
    @FXML
    public TextField GeneratedID;
    static public int generatedIdValue;

    /**Handle screens of Modify and Add*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*Setting up initial value for ID*/
        generatedIdValue += 1;
        String convertedValue = Integer.toString(generatedIdValue);
        GeneratedID.setText(convertedValue);

        /** Setting value according to chosen Form (modify or add part)*/
        if(MainForm_controller.getStage().getTitle().equals("Add Product")){
            /**Set heading to Add*/
            HeadingForProduct.setText("Add Part");
        }
        else{
            /**Set heading to Modify*/
            HeadingForProduct.setText("Modify Part");
        }

    }
    /**Close opened window*/
    public void handleCancelClick(MouseEvent mouseEvent) {
        MainForm_controller.getStage().close();
    }

}
