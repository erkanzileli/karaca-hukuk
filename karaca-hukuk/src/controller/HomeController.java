package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private BorderPane root;

    @FXML
    private JFXButton btnLawsuits;

    @FXML
    private Pane paneCenter;

    @FXML
    void showLawsuits() {
        Parent lawsuits = null;
        try {
            lawsuits = FXMLLoader.load(getClass().getResource("/fxml/lawsuits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setCenter(lawsuits);
        System.gc();
        //paneCenter.getChildren().setAll(lawsuits);
    }

    @FXML
    void showDashboard() {
        Parent dashboard = null;
        try {
            dashboard = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        root.setCenter(dashboard);
        System.gc();
        //paneCenter.getChildren().setAll(dashboard);
    }

    @FXML
    void showAccounting() {
        Parent accounting = null;
        try {
            accounting = FXMLLoader.load(getClass().getResource("/fxml/accounting.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        root.setCenter(accounting);
        System.gc();
    }
    
    @FXML
    void powerOff(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistemden Çıkış Yapılıyor!");
        alert.setContentText("Sistemi kapatmak istediğinize emin misiniz?");
        alert.headerTextProperty().setValue("Uyarı!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(1);
        }
    }

    @FXML
    void showReporting(ActionEvent event) {
        Parent reporting = null;
        try {
            reporting = FXMLLoader.load(getClass().getResource("/fxml/reporting.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        root.setCenter(reporting);
        System.gc();
    }

    @FXML
    void showDiary(ActionEvent event) {
        Parent diary = null;
        try {
            diary = FXMLLoader.load(getClass().getResource("/fxml/diary.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        root.setCenter(diary);
        System.gc();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showDashboard();
        System.out.println("HomeController.initialize");
    }


}
