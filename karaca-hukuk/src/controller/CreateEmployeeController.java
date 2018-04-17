package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEmployeeController implements Initializable {

    @FXML
    private RadioButton radioLawyer;

    @FXML
    private RadioButton radioSecretary;

    private ToggleGroup lawyerOrSecretary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lawyerOrSecretary = new ToggleGroup();
        radioLawyer.setToggleGroup(lawyerOrSecretary);
        radioSecretary.setToggleGroup(lawyerOrSecretary);
    }

    @FXML
    public void closeDialog() {
        EmployeesController.createEmployeeDialog.close();
    }
}
