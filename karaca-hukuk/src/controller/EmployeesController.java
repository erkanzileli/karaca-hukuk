package controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {

    @FXML
    private StackPane root;

    public static JFXDialog createEmployeeDialog;

    @FXML
    private void showCreateEmployee() {
        Parent createEmployee = null;
        try {
            createEmployee = FXMLLoader.load(getClass().getResource("/fxml/createEmployee.fxml"));
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(createEmployee);
        JFXDialog dialog = new JFXDialog(root, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
        createEmployeeDialog = dialog;
        dialog.setOverlayClose(false);
        dialog.show();
    }

    @FXML
    private void search() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
