package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;

public class DiaryController implements Initializable {

    @FXML
    public StackPane root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent calendar = null;
        try {
            calendar= FXMLLoader.load(getClass().getResource("/fxml/calendar.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().set(0,calendar);
    }
}
