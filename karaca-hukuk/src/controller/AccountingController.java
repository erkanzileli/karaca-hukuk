package controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountingController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTreeTableView<?> tableView;

    @FXML
    private TreeTableColumn<?, ?> columnDate;

    @FXML
    private TreeTableColumn<?, ?> columnCustomer;

    @FXML
    private TreeTableColumn<?, ?> columnLawsuitNumber;

    @FXML
    private TreeTableColumn<?, ?> columnAmount;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXButton btnPay;

    @FXML
    void create(ActionEvent event) {
        Parent createPayRecord = null;
        try {
            createPayRecord = FXMLLoader.load(getClass().getResource("/fxml/createPayRecord.fxml"));
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(createPayRecord);
        JFXDialog dialog = new JFXDialog(root, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }


    @FXML
    void search(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AccountingController.initialize");
    }
}
