package controller;

import com.jfoenix.controls.*;
import entity.Adress;
import entity.Customer;
import entity.Lawsuit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import main.MainClass;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerDetailController implements Initializable {
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
    private JFXButton btnUpdate;

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

    @FXML
    private JFXToggleButton toggleCorrection;

    @FXML
    private JFXButton btnDelete;

    public static Customer selectedCustomer;

    private List<String> provinces = null;

    private Adress selectedCustomerAdress;

    private ToggleGroup individualOrEnterprise;

    private EntityManager entityManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // toggle-group-settings
        individualOrEnterprise = new ToggleGroup();
        radioEnterprise.setToggleGroup(individualOrEnterprise);
        radioSingular.setToggleGroup(individualOrEnterprise);
        comboSex.getItems().addAll("Kadın", "Erkek");
        radioSingular.setDisable(true);
        radioEnterprise.setDisable(true);
        if (MainClass.member.getType().equals("Sekreter")) {
            btnDelete.setVisible(false);
            btnUpdate.setVisible(false);
            toggleCorrection.setVisible(false);
        }
        if ("Bireysel".equals(selectedCustomer.getType())) {
            individualOrEnterprise.selectToggle(radioSingular);
            selectIndividual();
            radioEnterprise.setDisable(true);
            radioSingular.setDisable(true);
            textName.setText(selectedCustomer.getName());
            textSurname.setText(selectedCustomer.getSurname());
            textPhone.setText(String.valueOf(selectedCustomer.getPhoneNumber()));
            txtIdentityNo.setText(String.valueOf(selectedCustomer.getTc()));
            comboSex.setPromptText(selectedCustomer.getGender());
            comboSex.setValue(selectedCustomer.getGender());
            comboSex.setDisable(true);
        } else {
            individualOrEnterprise.selectToggle(radioEnterprise);
            selectEnterprise();
            textName.setText(selectedCustomer.getName());
            textSurname.setText(selectedCustomer.getSurname());
            textPhone.setText(String.valueOf(selectedCustomer.getPhoneNumber()));
            txtIdentityNo.setText(String.valueOf(selectedCustomer.getTaxNumber()));
        }
        entityManager = EntityManagerUtility.getEntityManager();
        TypedQuery<Adress> query = (TypedQuery<Adress>) entityManager
                .createNativeQuery("Select * from Adress WHERE idCustomer = ?1", Adress.class);
        query.setParameter(1, selectedCustomer.getIdCustomer());
        List<Adress> result = query.getResultList();
        if (!result.isEmpty()) {
            selectedCustomerAdress = result.get(0);

            comboProvince.setPromptText(selectedCustomerAdress.getCounty());
            comboProvince.setDisable(true);

            comboDistrict.setPromptText(selectedCustomerAdress.getCity());
            comboDistrict.setDisable(true);

            textAdressPhone.setText(String.valueOf(selectedCustomerAdress.getPhoneNumber()));
            textStreet.setText(selectedCustomerAdress.getStreet());
            txtPostalCode.setText(String.valueOf(selectedCustomerAdress.getPostalCode()));
            textDoorNumber.setText(String.valueOf(selectedCustomerAdress.getDoorNumber()));
        }
    }

    private void fillComboProvinces() {
        Query query = entityManager.createNativeQuery("SELECT DISTINCT il_isim FROM Provinces ORDER BY il_isim ASC");
        List<String> provinces = query.getResultList();
        comboProvince.getItems().setAll(provinces);
    }

    @FXML
    void changeCorrectionMode() {
        if (toggleCorrection.isSelected()) {
            if (provinces == null) {
                fillComboProvinces();
            }
            btnUpdate.setDisable(false);
            comboProvince.setDisable(false);
            comboDistrict.setDisable(false);
            textAdressPhone.setEditable(true);
            textStreet.setEditable(true);
            textDoorNumber.setEditable(true);
            txtPostalCode.setEditable(true);
            textName.setEditable(true);
            textSurname.setEditable(true);
            textPhone.setEditable(true);
            txtIdentityNo.setEditable(true);
            comboSex.setDisable(false);
        } else {
            btnUpdate.setDisable(true);
            comboProvince.setDisable(true);
            comboDistrict.setDisable(true);
            textAdressPhone.setEditable(false);
            textStreet.setEditable(false);
            textDoorNumber.setEditable(false);
            txtPostalCode.setEditable(false);
            textName.setEditable(false);
            textSurname.setEditable(false);
            textPhone.setEditable(false);
            txtIdentityNo.setEditable(false);
            comboSex.setDisable(true);
        }
    }

    @FXML
    void closeDialog() {
        CustomerController.customerDetailsDialog.close();
    }

    @FXML
    void listDistrictsOfProvince() {
        Query query = entityManager.createNativeQuery("SELECT ilce_isim FROM Provinces WHERE il_isim = ?1");
        query.setParameter(1, comboProvince.getValue());
        List<String> districts = query.getResultList();
        comboDistrict.getItems().clear();
        comboDistrict.getItems().addAll(districts);
    }

    @FXML
    void save() {
        RadioButton selected = (RadioButton) individualOrEnterprise.selectedToggleProperty().getValue();
        // adress
        String province = comboProvince.getValue();
        String district = comboDistrict.getValue();

        String street = textStreet.getText().trim();
        String doorNumber = textDoorNumber.getText().trim();
        int postalCode = 0;
        long adressPhoneNumber = 0;
        // general
        String type = selected.getText();
        String name = textName.getText().trim();
        String surname = textSurname.getText().trim();
        long phoneNumber = 0;
        if (txtPostalCode.getText().trim().length() > 0) {
            try {
                postalCode = Integer.parseInt(txtPostalCode.getText().trim());
            } catch (Exception e) {
                // int değil
                createAlertDialog(Alert.AlertType.ERROR, "Hata", "Hatalı değer girildi.", "Posta kodu nümerik olmalıdır.")
                        .show();
            }
        }
        if (textPhone.getText().trim().length() > 0) {

            try {
                phoneNumber = Long.parseLong(textPhone.getText().trim());
            } catch (Exception e) {
                // long değil
                createAlertDialog(Alert.AlertType.ERROR, "Hata", "Hatalı değer girildi.",
                        "Telefon numarası nümerik olmalıdır.").show();
            }
        }
        if (textAdressPhone.getText().trim().length() > 0) {
            try {
                adressPhoneNumber = Long.parseLong(textAdressPhone.getText().trim());
            } catch (Exception e) {
                // long değil
                createAlertDialog(Alert.AlertType.ERROR, "Hata", "Hatalı değer girildi.",
                        "Telefon numarası nümerik olmalıdır.").show();
            }
        }
        // bireysel
        if ("Bireysel".equals(type)) {
            String tcString = txtIdentityNo.getText().trim();
            // T.C. 11 haneli olmalı
            if (tcString.length() != 11) {
                createAlertDialog(Alert.AlertType.INFORMATION, "Bilgi", null,
                        "T.C. numarası 11 rakamdan oluşmalıdır.").show();
            } else { // T.C. 11 haneli
                long tc = 0;
                String gender = comboSex.getValue();
                try {
                    tc = Long.parseLong(tcString);
                } catch (Exception e) {
                    createAlertDialog(Alert.AlertType.INFORMATION, "Bilgi", null, "T.C. nümerik olmalıdır.").show();
                }
                if (tc != 0) { // T.C. doğru alındı
                    entityManager.getTransaction().begin();
                    Customer customer = entityManager.find(Customer.class, selectedCustomer.getIdCustomer());
                    customer.setName(name);
                    customer.setSurname(surname);
                    customer.setPhoneNumber(phoneNumber);
                    customer.setTc(tc);
                    customer.setGender(gender);
                    entityManager.getTransaction().commit();
                    if (selectedCustomerAdress==null){
                        entityManager.getTransaction().begin();
                        entityManager.persist(new Adress(district, province, street, postalCode, phoneNumber, doorNumber, type, selectedCustomer.getIdCustomer()));
                        entityManager.getTransaction().commit();
                    } else {    //var ise
                        // update address
                        Adress adress = entityManager.find(Adress.class,selectedCustomerAdress.getIdAdress());
                        entityManager.getTransaction().begin();
                        adress.setCounty(province);
                        adress.setCity(district);
                        adress.setStreet(street);
                        adress.setDoorNumber(doorNumber);
                        adress.setPostalCode(postalCode);
                        adress.setPhoneNumber(adressPhoneNumber);
                        adress.setType(type);
                        // save the address
                        entityManager.getTransaction().commit();
                    }
                    successNotification();
                }
            }
        } else { // kurumsal
            String taxNumberString = txtIdentityNo.getText().trim();
            if (taxNumberString.length() != 10) {
                createAlertDialog(Alert.AlertType.ERROR, "Eksik bilgi.", null, "Vergi numarası 10 rakamdan oluşmalıdır.")
                        .show();
            } else {
                long taxNumber = 0;
                try {
                    taxNumber = Long.parseLong(txtIdentityNo.getText().trim());
                } catch (Exception e) {
                    createAlertDialog(Alert.AlertType.INFORMATION, "Bilgi", null, "Verigi numarası nümerik olmalıdır.")
                            .show();
                }
                if (taxNumber != 0) { //tax number has been take true
                    // update customer
                    entityManager.getTransaction().begin();
                    Customer customer = entityManager.find(Customer.class, selectedCustomer.getIdCustomer());
                    customer.setName(name);
                    customer.setSurname(surname);
                    customer.setPhoneNumber(phoneNumber);
                    customer.setTaxNumber(taxNumber);
                    entityManager.getTransaction().commit();
                    // update address
                    entityManager.getTransaction().begin();
                    Adress adress = entityManager.find(Adress.class, selectedCustomerAdress.getIdAdress());
                    adress.setCounty(province);
                    adress.setCity(district);
                    adress.setStreet(street);
                    adress.setDoorNumber(doorNumber);
                    adress.setPostalCode(postalCode);
                    adress.setPhoneNumber(adressPhoneNumber);
                    adress.setType(type);
                    //save
                    entityManager.getTransaction().commit();
                    successNotification();
                }
            }
        }
    }

    @FXML
    void delete() {
        ButtonType okButton = new ButtonType("Evet", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uyarı");
        alert.setHeaderText("Bir müşteri siliyorsunuz.");
        alert.setContentText("Bu müşteriye ait kayıtları silmek istiyor musunuz?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(cancelButton, okButton);
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent()) {
            if (optional.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                try {
                    entityManager.getTransaction().begin();
                    entityManager.remove(entityManager.find(Adress.class, selectedCustomerAdress.getIdAdress()));
                    entityManager.getTransaction().commit();
                } catch (NullPointerException e) {
                    entityManager.getTransaction().commit();
                }
                String isRemoved = "NO";
                try {

                    Query query = entityManager.createNativeQuery("SELECT * FROM Lawsuit Where idCustomer=?1", Lawsuit.class);
                    query.setParameter(1, selectedCustomer.getIdCustomer());
                    if (!query.getResultList().isEmpty()) {
                        Customer customer = entityManager.find(Customer.class, selectedCustomer.getIdCustomer());
                        entityManager.getTransaction().begin();
                        customer.setIsRemoved("YES");
                        entityManager.getTransaction().commit();
                        isRemoved = "YES";
                    }
                } catch (Exception e) {

                }
                if (isRemoved.equals("NO")) {
                    Customer customer = entityManager.find(Customer.class, selectedCustomer.getIdCustomer());
                    entityManager.getTransaction().begin();
                    entityManager.remove(customer);
                    entityManager.getTransaction().commit();
                }
                closeDialog();
                //refreshData
            }
        }
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

    private void successNotification() {
        ButtonType okButton = new ButtonType("Müşterilere geri dön.", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Burada kal.", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bilgi");
        alert.setHeaderText("İşlem başarılı.");
        alert.setContentText("Güncelleme işlemi gerçekleşti.");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(cancelButton, okButton);
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent()) {
            if (optional.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                closeDialog();
                //refreshData
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
}
