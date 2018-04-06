package controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.effect.Effect;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportingController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private NumberAxis axisX;

    @FXML
    private NumberAxis axisY;

    @FXML
    private JFXCheckBox checkLawsuits;

    @FXML
    private JFXCheckBox checkIncoming;

    @FXML
    private JFXCheckBox checkCustomers;

    private XYChart.Series<Number, Number> lawsuitSeries;

    private XYChart.Series<Number, Number> incomingSeries;

    private XYChart.Series<Number, Number> customerSeries;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ReportingController.initialize");

        generatePieChart();

        axisX.setLabel("Zaman");
        axisY.setLabel("Miktar");

        lawsuitSeries = new XYChart.Series<>();
        lawsuitSeries.setName("Davalar");

        incomingSeries = new XYChart.Series<>();
        incomingSeries.setName("Gelir");

        customerSeries = new XYChart.Series<>();
        customerSeries.setName("Müşteriler");
    }

    private void generatePieChart() {
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

    @FXML
    public void setCheckLawsuits() {
        if (checkLawsuits.isSelected()) {
            setLawsuitSeries();
            lineChart.getData().add(lawsuitSeries);
        } else {
            lineChart.getData().remove(lawsuitSeries);
        }
    }

    private void setLawsuitSeries() {
        //Verilerin çekilmesi ve düzene sokulması
        lawsuitSeries.getData().add(new XYChart.Data<>(1,1));
        lawsuitSeries.getData().add(new XYChart.Data<>(2,2));
        lawsuitSeries.getData().add(new XYChart.Data<>(3,3));
        lawsuitSeries.getData().add(new XYChart.Data<>(4,2));
        lawsuitSeries.getData().add(new XYChart.Data<>(5,5));
        lawsuitSeries.getData().add(new XYChart.Data<>(6,4));
    }

    @FXML
    public void setCheckIncoming() {
        if (checkIncoming.isSelected()) {
            setIncomingSeries();
            lineChart.getData().add(incomingSeries);
        } else {
            lineChart.getData().remove(incomingSeries);
        }
    }

    private void setIncomingSeries() {
        incomingSeries.getData().add(new XYChart.Data<>(1,5));
        incomingSeries.getData().add(new XYChart.Data<>(2,10));
        incomingSeries.getData().add(new XYChart.Data<>(3,12));
    }



    @FXML
    public void setCheckCustomers() {
        if (checkCustomers.isSelected()) {
            lineChart.getData().add(customerSeries);
            setCustomerSeries();
        } else {
            lineChart.getData().remove(customerSeries);
        }
    }

    private void setCustomerSeries() {
        customerSeries.getData().add(new XYChart.Data<>(1,2));
        customerSeries.getData().add(new XYChart.Data<>(2,4));
        customerSeries.getData().add(new XYChart.Data<>(3,5));
        customerSeries.getData().add(new XYChart.Data<>(4,6));
        customerSeries.getData().add(new XYChart.Data<>(5,7));
        customerSeries.getData().add(new XYChart.Data<>(6,10));
    }

}
