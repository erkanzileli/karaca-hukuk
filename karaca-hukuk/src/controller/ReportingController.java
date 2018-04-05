package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportingController implements Initializable {

    public StackPane root;

    public PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ReportingController.initialize");
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("1 Yönetici", 1),
                        new PieChart.Data("4 Avukat", 4),
                        new PieChart.Data("2 Sekreter", 2),
                        new PieChart.Data("12 Bireysel Müşteri", 12),
                        new PieChart.Data("5 Kurumsal Müşteri", 5));
        pieChart.setTitle("Sistemdeki Kullanıcılar");
        pieChart.setLegendVisible(false);
        pieChart.setData(pieChartData);
    }

}
