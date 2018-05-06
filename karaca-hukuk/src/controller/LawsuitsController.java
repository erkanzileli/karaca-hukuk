package controller;

import com.jfoenix.controls.*;
import entity.Lawsuit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.LawsuitListModel;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class LawsuitsController implements Initializable {

    public static BorderPane rootpane;
    public Parent lawsuit = null;

    @FXML
    private StackPane root;

    @FXML
    private JFXButton btnCreateLawsuit;

    @FXML
    private JFXButton btnFilter;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private TableView<LawsuitListModel> tableView;

    @FXML
    private TableColumn<LawsuitListModel, String> columnName;

    @FXML
    private TableColumn<LawsuitListModel, String> columnSurname;

    @FXML
    private TableColumn<LawsuitListModel, Long> columnTC;

    @FXML
    private TableColumn<LawsuitListModel, Long> columnPhoneNumber;

    @FXML
    private TableColumn<LawsuitListModel, String> columnLawsuitType;

    @FXML
    private TableColumn<LawsuitListModel, String> columnLawsuitStatus;

    @FXML
    private TableColumn<LawsuitListModel, String> columnCustomerType;

    @FXML
    private TableColumn<LawsuitListModel, Date> columnCreateDate;


    private EntityManager entityManager;
    private List<Object> models;
    private List<LawsuitListModel> lawsuitListModelList = new ArrayList<>();

    @FXML
    void create() {
        Parent lawsuitFormDialog = null;
        try {
            lawsuitFormDialog = FXMLLoader.load(getClass().getResource("/fxml/createLawsuit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootpane.setCenter(lawsuitFormDialog);

    }

    @FXML
    void openFilterDialog() {
        Parent filterDialog = null;
        try {
            filterDialog = FXMLLoader.load(getClass().getResource("/fxml/lawsuitFilter.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setBody(filterDialog);
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.RIGHT);
        dialog.show();
    }

    @FXML
    void search() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/lawsuitsDetails.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setBody(parent);
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LawsuitsController.initialize");
        entityManager = EntityManagerUtility.getEntityManager();
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnTC.setCellValueFactory(new PropertyValueFactory<>("tc"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnLawsuitType.setCellValueFactory(new PropertyValueFactory<>("lawsuitType"));
        columnLawsuitStatus.setCellValueFactory(new PropertyValueFactory<>("lawsuitStatus"));
        columnCustomerType.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        columnCreateDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        LawsuitListModel selectedModel;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://138.197.191.148:3306/karaca", "root", "00000000");
            ResultSet rs = connection.createStatement().executeQuery("SELECT name,surname,Customer.type as customerType,tc,phoneNumber,L.type as lawsuitType,status,date from Customer inner join Lawsuit L ON Customer.idCustomer = L.idCustomer");
            while (rs.next()) {
                LawsuitListModel m1 = new LawsuitListModel(rs.getString("name"), rs.getString("surname"), rs.getLong("tc"), rs.getLong("phoneNumber"), rs.getString("lawsuitType"), rs.getString("status"), rs.getString("customerType"), rs.getDate("date"));
                lawsuitListModelList.add(m1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.getItems().addAll(lawsuitListModelList);

        System.out.println("BAYRAK 6");
        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {

                showLawsuitDetails(tableView.getSelectionModel().getSelectedItem().getTc(),tableView.getSelectionModel().getSelectedItem().getCreateDate());
            }
        });


    }

    private void showLawsuitDetails(Long tc,Date date) {

        CreateLawsuitController.selectedItemTc=tc;
        CreateLawsuitController.selectedItemDate=date;
        try {
            lawsuit = FXMLLoader.load(getClass().getResource("/fxml/createLawsuit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootpane.setCenter(lawsuit);


    }
}
