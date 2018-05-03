package controller;

import entity.Agenda;
import entity.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class DashboardController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private Pane contentRoot;

    @FXML
    private TableView<LogContainer> tableLogs;


    public static class LogContainer {
        private String date;
        private String description;

        public LogContainer(String date, String description) {
            this.date = date;
            this.description = description;
        }

        public String getDate() {

            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @FXML
    private TableColumn<Log, java.util.Date> columnLogDate;

    @FXML
    private TableColumn<Log, String> columnActivity;

    @FXML
    private TableView<Agenda> tableAgenda;

    @FXML
    private TableColumn<Agenda, Date> columnAgendaDate;

    @FXML
    private TableColumn<Agenda, String> columnHeader;

    @FXML
    private TableColumn<Agenda, String> columnDescription;

    @FXML
    private ListView<String> logList;

    private EntityManager entityManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();

        setAgendaTableSettings();
        fillAgendaTable();
/*
        setLogTableSettings();
        fillLogTable();
  */  }

    private void setLogTableSettings() {
        columnLogDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnActivity.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    private void fillLogTable() {
        TypedQuery<Log> query = (TypedQuery<Log>) entityManager.createNativeQuery("SELECT * FROM Log WHERE createDate BETWEEN ?1 AND ?2", Log.class);
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -1);
        dt = c.getTime();
        query.setParameter(1, sdfDate.format(dt));
        c.add(Calendar.DATE, 2);
        dt = c.getTime();
        query.setParameter(2, sdfDate.format(dt));
        List<Log> logList = query.getResultList();

        tableLogs.getItems().clear();

        logList.forEach(l -> {
            tableLogs.getItems().add(new LogContainer(sdfDate.format(l.getDate()),l.getDescription()));
        });

    }

    private void setAgendaTableSettings() {
        columnAgendaDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnHeader.setCellValueFactory(new PropertyValueFactory<>("header"));
        tableAgenda.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                ButtonType okButton = new ButtonType("Sil", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButton = new ButtonType("Tamam", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bilgi");
                alert.setHeaderText("Konu: " + tableAgenda.getSelectionModel().getSelectedItem().getHeader());
                alert.setContentText("İçerik: " + tableAgenda.getSelectionModel().getSelectedItem().getDescription());
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(cancelButton, okButton);
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.isPresent()) {
                    if (optional.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                        Agenda agenda = entityManager.find(Agenda.class, tableAgenda.getSelectionModel().getSelectedItem().getIdAgenda());
                        entityManager.getTransaction().begin();
                        entityManager.remove(agenda);
                        entityManager.getTransaction().commit();
                        fillAgendaTable();
                        alert.close();
                    }
                }
            }
        });
    }

    private void fillAgendaTable() {
        TypedQuery<Agenda> query = (TypedQuery<Agenda>) entityManager.createNativeQuery("SELECT * FROM Agenda WHERE Agenda.date BETWEEN ?1 AND ?2 ORDER BY Agenda.date ASC", Agenda.class);
        query.setParameter(1, java.sql.Date.valueOf(LocalDate.now().minusDays(7)));
        query.setParameter(2, java.sql.Date.valueOf(LocalDate.now().plusDays(1)));
        List<Agenda> agendaList = query.getResultList();
        tableAgenda.getItems().clear();
        tableAgenda.getItems().addAll(agendaList);
    }


    @FXML
    void refresh() {

    }
}
