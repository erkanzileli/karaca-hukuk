package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import entity.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import main.MainClass;
import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane root;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnLawsuits;

    @FXML
    private JFXButton btnCustomers;

    @FXML
    private JFXButton btnReports;

    @FXML
    private JFXButton btnAgenda;

    @FXML
    private JFXButton btnQuit;

    @FXML
    private JFXButton btnEmployees;

    private Member member;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showDashboard();
        member = MainClass.member;
        System.out.println("HomeController.initialize");
        setButtonsForUser();
    }

    private void setButtonsForUser() {
        //Butonların ayarlanması
        /** if lawyer
         * agenda -> 0,273
         * quit -> 314
         */
        if ("Avukat".equals(member.getType()) || "Sekreter".equals(member.getType())) {
            btnReports.setVisible(false);
            btnAgenda.setLayoutY(273);
            btnEmployees.setVisible(false);
            btnQuit.setLayoutY(314);
        }
    }

    @FXML
    void showLawsuits() {
        Parent lawsuits = null;
        try {
            lawsuits = FXMLLoader.load(getClass().getResource("/fxml/lawsuits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LawsuitsController.rootpane = borderPane;
        borderPane.setCenter(lawsuits);
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
        borderPane.setCenter(dashboard);
        System.gc();
        //paneCenter.getChildren().setAll(dashboard);
    }

    @FXML
    void showCustomers() {
        Parent customers = null;
        try {
            customers = FXMLLoader.load(getClass().getResource("/fxml/customers.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        borderPane.setCenter(customers);
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
        borderPane.setCenter(reporting);
        System.gc();
    }

    @FXML
    void showProfile() {
        Parent profile = null;
        try {
            profile = FXMLLoader.load(getClass().getResource("/fxml/profile.fxml"));
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(profile);
        JFXDialog dialog = new JFXDialog(root, jfxDialogLayout, JFXDialog.DialogTransition.LEFT);
        dialog.show();
    }

    @FXML
    public void showEmployees() {
        Parent employees = null;
        try {
            employees = FXMLLoader.load(getClass().getResource("/fxml/employees.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        borderPane.setCenter(employees);
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
        borderPane.setCenter(diary);
        System.gc();
    }


}
