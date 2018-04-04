package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entity.Province;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCustomerController implements Initializable {

    @FXML
    private JFXDialogLayout root;

    @FXML
    private Pane rootPane;

    @FXML
    private JFXComboBox<String> comboProvince;

    @FXML
    private JFXComboBox<String> comboDistrict;

    @FXML
    private JFXTextField textQuarter;

    @FXML
    private JFXTextField textStreet;

    @FXML
    private JFXTextField textDoorNumber;

    @FXML
    private JFXTextField doorPostalCode;

    @FXML
    private JFXTextField textName;

    @FXML
    private JFXTextField textPhone;

    @FXML
    private JFXRadioButton radioSingular;

    @FXML
    private JFXRadioButton radioEnterprise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("CreateCustomerController.initialize");
    }

    public void fillComboBoxes(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Province> typedQuery = em.createQuery("SELECT P FROM Province P",Province.class);
        List<Province> provinces = typedQuery.getResultList();
    }

    @FXML
    public void closeDialog() {
        CustomerController.createCustomerDialog.close();
        fillComboBoxes();
    }
}
