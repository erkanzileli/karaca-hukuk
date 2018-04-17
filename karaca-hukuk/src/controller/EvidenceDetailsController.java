package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EvidenceDetailsController implements Initializable {
    public static ArrayList<String> tmpEvidencesDb = new ArrayList<>();
    public static JFXDialog fxd2;
    public static Label lbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillGridPane();

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
        lbl.setFont(new Font(13));
        lbl.setText(i + 1 + " - " + tmpEvidencesDb.get(i));
        lbl.setId("lbl" + i);


        pane.getChildren().add(lbl);
        evidences_in_scroll.add(pane, 0, i + 1);

    }


    @FXML
    void submit() {
        fxd2.close();
        lbl.setText(tmpEvidencesDb.size() + "");
    }

    @FXML
    private JFXButton e_add_button;

    @FXML
    private FontAwesomeIconView q_add_icon;

    @FXML
    private GridPane evidences_in_scroll;


    @FXML
    private ScrollPane scrollPane;


    @FXML
    void e_add() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Kanıt ekleme kısmı");
        dialog.setHeaderText("Lütfen kanıt bilgisi giriniz ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            tmpEvidencesDb.add(result.get());
            addRowInGrid(tmpEvidencesDb.size() - 1);
            scrollPane.vvalueProperty().bind(evidences_in_scroll.heightProperty());

        }
    }


}
