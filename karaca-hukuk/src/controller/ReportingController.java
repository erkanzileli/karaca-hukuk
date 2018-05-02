package controller;

import entity.Customer;
import entity.Lawsuit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReportingController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private PieChart pieChartCustomers;

    @FXML
    private PieChart pieChartLawsuits;

    private EntityManager entityManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();
        generateCustomersPieChart();
        generateLawsuitsPieChart();
    }

    private void generateCustomersPieChart() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Customer", Customer.class);
        List<Customer> result = query.getResultList();
        if (!result.isEmpty()) {
            int individualCustomer = 0;
            int enterpriseCustomer = 0;
            for (Customer customer : result) {
                if (customer.getType().equals("Bireysel"))
                    individualCustomer++;
                else
                    enterpriseCustomer++;
            }
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data(individualCustomer + " Bireysel Müşteri", individualCustomer),
                            new PieChart.Data(enterpriseCustomer + " Kurumsal Müşteri", enterpriseCustomer));
            pieChartCustomers.setTitle("Sistemdeki Kullanıcılar");
            pieChartCustomers.setData(pieChartData);
        }
    }

    private void generateLawsuitsPieChart() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Lawsuit", Lawsuit.class);
        List<Lawsuit> result = query.getResultList();
        if (!result.isEmpty()) {
            int active = 0;
            int passive = 0;
            int pending = 0;
            for (Lawsuit lawsuit : result) {
                switch (lawsuit.getStatus()) {
                    case "Aktif":
                        active++;
                        break;
                    case "Pasif":
                        passive++;
                        break;
                    default:
                        pending++;
                        break;
                }
            }
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data(active + " Aktif Dava", active),
                            new PieChart.Data(pending + " Dava Beklemede", pending),
                            new PieChart.Data(passive + " Pasif Dava", passive));
            pieChartLawsuits.setTitle("Davaların Durumu");
            pieChartLawsuits.setData(pieChartData);
        }
    }

}
