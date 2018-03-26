package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


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
        //paneCenter.getChildren().setAll(lawsuits);
    }

    @FXML
    void showDashboard(){
        Parent dashboard = null;
        try {
            dashboard=FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        root.setCenter(dashboard);
        //paneCenter.getChildren().setAll(dashboard);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       showDashboard();
        System.out.println("HomeController.initialize");
    }


}
