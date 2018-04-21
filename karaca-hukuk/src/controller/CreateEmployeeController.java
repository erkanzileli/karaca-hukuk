package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEmployeeController implements Initializable {

    @FXML
    private JFXComboBox<String> comboPosition;

    @FXML
    private JFXComboBox<String> comboSex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboPosition.getItems().addAll("Avukat", "Sekreter");
        comboSex.getItems().addAll("Kadın","Erkek");

    }

    @FXML
    public void closeDialog() {
        EmployeeController.createEmployeeDialog.close();
    }
}
