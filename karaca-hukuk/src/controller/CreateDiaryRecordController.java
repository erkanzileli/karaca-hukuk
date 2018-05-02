package controller;

import entity.Agenda;
import entity.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import main.MainClass;
import model.CalendarPaneModel;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateDiaryRecordController implements Initializable {

    @FXML
    private Label lblToday;

    @FXML
    private TableView<Agenda> tableView;

    @FXML
    private TableColumn<Agenda, String> columnTitle;

    @FXML
    private TableColumn<Agenda, String> columnContent;

    private Member currentMember;

    public static CalendarPaneModel selectedPaneModel;

    private EntityManager entityManager;

    private HashMap<Integer, String> months = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentMember = MainClass.member;
        entityManager = EntityManagerUtility.getEntityManager();

        columnTitle.setCellValueFactory(new PropertyValueFactory<>("header"));
        columnContent.setCellValueFactory(new PropertyValueFactory<>("description"));

        setMonths();
        LocalDate localDate = selectedPaneModel.getDate().toLocalDate();
        lblToday.setText(String.valueOf(localDate.getDayOfMonth()) + " " + months.get(localDate.getMonth().getValue()));

        fillTable();

        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                ButtonType okButton = new ButtonType("Sil", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButton = new ButtonType("Tamam", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Bilgi");
                alert.setHeaderText("Konu: " + tableView.getSelectionModel().getSelectedItem().getHeader());
                alert.setContentText("İçerik: " + tableView.getSelectionModel().getSelectedItem().getDescription());
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(cancelButton, okButton);
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.isPresent()) {
                    if (optional.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                        Agenda agenda = entityManager.find(Agenda.class, tableView.getSelectionModel().getSelectedItem().getIdAgenda());
                        entityManager.getTransaction().begin();
                        entityManager.remove(agenda);
                        entityManager.getTransaction().commit();
                        fillTable();
                        alert.close();
                    }
                }
            }
        });
    }

    private void fillTable() {
        Query query = entityManager.createNativeQuery("Select * from Agenda Where Agenda.date=?1", Agenda.class);
        query.setParameter(1, selectedPaneModel.getDate());
        List<Agenda> result = query.getResultList();
        if (!result.isEmpty()) {
            tableView.getItems().clear();
            tableView.getItems().addAll(result);
        }
    }

    private void setMonths() {
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
    }

    @FXML
    void createNote() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Not Ekleyin");

        // Set the button types.
        ButtonType okButtonType = new ButtonType("Ekle", ButtonBar.ButtonData.OK_DONE);
        ButtonType closeButtonType = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(closeButtonType, okButtonType);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Konu");
        TextArea content = new TextArea();
        content.setPromptText("İçerik");

        gridPane.add(new Label("Konu:"), 0, 0);
        gridPane.add(title, 1, 0);
        gridPane.add(new Label("İçerik"), 0, 1);
        gridPane.add(content, 1, 1);

        dialog.getDialogPane().setContent(gridPane);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new Pair<>(title.getText(), content.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(pair -> {

            String titleText = pair.getKey().trim();
            String contentText = pair.getValue().trim();

            if (!"".equals(titleText) && !"".equals(contentText)) {
                Agenda record = new Agenda(currentMember.getIdMember(), titleText, contentText, selectedPaneModel.getDate());
                entityManager.getTransaction().begin();
                entityManager.persist(record);
                entityManager.getTransaction().commit();
                fillTable();
                dialog.close();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Eksik veri girişi.");
                alert.setHeaderText(null);
                alert.setContentText("Eksik kısım bırakmayınız.");
                alert.show();
            }
        });
    }

}
