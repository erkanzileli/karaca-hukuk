package controller;

import com.jfoenix.controls.JFXTextField;
import entity.Log;
import entity.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.MainClass;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    private JFXTextField txtTC;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private AnchorPane login_screen_root_pane;

    @FXML
    private Label login_screen_warning_lbl;

    @FXML
    private Label afterDialog;

    private EntityManager entityManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();
    }

    @FXML
    void exit() {
        System.exit(0);
    }

    @FXML
    void login() {
        String tcString = txtTC.getText().trim();
        String password = txtPassword.getText().trim();
        if (tcString.equals("") || password.equals("") || tcString.length() != 11) {
            createAlertDialog(Alert.AlertType.ERROR, "Hata", null, "Kullanıcı adı veya şifre eksik.").show();
        } else {
            // başlangıç değeri gerekli
            long tc = 0;
            try {
                tc = Long.parseLong(tcString);
            } catch (Exception e) {
                //
            }
            // tc değeri hatasız ve eksiksiz alındı ise
            if (tc != 0) {
                Query query = entityManager.createNativeQuery("Select * from Member where tc=?1 and password=?2",
                        Member.class);
                query.setParameter(1, tc);
                query.setParameter(2, password);
                List<Member> list = query.getResultList();
                if (!list.isEmpty()) {
                    MainClass.member = list.get(0);
                    entityManager.getTransaction().begin();
                    entityManager.persist(new Log(new Date(), "test"));
                    entityManager.getTransaction().commit();
                    loadHome();
                } else {
                    // member not found
                    createAlertDialog(Alert.AlertType.INFORMATION, "Bilgi", null, "Böyle bir kullanıcı bulunamadı.")
                            .show();
                }
            } else {
                createAlertDialog(AlertType.ERROR, "Hata", null, "T.C. kimlik numarası nümerik olmalıdır.").show();
            }
        }
    }

    private void loadHome() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Anasayfa");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.show();
        txtTC.getScene().getWindow().hide();
    }

    private Alert createAlertDialog(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.headerTextProperty().setValue(header);
        alert.titleProperty().set(title);
        alert.contentTextProperty().set(content);
        return alert;
    }

    @FXML
    void forget() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Sifremi Unutttum !");
        dialog.setHeaderText("Lütfen kullanıcı adınızı giriniz ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            login_screen_root_pane.setStyle(
                    "-fx-border-color : red ; -fx-background-color :  #2A2C37 ; -fx-border-radius : 0.5em ; -fx-background-radius : 0.5em ; -fx-border-width : 2 ; ");
            login_screen_warning_lbl.setText("Unutulan sifre icin mailinizi kontrol ediniz !");
            txtTC.setText(result.get());
            txtPassword.clear();
        }

    }

}
