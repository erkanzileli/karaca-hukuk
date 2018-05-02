package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateEmployeeController implements Initializable {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSurname;

    @FXML
    private JFXTextField txtTC;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtMobilePhone;

    @FXML
    private JFXComboBox<String> comboPosition;

    @FXML
    private JFXComboBox<String> comboSex;

    @FXML
    private JFXPasswordField txtPassword;

    private EntityManager entityManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();
        comboPosition.getItems().addAll("Avukat", "Sekreter");
        comboSex.getItems().addAll("Kadın", "Erkek");
    }

    @FXML
    void closeDialog() {
        EmployeeController.getCreateEmployeeDialog().close();
    }

    @FXML
    void save(ActionEvent event) {
        String name = txtName.getText().trim();
        String surname = txtSurname.getText().trim();
        String email = txtEmail.getText().trim();
        String gender = comboSex.getValue();
        String type = comboPosition.getValue();
        String password = txtPassword.getText().trim();
        if (txtTC.getText().trim().length() == 11) {
            long tc = 0;
            try {
                tc = Long.parseLong(txtTC.getText().trim());
            } catch (Exception ignored) {
            }
            long phoneNumber = 0;
            if (txtMobilePhone.getText().trim().length() > 0) {
                try {
                    phoneNumber = Long.parseLong(txtMobilePhone.getText().trim());
                } catch (Exception e) {
                    createAlertDialog(Alert.AlertType.ERROR, "Girdi Hatası", null, "Telefon numarası nümerik olmalıdır.").show();
                }
            }
            if (txtMobilePhone.getText().trim().length() > 13) {
                createAlertDialog(Alert.AlertType.ERROR, "Girdi Hatası", null, "Telefon numarası 13 haneyi geçemez.").show();
            }
            if (tc != 0) {
                Query query = entityManager.createNativeQuery("SELECT * FROM Member WHERE tc=?1");
                query.setParameter(1, tc);
                List result = query.getResultList();
                if (result.isEmpty()) {
                    entityManager.getTransaction().begin();
                    entityManager.persist(new Member(tc, name, surname, type, password, email, gender, phoneNumber));
                    entityManager.getTransaction().commit();
                    closeDialog();
                } else {
                    createAlertDialog(Alert.AlertType.INFORMATION, "Hata", "Bu kullanıcı zaten kayıtlı.", "Bu T.C. kimlik numarasına sahip bir personel zaten kayıtlı.").show();
                }
            } else {
                createAlertDialog(Alert.AlertType.ERROR, "Girdi Hatası", null, "T.C. kimlik numarası nümerik olmalı.").show();
            }
        } else {
            createAlertDialog(Alert.AlertType.ERROR, "Girdi Hatası", null, "T.C. kimlik numarası 11 haneden oluşmalı.").show();
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
