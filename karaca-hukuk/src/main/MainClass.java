package main;

import entity.Member;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDateTime;

public class MainClass extends Application {


    public static Member member;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        arg0.setScene(scene);
        arg0.setTitle("Hukuk Bürosu Otomasyonu");
        arg0.initStyle(StageStyle.TRANSPARENT);
        arg0.setResizable(false);
        arg0.show();
    }
}