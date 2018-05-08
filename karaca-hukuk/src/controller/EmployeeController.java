package controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import entity.Member;
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

public class EmployeeController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private TableView<Member> tableView;

    @FXML
    private TableColumn<Member, String> columnName;

    @FXML
    private TableColumn<Member, String> columnSurname;

    @FXML
    private TableColumn<Member, Long> columnTC;

    @FXML
    private TableColumn<Member, Long> columnPhoneNumber;

    @FXML
    private TableColumn<Member, String> columnEmail;

    @FXML
    private TableColumn<Member, String> columnGender;

    @FXML
    private TableColumn<Member, String> columnType;


    public static JFXDialog createEmployeeDialog;

    public static JFXDialog employeeDetailsDialog;

    private EntityManager entityManager;

    private List<Member> members;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();

        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnTC.setCellValueFactory(new PropertyValueFactory<>("tc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        fillTable();

        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                EmployeeDetailsController.selectedEmployee = tableView.getSelectionModel().getSelectedItem();
                showEmployeeDetails();
            }
        });

    }

    private void fillTable() {
        TypedQuery<Member> query = (TypedQuery<Member>) entityManager.createNativeQuery("SELECT * FROM Member",
                Member.class);
        members = query.getResultList();
        tableView.getItems().clear();
        tableView.getItems().addAll(members);
    }

    @FXML
    private void showCreateEmployee() {
        createEmployeeDialog = createDialog("createEmployee", JFXDialog.DialogTransition.TOP);
        createEmployeeDialog.setOverlayClose(false);
        createEmployeeDialog.show();
    }


    @FXML
    private void search() {

    }


    //TODO:tablodaki elemanlardan birine tıklandığında
    private void showEmployeeDetails() {
        employeeDetailsDialog = createDialog("employeeDetails", JFXDialog.DialogTransition.CENTER);
        employeeDetailsDialog.setOverlayClose(false);
        employeeDetailsDialog.show();
        employeeDetailsDialog.setOnDialogClosed(e->{
            fillTable();
        });
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
