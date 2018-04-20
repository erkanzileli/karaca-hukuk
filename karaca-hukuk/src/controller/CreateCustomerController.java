package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entity.District;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.HashMap;
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

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private HashMap<Integer, String> provinces;

    private ToggleGroup singularPlural;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        provinces = new HashMap<>();
        singularPlural = new ToggleGroup();
        radioEnterprise.setToggleGroup(singularPlural);
        radioSingular.setToggleGroup(singularPlural);
        fillComboProvince();

    }

    public void fillComboProvince() {
        provinces.put(1, "Adana");
        provinces.put(2, "Adıyaman");
        provinces.put(3, "Afyonkarahisar");
        provinces.put(4, "Ağrı");
        provinces.put(5, "Amasya");
        provinces.put(6, "Ankara");
        provinces.put(7, "Antalya");
        provinces.put(8, "Artvin");
        provinces.put(9, "Aydın");
        provinces.put(10, "Balıkesir");
        provinces.put(11, "Bilecik");
        provinces.put(12, "Bingöl");
        provinces.put(13, "Bitlis");
        provinces.put(14, "Bolu");
        provinces.put(15, "Burdur");
        provinces.put(16, "Bursa");
        provinces.put(17, "Çanakkale");
        provinces.put(18, "Çankırı");
        provinces.put(19, "Çorum");
        provinces.put(20, "Denizli");
        provinces.put(21, "Diyarbakır");
        provinces.put(22, "Edirne");
        provinces.put(23, "Elâzığ");
        provinces.put(24, "Erzincan");
        provinces.put(25, "Erzurum");
        provinces.put(26, "Eskişehir");
        provinces.put(27, "Gaziantep");
        provinces.put(28, "Giresun");
        provinces.put(29, "Gümüşhane");
        provinces.put(30, "Hakkâri");
        provinces.put(31, "Hatay");
        provinces.put(32, "Isparta");
        provinces.put(33, "Mersin");
        provinces.put(34, "İstanbul");
        provinces.put(35, "İzmir");
        provinces.put(36, "Kars");
        provinces.put(37, "Kastamonu");
        provinces.put(38, "Kayseri");
        provinces.put(39, "Kırklareli");
        provinces.put(40, "Kırşehir");
        provinces.put(41, "Kocaeli");
        provinces.put(42, "Konya");
        provinces.put(43, "Kütahya");
        provinces.put(44, "Malatya");
        provinces.put(45, "Manisa");
        provinces.put(46, "Kahramanmaraş");
        provinces.put(47, "Mardin");
        provinces.put(48, "Muğla");
        provinces.put(49, "Muş");
        provinces.put(50, "Nevşehir");
        provinces.put(51, "Niğde");
        provinces.put(52, "Ordu");
        provinces.put(53, "Rize");
        provinces.put(54, "Sakarya");
        provinces.put(55, "Samsun");
        provinces.put(56, "Siirt");
        provinces.put(57, "Sinop");
        provinces.put(58, "Sivas");
        provinces.put(59, "Tekirdağ");
        provinces.put(60, "Tokat");
        provinces.put(61, "Trabzon");
        provinces.put(62, "Tunceli");
        provinces.put(63, "Şanlıurfa");
        provinces.put(64, "Uşak");
        provinces.put(65, "Van");
        provinces.put(66, "Yozgat");
        provinces.put(67, "Zonguldak");
        provinces.put(68, "Aksaray");
        provinces.put(69, "Bayburt");
        provinces.put(70, "Karaman");
        provinces.put(71, "Kırıkkale");
        provinces.put(72, "Batman");
        provinces.put(73, "Şırnak");
        provinces.put(74, "Bartın");
        provinces.put(75, "Ardahan");
        provinces.put(76, "Iğdır");
        provinces.put(77, "Yalova");
        provinces.put(78, "Karabük");
        provinces.put(79, "Kilis");
        provinces.put(80, "Osmaniye");
        provinces.put(81, "Düzce");
        comboProvince.getItems().addAll(provinces.values());
    }

    @FXML
    public void save() {
        RadioButton selected = (RadioButton) singularPlural.selectedToggleProperty().getValue();
        System.out.println(selected.getText());
    }

    @FXML
    public void listDistrictsOfProvince() {
        entityManagerFactory = Persistence.createEntityManagerFactory("mysqlPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<District> typedQuery = (TypedQuery<District>) entityManager.createNativeQuery("SELECT * FROM ilceler", District.class);
        List<District> districtsOfProvince = typedQuery.getResultList();
        System.out.println("HashMap<String,String> districtsofProvinces = HashMap<>();");
        for (District dis :
                districtsOfProvince) {
            System.out.println("districtsofProvinces.put(\"" + dis.getIl() + "\",\"" + dis.getIsim() + "\");");
        }
    }

    @FXML
    public void closeDialog() {
        CustomerController.createCustomerDialog.close();
    }
}
