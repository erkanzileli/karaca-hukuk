package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginScreenController {
    @FXML
    private JFXTextField login_screen_un_in;

    @FXML
    private PasswordField login_screen_pw_in;

    @FXML
    private JFXButton login_screen_login_btn;

    @FXML
    private JFXButton login_screen_forget_btn;

    @FXML
    private MaterialDesignIconView login_screen_close;


    @FXML
    private AnchorPane login_screen_root_pane;


    @FXML
    private Label login_screen_warning_lbl;

    @FXML
    void exit(MouseEvent event){
        System.exit(0);
    }
    @FXML
    void login(){
        if (login_screen_un_in.getText().equals("admin") && login_screen_pw_in.getText().equals("admin")){
            // to homepage
        }
        else {
            login_screen_root_pane.setStyle("-fx-border-color : red ; -fx-background-color :  #2A2C37 ; -fx-border-radius : 0.5em ; -fx-background-radius : 0.5em ; -fx-border-width : 2 ; ");
            login_screen_warning_lbl.setText("Lütfen girdilerinizden emin olun !");
        }
    }

}
