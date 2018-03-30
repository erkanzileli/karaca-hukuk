package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private GridPane calendarGridPane;

    @FXML
    private Label lblYear;

    @FXML
    private Label lblMonth;
    @FXML
    private JFXButton btnBackYear;

    @FXML
    private JFXButton btnForwardYear;

    @FXML
    private JFXButton btnBackMonth;

    @FXML
    private JFXButton btnForwardMonth;

    private ArrayList<Pane> allCalendarDays = new ArrayList<>(35);

    private YearMonth currentYearMonth;
    private HashMap<Integer, String> months;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        months = new HashMap<>();
        months.put(1, "Ocak");
        months.put(2, "Şubat");
        months.put(3, "Mart");
        months.put(4, "Nisan");
        months.put(5, "Mayıs");
        months.put(6, "Haziran");
        months.put(7, "Temmuz");
        months.put(8, "Ağustos");
        months.put(9, "Eylül");
        months.put(10, "Ekim");
        months.put(11, "Kasım");
        months.put(12, "Aralık");
        currentYearMonth = YearMonth.now();
        fillGridPane();
        updateCalendar(currentYearMonth);
    }

    private void fillGridPane() {
        //GridPane içine  35 adet Pane yerleştirme
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                Pane pane = new Pane();
                pane.setPrefSize(130, 120);
                calendarGridPane.add(pane, j, i);
                allCalendarDays.add(pane);
            }
        }
    }

    private void updateCalendar(YearMonth yearMonth) {
        LocalDate localDate = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonthValue(), 1);
        //Ayın 1'i pazartesi değilse bir önceki ayın son pazartesisine kadar git
        while (!localDate.getDayOfWeek().toString().equals("MONDAY")) {
            localDate = localDate.minusDays(1);
        }
        //Takvimi günlerin numaraları ile doldurma
        for (Pane allCalendarDay : allCalendarDays) {
            allCalendarDay.getChildren().setAll(new Text(5, 90, String.valueOf(localDate.getDayOfMonth())));
            localDate = localDate.plusDays(1);
        }
        lblYear.setText(String.valueOf(yearMonth.getYear()));
        lblMonth.setText(months.get(yearMonth.getMonthValue()));
    }

    @FXML
    void minusMonth(ActionEvent event) {
        currentYearMonth = currentYearMonth.minusMonths(1);
        updateCalendar(currentYearMonth);
    }

    @FXML
    void minusYear(ActionEvent event) {
        currentYearMonth = currentYearMonth.minusYears(1);
        updateCalendar(currentYearMonth);
    }

    @FXML
    void plusMonth(ActionEvent event) {
        currentYearMonth = currentYearMonth.plusMonths(1);
        updateCalendar(currentYearMonth);
    }

    @FXML
    void plusYear(ActionEvent event) {
        currentYearMonth = currentYearMonth.plusYears(1);
        updateCalendar(currentYearMonth);
    }
}
