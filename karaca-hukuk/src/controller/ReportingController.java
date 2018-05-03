package controller;

import com.jfoenix.controls.JFXDatePicker;
import entity.Customer;
import entity.Gelir;
import entity.Gider;
import entity.Lawsuit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReportingController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private PieChart pieChartCustomers;

    @FXML
    private PieChart pieChartLawsuits;

    @FXML
    private TableView<Gelir> tableGelir;

    @FXML
    private TableView<Gider> tableGider;

    @FXML
    private TableColumn<?, ?> columnGelirDate;

    @FXML
    private TableColumn<?, ?> columnGelirAmount;

    @FXML
    private TableColumn<Gider, Date> columnGiderDate;

    @FXML
    private TableColumn<Gider, Double> columnGiderAmount;

    @FXML
    private JFXDatePicker dateStart;

    @FXML
    private JFXDatePicker dateEnd;

    @FXML
    private Label lblTotalGelir;

    @FXML
    private Label lblTotalGider;

    private EntityManager entityManager;

    private List<Gelir> gelirler;

    private List<Gider> giderler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();

        columnGelirDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        columnGelirAmount.setCellValueFactory(new PropertyValueFactory<>("tutar"));

        columnGiderDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        columnGiderAmount.setCellValueFactory(new PropertyValueFactory<>("tutar"));

        generateCustomersPieChart();
        generateLawsuitsPieChart();
        fillGelirTable();
        //2 tık ile silinsin mi
        fillGiderTable();


    }

    private void fillGelirTable() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Gelir", Gelir.class);
        List<Gelir> result = query.getResultList();
        gelirler = new ArrayList<>();
        if (!result.isEmpty()) {
            gelirler = result;
            tableGelir.getItems().setAll(gelirler);
        }
        setLabelGelir();
    }

    private void fillGiderTable() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Gider", Gider.class);
        List<Gider> result = query.getResultList();
        giderler = new ArrayList<>();
        if (!result.isEmpty()) {
            giderler = result;
            tableGider.getItems().setAll(giderler);
        }
        setLabelGider();
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
                            new PieChart.Data(individualCustomer + " Bireysel", individualCustomer),
                            new PieChart.Data(enterpriseCustomer + " Kurumsal", enterpriseCustomer));
            pieChartCustomers.setTitle("Müşteriler");
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

    @FXML
    void createGider() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Yeni gider kaydı");
        textInputDialog.headerTextProperty().set(null);
        textInputDialog.setContentText("Tutar girin");
        Optional<String> optionalButtonType = textInputDialog.showAndWait();
        optionalButtonType.ifPresent(s -> {
            if (s.trim().length() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Boş veri kaydedemezsiniz!");
                alert.setTitle("Hata");
                alert.setHeaderText("");
                alert.show();
            } else {
                double d = 0;
                try {
                    d = Double.parseDouble(s);
                } catch (Exception ignored) {

                }
                if (d != 0) {
                    entityManager.getTransaction().begin();
                    entityManager.persist(new Gider(Date.valueOf(LocalDate.now()), d));
                    entityManager.getTransaction().commit();
                    fillGiderTable();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Sayısal değer girin!");
                    alert.setTitle("Hata");
                    alert.setHeaderText("");
                    alert.show();
                }
            }
        });
    }

    @FXML
    void doFilter() {
        if (dateStart.getValue() != null && dateEnd.getValue() != null) {
            Query query = entityManager.createNativeQuery("Select * from Gelir Where createDate BETWEEN ?1 AND ?2 ORDER BY createDate DESC", Gelir.class);
            query.setParameter(1, dateStart.getValue());
            query.setParameter(2, dateEnd.getValue());
            List<Gelir> result = query.getResultList();
            tableGelir.getItems().removeAll(gelirler);
            if (!result.isEmpty()) {
                gelirler = result;
                tableGelir.getItems().addAll(gelirler);
            } else {
                gelirler = new ArrayList<>();
            }
            query = entityManager.createNativeQuery("SELECT * FROM Gider WHERE createDate BETWEEN  ?1 and ?2 order by createDate desc", Gider.class);
            query.setParameter(1, dateStart.getValue());
            query.setParameter(2, dateEnd.getValue());
            List<Gider> result1 = query.getResultList();
            tableGider.getItems().removeAll(giderler);
            if (!result.isEmpty()) {
                giderler = result1;
                tableGider.getItems().addAll(giderler);
            } else {
                giderler = new ArrayList<>();
            }
            setLabelGelir();
            setLabelGider();
        }
    }

    private void setLabelGelir() {
        if (gelirler.size() == 0)
            lblTotalGelir.setText(String.valueOf("Toplam Gelir : " + 0));
        else {
            double d = gelirler.stream().mapToDouble(Gelir::getTutar).sum();
            lblTotalGelir.setText(String.valueOf("Toplam Gelir : " + d));
        }
    }

    private void setLabelGider() {
        if (giderler.size() == 0)
            lblTotalGider.setText(String.valueOf("Toplam Gider : " + 0));
        else {
            double d = giderler.stream().mapToDouble(Gider::getTutar).sum();
            lblTotalGider.setText(String.valueOf("Toplam Gider : " + d));
        }
    }

}
