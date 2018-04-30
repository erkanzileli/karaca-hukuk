package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
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
    private JFXComboBox<?> comboPosition;

    @FXML
    private JFXComboBox<?> comboSex;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXToggleButton tglCorrection;

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
            txtPhone.setEditable(true);
        } else {
            txtEmail.setEditable(false);
            txtName.setEditable(false);
            txtSurname.setEditable(false);
            txtTC.setEditable(false);
            txtMobilePhone.setEditable(false);
            txtPhone.setEditable(false);
        }
    }

    @FXML
    void save() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
