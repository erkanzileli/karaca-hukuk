package controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.EvidenceModel;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EvidenceDetailsController implements Initializable {
    public ArrayList<EvidenceModel> tmpEvidencesDb = CreateLawsuitController.tmpDbEvidence;
    public static JFXDialog fxd2;
    public static Label lbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setValue(LocalDate.now());
        fillGridPane();
        ObservableList observableListEvidenceType = FXCollections.observableArrayList("Döküman", "Görüntü", "Ses Kaydı", "Suç Aleti");
        type.setItems(observableListEvidenceType);
    }

    private void fillGridPane() {

        int i;
        for (i = 0; i < tmpEvidencesDb.size(); i++) {
            addRowInGrid(i);
        }


    }

    private void addRowInGrid(int i) {
        Pane pane = new Pane();
        pane.setPrefSize(570, 30);
        Label lbl = new Label();
        lbl.setLayoutX(10);
        lbl.setFont(new Font(10));
        lbl.setText(i + 1 + " - " + tmpEvidencesDb.get(i).getDesc());
        lbl.setId("lbl" + i);
        Label lbl2 = new Label();
        lbl2.setLayoutX(230);
        lbl2.setFont(new Font(10));
        lbl2.setText("Tipi: " + tmpEvidencesDb.get(i).getType());
        Label lbl3 = new Label();
        lbl3.setLayoutX(375);
        lbl3.setFont(new Font(10));
        lbl3.setText("Kanıt Sahibi: " + tmpEvidencesDb.get(i).getFromWho());


        pane.getChildren().addAll(lbl, lbl2, lbl3);
        evidences_in_scroll.add(pane, 0, i + 1);

    }


    @FXML
    void submit() {
        fxd2.close();
        lbl.setText(tmpEvidencesDb.size() + "");
    }

    @FXML
    private Pane pane;

    @FXML
    private JFXTextField desc;

    @FXML
    private JFXComboBox<?> type;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTextField fromwho;

    @FXML
    private GridPane evidences_in_scroll;


    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label wrong;

    @FXML
    void e_add() {
        pane.setVisible(true);
    }


    @FXML
    void ekle() {
        if (fromwho.getText().isEmpty() || type.getSelectionModel().getSelectedItem() == null || desc.getText().isEmpty()) {
            wrong.setVisible(true);

        } else {
            wrong.setText("");
            tmpEvidencesDb.add(new EvidenceModel(fromwho.getText(),Date.valueOf(date.getValue()), type.getSelectionModel().getSelectedItem().toString(), desc.getText()));
            fillGridPane();
            pane.setVisible(false);
        }


    }

}



