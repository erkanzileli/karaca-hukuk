package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import entity.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeDetailsController implements Initializable {

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
    private JFXToggleButton tglCorrection;

    @FXML
    private JFXButton btnSave;

    public static Member selectedEmployee;

    private EntityManager entityManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();
        initializeFields();
        changeCorrectionMode();
    }

    private void initializeFields() {
        btnSave.setDisable(true);

        comboPosition.setPromptText("Pozisyon");
        comboPosition.getItems().addAll("Avukat", "Sekreter");
        comboPosition.setValue(selectedEmployee.getType());
        comboPosition.setDisable(true);

        comboSex.setPromptText("Cinsiyet");
        comboSex.getItems().addAll("Kadın", "Erkek");
        comboSex.setValue(selectedEmployee.getGender());
        comboSex.setDisable(true);

        txtTC.setEditable(false);
        txtName.setText(selectedEmployee.getName());
        txtSurname.setText(selectedEmployee.getSurname());
        txtEmail.setText(selectedEmployee.getEmail());
        txtMobilePhone.setText(String.valueOf(selectedEmployee.getPhoneNumber()));
        txtTC.setText(String.valueOf(selectedEmployee.getTc()));
    }

    @FXML
    void closeDialog() {
        EmployeeController.getEmployeeDetailsDialog().close();
    }

    @FXML
    void changeCorrectionMode() {
        if (tglCorrection.isSelected()) {
            comboPosition.setDisable(false);
            comboSex.setDisable(false);
            txtEmail.setEditable(true);
            txtName.setEditable(true);
            txtSurname.setEditable(true);
            txtMobilePhone.setEditable(true);
            btnSave.setDisable(false);
        } else {
            comboPosition.setDisable(true);
            comboSex.setDisable(true);
            txtEmail.setEditable(false);
            txtName.setEditable(false);
            txtSurname.setEditable(false);
            txtMobilePhone.setEditable(false);
            btnSave.setDisable(true);
        }
    }

    @FXML
    void save() {
        String name = txtName.getText().trim();
        String surname = txtSurname.getText().trim();
        String email = txtEmail.getText().trim();
        String gender = comboSex.getValue();
        String type = comboPosition.getValue();
        long phoneNumber = 0;
        if (txtMobilePhone.getText().trim().length() > 0) {
            try {
                phoneNumber = Long.parseLong(txtMobilePhone.getText().trim());
            } catch (Exception ignored) {
            }
        }
        if (txtMobilePhone.getText().trim().length() > 13) {
            createAlertDialog(Alert.AlertType.ERROR, "Girdi Hatası", null, "Telefon numarası 12 haneyi geçemez.").show();
        }
        Member member = entityManager.find(Member.class, selectedEmployee.getIdMember());
        entityManager.getTransaction().begin();
        member.setName(name);
        member.setSurname(surname);
        member.setEmail(email);
        member.setGender(gender);
        member.setPhoneNumber(phoneNumber);
        member.setType(type);
        entityManager.getTransaction().commit();
        closeDialog();
    }


    @FXML
    void delete() {
        ButtonType okButton = new ButtonType("Evet", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uyarı");
        alert.setHeaderText("Bir personeli siliyorsunuz.");
        alert.setContentText("Bu personeli sistemden silmek istiyor musunuz?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(cancelButton, okButton);
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.isPresent()) {
            if (optional.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                Member member = entityManager.find(Member.class, selectedEmployee.getIdMember());
                entityManager.getTransaction().begin();
                entityManager.remove(member);
                entityManager.getTransaction().commit();
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
