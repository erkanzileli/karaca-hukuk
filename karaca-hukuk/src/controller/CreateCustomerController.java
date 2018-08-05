package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entity.Adress;
import entity.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    private JFXTextField textAdressPhone;

    @FXML
    private JFXTextField textStreet;

    @FXML
    private JFXTextField textDoorNumber;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField textName;

    @FXML
    private JFXTextField textSurname;

    @FXML
    private JFXTextField textPhone;

    @FXML
    private JFXRadioButton radioSingular;

    @FXML
    private JFXRadioButton radioEnterprise;

    @FXML
    private Label lblIdentityNo;

    @FXML
    private JFXTextField txtIdentityNo;

    @FXML
    private Label lblSex;

    @FXML
    private JFXComboBox<String> comboSex;

    private List<String> provinces;

    private List<String> districts;

    private EntityManager entityManager;

    private ToggleGroup individualOrEnterprise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();
        fillComboProvinces();
        comboSex.getItems().addAll("Kadin", "Erkek");
        individualOrEnterprise = new ToggleGroup();
        radioEnterprise.setToggleGroup(individualOrEnterprise);
        radioSingular.setToggleGroup(individualOrEnterprise);
    }

    @FXML
    void selectEnterprise() {
        lblIdentityNo.setText("Vergi no");
        lblSex.setVisible(false);
        comboSex.setVisible(false);
    }

    @FXML
    void selectIndividual() {
        lblIdentityNo.setText("T.C.");
        lblSex.setVisible(true);
        comboSex.setVisible(true);
    }

    @FXML
    public void save() {
        RadioButton selected = (RadioButton) individualOrEnterprise.selectedToggleProperty().getValue();
        // adress
        String province = comboProvince.getValue();
        String district = comboDistrict.getValue();
        String street = textStreet.getText().trim();
        String doorNumber = textDoorNumber.getText().trim();
        // general
        String type = selected.getText();
        String name = textName.getText().trim();
        String surname = textSurname.getText().trim();
        int postalCode = 0;
        if (txtPostalCode.getText().trim().length() > 0) {
            try {
                postalCode = Integer.parseInt(txtPostalCode.getText().trim());
            } catch (Exception e) {
                // int değil
                createAlertDialog(AlertType.ERROR, "Hata", "Hatalı değer girildi.", "Posta kodu nümerik olmalıdır.")
                        .show();
            }
        }
        long phoneNumber = 0;
        if (textPhone.getText().trim().length() > 0) {

            try {
                phoneNumber = Long.parseLong(textPhone.getText().trim());
            } catch (Exception e) {
                // long değil
                createAlertDialog(AlertType.ERROR, "Hata", "Hatalı değer girildi.",
                        "Telefon numarası nümerik olmalıdır.").show();
            }
        }
        // bireysel
        if ("Bireysel".equals(type)) {
            String tcString = txtIdentityNo.getText().trim();
            // T.C. 11 haneli olmalı
            if (tcString.length() != 11) {
                createAlertDialog(AlertType.INFORMATION, "Bilgi", null,
                        "T.C. numarası 11 haneli olmalı nümerik olmalıdır.").show();
            } else { // T.C. 11 haneli
                long tc = 0;
                String sex = comboSex.getValue();
                try {
                    tc = Long.parseLong(tcString);
                } catch (Exception e) {
                    createAlertDialog(AlertType.INFORMATION, "Bilgi", null, "T.C. nümerik olmalıdır.").show();
                }
                if (tc != 0) { // T.C. doğru alındı
                    //bu kimlik numarasına sahip bir müşteri var mı
                    Query query = entityManager.createNativeQuery("SELECT idCustomer FROM Customer WHERE tc=?1");
                    query.setParameter(1, tc);
                    List<Integer> result = query.getResultList();
                    if (query.getResultList().isEmpty()) {
                        // Müşteri kaydedilir
                        entityManager.getTransaction().begin();
                        entityManager.persist(new Customer(tc, type, name, surname, phoneNumber, sex));
                        entityManager.getTransaction().commit();
                        //eklenen müşterinin idCustomer bilgisini çekme
                        result = query.getResultList();
                        // Adress bilgilerinin nesneye aktarılması
                        Adress adress = new Adress();
                        adress.setCounty(province);
                        adress.setCity(district);
                        adress.setStreet(street);
                        adress.setDoorNumber(doorNumber);
                        adress.setPostalCode(postalCode);
                        adress.setIdCustomer(result.get(0));
                        adress.setType(type);
                        // adres bilgisinin kaydedilmesi
                        entityManager.getTransaction().begin();
                        entityManager.persist(adress);
                        entityManager.getTransaction().commit();
                        closeDialog();
                    } else {
                        createAlertDialog(AlertType.ERROR, "Hata", "Bu kimlik numarasına sahip bir müşteri zaten var!", null).show();
                    }
                }
            }
        } else { // kurumsal
            String taxNumberString = txtIdentityNo.getText().trim();
            if (taxNumberString.length() != 10) {
                createAlertDialog(AlertType.ERROR, "Eksik bilgi.", null, "Vergi numarası 10 adet sayıdan oluşmalıdır.")
                        .show();
            } else {
                long taxNumber = 0;
                try {
                    taxNumber = Long.parseLong(txtIdentityNo.getText().trim());
                } catch (Exception e) {
                    createAlertDialog(AlertType.INFORMATION, "Bilgi", null, "Verigi numarası nümerik olmalıdır.")
                            .show();
                }
                if (taxNumber != 0) {
                    Query query = entityManager.createNativeQuery("SELECT idCustomer FROM Customer WHERE taxNumber=?1");
                    query.setParameter(1, taxNumber);
                    List<Integer> result = query.getResultList();
                    if (result.isEmpty()) {
                        // Müşteri kaydedilir
                        entityManager.getTransaction().begin();
                        entityManager.persist(new Customer(taxNumber, type, name, surname, phoneNumber));
                        entityManager.getTransaction().commit();
                        //eklenen müşterinin idCustomer bilgisini çekme
                        result = query.getResultList();
                        // Adress bilgilerinin nesneye aktarılması
                        Adress adress = new Adress();
                        adress.setCounty(province);
                        adress.setCity(district);
                        adress.setStreet(street);
                        adress.setDoorNumber(doorNumber);
                        adress.setPostalCode(postalCode);
                        adress.setIdCustomer(result.get(0));
                        adress.setType(type);
                        // adres bilgisinin kaydedilmesi
                        entityManager.getTransaction().begin();
                        entityManager.persist(adress);
                        entityManager.getTransaction().commit();
                        closeDialog();
                    } else {
                        createAlertDialog(AlertType.ERROR, "Hata", "Bu kimlik numarasına sahip bir müşteri zaten var!", null).show();
                    }

                }
            }
        }
    }

    private Alert createAlertDialog(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.headerTextProperty().setValue(header);
        alert.titleProperty().set(title);
        alert.contentTextProperty().set(content);
        return alert;
    }

    private void fillComboProvinces() {
        Query query = entityManager.createNativeQuery("SELECT DISTINCT il_isim FROM Provinces ORDER BY il_isim ASC");
        provinces = query.getResultList();
        comboProvince.getItems().setAll(provinces);
    }

    @FXML
    public void listDistrictsOfProvince() {
        // ile göre ilçe listeleme
        Query query = entityManager.createNativeQuery("SELECT ilce_isim FROM Provinces WHERE il_isim = ?1");
        query.setParameter(1, comboProvince.getValue());
        districts = query.getResultList();
        comboDistrict.getItems().clear();
        comboDistrict.getItems().addAll(districts);
    }

    @FXML
    public void closeDialog() {
        CustomerController.createCustomerDialog.close();
    }

}
