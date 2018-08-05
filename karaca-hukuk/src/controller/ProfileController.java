package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import entity.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.MainClass;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private JFXToggleButton toggleReplaceMode;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSurname;

    @FXML
    private JFXTextField txtTC;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXPasswordField txtOldPasswd;

    @FXML
    private JFXPasswordField txtNewPasswd;

    @FXML
    private JFXPasswordField txtNewPasswdAgain;

    @FXML
    private JFXButton btnSave;

    private EntityManager entityManager;

    private Member member;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();
        member = MainClass.member;
        fillFields();
    }

    private void fillFields() {
        txtName.setText(member.getName());
        txtSurname.setText(member.getSurname());
        txtTC.setText(String.valueOf(member.getTc()));
        txtPhone.setText(String.valueOf(member.getPhoneNumber()));
        txtEmail.setText(member.getEmail());
    }

    @FXML
    void save() {
        String name = txtName.getText().trim();
        String surname = txtSurname.getText().trim();
        String email = txtEmail.getText().trim();
        String phoneString = txtPhone.getText().trim();
        if (!phoneString.equals(member.getPhoneNumber())) {
            if (phoneString.length() < 13) {
                long phone = 0;
                try {
                    phone = Long.parseLong(phoneString);
                } catch (Exception ignored) {
                }
                if (phone != 0) {
                    member = entityManager.find(Member.class, member.getIdMember());
                    entityManager.getTransaction().begin();
                    member.setName(name);
                    member.setSurname(surname);
                    member.setEmail(email);
                    member.setPhoneNumber(phone);
                    entityManager.getTransaction().commit();
                    MainClass.member = member;
                    fillFields();
                    Optional<ButtonType> optional = createAlertDialog(Alert.AlertType.INFORMATION,"Bilgi","İşlem başarılı.",null).showAndWait();
                    if (optional.isPresent()){
                        if (optional.get()==ButtonType.OK){
                            HomeController.dialogProfile.close();
                        }
                    }
                } else {
                    createAlertDialog(Alert.AlertType.ERROR, "Hatalı giriş", null, "Telefon numarası nümerik olmalıdır.").show();
                }
            } else {
                createAlertDialog(Alert.AlertType.ERROR, "Hatalı giriş", null, "Telefon numarası 13 haneyi geçemez.").show();
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

    @FXML
    public void toggleReplace() {
        if (toggleReplaceMode.isSelected()) {
            btnSave.setDisable(false);
            txtName.setEditable(true);
            txtSurname.setEditable(true);
            txtEmail.setEditable(true);
            txtOldPasswd.setEditable(true);
            txtNewPasswd.setEditable(true);
            txtNewPasswdAgain.setEditable(true);
        } else {
            btnSave.setDisable(true);
            txtName.setEditable(false);
            txtSurname.setEditable(false);
            txtEmail.setEditable(false);
            txtOldPasswd.setEditable(false);
            txtNewPasswd.setEditable(false);
            txtNewPasswdAgain.setEditable(false);
        }
    }

}
