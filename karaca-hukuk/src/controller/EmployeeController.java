package controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import dao.EmployeeDAO;
import daoImpl.EmployeeDAOImpl;
import entity.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTreeTableView<?> tableOfEmployees;

    @FXML
    private TreeTableColumn<?, ?> columnName;

    @FXML
    private TreeTableColumn<?, ?> columnSurname;

    @FXML
    private TreeTableColumn<?, ?> columnTC;

    @FXML
    private TreeTableColumn<?, ?> columnType;

    @FXML
    private TreeTableColumn<?, ?> columnEmail;

    @FXML
    private TreeTableColumn<?, ?> columnMobilePhone;

    @FXML
    private JFXTextField textSearch;

    static JFXDialog createEmployeeDialog;

    private static JFXDialog employeeDetailsDialog;

    @FXML
    private void showCreateEmployee() {
        createEmployeeDialog = createDialog("createEmployee", JFXDialog.DialogTransition.TOP);
        createEmployeeDialog.setOverlayClose(false);
        createEmployeeDialog.show();
    }


    @FXML
    private void search() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //todo:tablodaki elemanlardan birine tıklandığında
    private void showEmployeeDetails() {
        employeeDetailsDialog = createDialog("employeeDetails", JFXDialog.DialogTransition.CENTER);
        employeeDetailsDialog.setOverlayClose(false);
        employeeDetailsDialog.show();
    }

    private JFXDialog createDialog(String fxmlName, JFXDialog.DialogTransition dialogTransition) {
        Parent employeeDetails = null;
        try {
            employeeDetails = FXMLLoader.load(getClass().getResource("/fxml/" + fxmlName + ".fxml"));
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(employeeDetails);
        return new JFXDialog(root, jfxDialogLayout, dialogTransition);
    }

    static JFXDialog getCreateEmployeeDialog() {
        return createEmployeeDialog;
    }

    static JFXDialog getEmployeeDetailsDialog() {
        return employeeDetailsDialog;
    }

}
