package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label root;

    @FXML
    private JFXToggleButton toggleReplaceMode;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSurname;

    @FXML
    private JFXTextField txtTC;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtOldPasswd;

    @FXML
    private JFXPasswordField txtNewPasswd;

    @FXML
    private JFXPasswordField txtNewPasswdAgain;

    @FXML
    private JFXButton btnSave;

    @FXML
    public void save() {

    }

    @FXML
    public void toggleReplace() {
        if (toggleReplaceMode.isSelected()) {
            txtName.setEditable(true);
            txtSurname.setEditable(true);
            txtEmail.setEditable(true);
            txtTC.setEditable(true);
            txtOldPasswd.setEditable(true);
            txtNewPasswd.setEditable(true);
            txtNewPasswdAgain.setEditable(true);
        } else {
            txtName.setEditable(false);
            txtSurname.setEditable(false);
            txtEmail.setEditable(false);
            txtTC.setEditable(false);
            txtOldPasswd.setEditable(false);
            txtNewPasswd.setEditable(false);
            txtNewPasswdAgain.setEditable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toggleReplace();
    }
}
