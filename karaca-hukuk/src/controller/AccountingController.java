package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;

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

    }

    @FXML
    void pay(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AccountingController.initialize");
    }
}
