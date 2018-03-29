package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;

public class DiaryController implements Initializable {

    @FXML
    private StackPane root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent calendar = new FullCalendarView(YearMonth.now()).getView();
        root.getChildren().setAll(calendar);
    }
}
