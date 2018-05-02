package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import entity.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, String> columnName;

    @FXML
    private TableColumn<Customer, String> columnSurname;

    @FXML
    private TableColumn<Customer, String> columnType;

    @FXML
    private TableColumn<Customer, Long> columnPhone;

    @FXML
    private TableColumn<Customer, Long> columnTC;

    @FXML
    private TableColumn<Customer, Long> columnTaxNumber;

    public static JFXDialog createCustomerDialog;

    public static JFXDialog customerDetailsDialog;

    private EntityManager entityManager;

    private List<Customer> customers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("CustomerController.initialize");
        entityManager = EntityManagerUtility.getEntityManager();

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnTC.setCellValueFactory(new PropertyValueFactory<>("tc"));
        columnTaxNumber.setCellValueFactory(new PropertyValueFactory<>("taxNumber"));

        fillTable();

        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                CustomerDetailController.selectedCustomer = tableView.getSelectionModel().getSelectedItem();
                showCustomerDetails();
            }
        });
    }

    private void fillTable() {
        TypedQuery<Customer> query = (TypedQuery<Customer>) entityManager.createNativeQuery("SELECT * FROM Customer",
                Customer.class);
        customers = query.getResultList();
        tableView.getItems().clear();
        tableView.getItems().addAll(customers);
    }

    private void getData() {
        TypedQuery<Customer> query = (TypedQuery<Customer>) entityManager.createNativeQuery("SELECT * FROM Customer",
                Customer.class);
        customers = query.getResultList();
    }

    void showCustomerDetails() {
        Parent details = null;
        try {
            details = FXMLLoader.load(getClass().getResource("/fxml/customerDetail.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setBody(details);
        JFXDialog dialog = new JFXDialog(root, dialogLayout, DialogTransition.CENTER);
        dialog.setOverlayClose(false);
        dialog.setOnDialogClosed(e -> {
            fillTable();
        });
        dialog.show();
        customerDetailsDialog = dialog;
    }

    @FXML
    void search() {

    }

    @FXML
    void create() {
        Parent createCustomer = null;
        try {
            createCustomer = FXMLLoader.load(getClass().getResource("/fxml/createCustomer.fxml"));
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(createCustomer);
        JFXDialog dialog = new JFXDialog(root, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.setOverlayClose(false);
        createCustomerDialog = dialog;
        dialog.show();
    }

}
