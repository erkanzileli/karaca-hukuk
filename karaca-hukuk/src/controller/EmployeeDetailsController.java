package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import entity.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeDetailsController implements Initializable {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSurname;

    @FXML
    private JFXTextField txtTC;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtMobilePhone;

    @FXML
    private JFXComboBox<String> comboPosition;

    @FXML
    private JFXComboBox<String> comboSex;

    @FXML
    private JFXToggleButton tglCorrection;

    public static Member selectedEmployee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtName.setText(selectedEmployee.getName());
        txtSurname.setText(selectedEmployee.getSurname());
        txtEmail.setText(selectedEmployee.getEmail());
        txtMobilePhone.setText(String.valueOf(selectedEmployee.getPhoneNumber()));
        txtTC.setText(String.valueOf(selectedEmployee.getTc()));

    }

    @FXML
    void closeDialog() {
        EmployeeController.getEmployeeDetailsDialog().close();
    }

    @FXML
    void changeCorrectionMode() {
        if (tglCorrection.isSelected()) {
            txtEmail.setEditable(true);
            txtName.setEditable(true);
            txtSurname.setEditable(true);
            txtTC.setEditable(true);
            txtMobilePhone.setEditable(true);
        } else {
            txtEmail.setEditable(false);
            txtName.setEditable(false);
            txtSurname.setEditable(false);
            txtTC.setEditable(false);
            txtMobilePhone.setEditable(false);
        }
    }

    @FXML
    void save() {

    }


}
