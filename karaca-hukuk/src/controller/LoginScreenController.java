package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {
    @FXML
    private JFXTextField login_screen_un_in;

    @FXML
    private PasswordField login_screen_pw_in;

    @FXML
    private AnchorPane login_screen_root_pane;


    @FXML
    private Label login_screen_warning_lbl;

    @FXML
    void exit(){
        System.exit(0);
    }
    @FXML
    void login() throws IOException {
        if (login_screen_un_in.getText().equals("admin") && login_screen_pw_in.getText().equals("admin")){
            // to homepage
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
            Scene scene=new Scene(root);
            Stage stage= (Stage) login_screen_root_pane.getScene().getWindow();
            stage.setWidth(1200);
            stage.setHeight(650);
            stage.centerOnScreen();
            stage.setScene(scene);
        }
        else {
            login_screen_root_pane.setStyle("-fx-border-color : red ; -fx-background-color :  #2A2C37 ; -fx-border-radius : 0.5em ; -fx-background-radius : 0.5em ; -fx-border-width : 2 ; ");
            login_screen_warning_lbl.setText("Lütfen girdilerinizden emin olun !");
        }
    }

    @FXML
    void forget(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sifre Hatırlatması");
        alert.headerTextProperty().setValue("İşlem tamamlandı !!");
        alert.setContentText("Mail adresinizi kontrol ediniz. ");
        alert.show();

    }


}
