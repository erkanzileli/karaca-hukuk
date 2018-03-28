package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

public class FirstScreenController {
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
    void action(MouseEvent event){
    if (event.getSource() == login_screen_close){
        System.exit(0);
    }
    else if(event.getSource() == login_screen_login_btn){
            if(login_screen_un_in.getText().equals("admin") && login_screen_pw_in.getText().equals("admin")){

            }else{

            }
        }
    }

}
