package controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CreateLawsuitController implements Initializable {
    private ArrayList<String> tmpDb = new ArrayList<>();
    private ArrayList<ToggleGroup> toggleGroups = new ArrayList<>();
    private ArrayList<String> tmpDbEvidence = new ArrayList<>();
    JFXDialog dialog;

    @FXML
    private StackPane rootStack;

    @FXML
    private AnchorPane createLawsuit_basePane;

    @FXML
    private FontAwesomeIconView create_lawsuit_screen_close;

    @FXML
    private JFXToggleButton customer_type;

    @FXML
    private JFXTextField kimlikNo;

    @FXML
    private FontAwesomeIconView search_customer;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField numara;

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXTextField complainant_name;

    @FXML
    private JFXTextField defandant_name;

    @FXML
    private JFXTextField complainant_no;

    @FXML
    private JFXTextField defandant_no;

    @FXML
    private JFXTextField complainant_adress;

    @FXML
    private JFXTextField defandant_adress;

    @FXML
    private JFXDatePicker lawsuit_start_date;

    @FXML
    private JFXComboBox<?> lawsuit_status;

    @FXML
    private JFXCheckBox checkbox_evidence;

    @FXML
    private FontAwesomeIconView add_evidence;

    @FXML
    private JFXButton q_add_button;

    @FXML
    private FontAwesomeIconView q_add_icon;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridpane_in_scrollpane;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXRadioButton which_customer_complainant;

    @FXML
    private ToggleGroup t1;

    @FXML
    private JFXRadioButton which_customer_defendant;

    @FXML
    private Label uyari;

    @FXML
    private Pane evidence_pane;

    @FXML
    private JFXTextField info_evidence;

    @FXML
    private JFXComboBox<?> type_evidence;

    @FXML
    private JFXDatePicker date_evidence;

    @FXML
    private JFXButton add_evidence_hide;

    @FXML
    private Label evidence_count;


    @FXML
    private FontAwesomeIconView show_evidence;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //davalı-davacı secimi için
        which_customer_defendant.setUserData("davalıId");
        which_customer_complainant.setUserData("davacıId");
        // Tasarım icin gecici db
        String tmpString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";

        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        // Sorular kısmı için gridpane oluşturma
        fillGridPane();
    }

    // Bireysel - Kurumsal seçimi
    @FXML
    void customer_type() {
        if (customer_type.getText().toString().equals("Bireysel")) {
            customer_type.setText("Kurumsal");
            kimlikNo.setPromptText("Vergi Numarası");
        } else {
            customer_type.setText("Bireysel");
            kimlikNo.setPromptText("T.C");
        }
    }

    // Kayıtlı müşteri araması
    @FXML
    void search_customer() {
        if (kimlikNo.getText().equals("12345678910")) {
            name.setText("admin");
            numara.setText("10");
            adress.setText("Namık Kemal Universitesi");
        } else {

            uyari.setVisible(true);
            uyari.setText("-->  BULUNAMADI ");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    uyari.setVisible(false);
                }
            }, 3000);
        }


    }

    // Davalı - Davacı seçimi
    @FXML
    void toogle() {


        if (t1.getSelectedToggle().getUserData().toString().equals("davacıId")) {
            System.out.println("davacı");
            cleanInput();
            complainant_name.setText(name.getText().toString());
            complainant_adress.setText(adress.getText().toString());
            complainant_no.setText(numara.getText().toString());
        } else if (t1.getSelectedToggle().getUserData().toString().equals("davalıId")) {
            System.out.println("davalı");
            cleanInput();
            defandant_name.setText(name.getText().toString());
            defandant_adress.setText(adress.getText().toString());
            defandant_no.setText(numara.getText().toString());
        }

    }

    void cleanInput() {
        defandant_no.clear();
        defandant_adress.clear();
        defandant_name.clear();
        complainant_adress.clear();
        complainant_no.clear();
        complainant_name.clear();
    }

    // Gridpane e kayıtlı soruları ekleme
    void fillGridPane() {

        scrollPane.setPadding(new Insets(10, 5, 5, 5));
        int i;
        for (i = 1; i < tmpDb.size(); i++) {
            addRowInGrid(i);
        }
    }

    // Gridpane e item eklemek
    private void addRowInGrid(int i) {


        Pane pane = new Pane();
        pane.setPrefSize(615, 30);
        Label lbl = new Label();
        lbl.setLayoutX(10);
        lbl.setFont(new Font(12));
        lbl.setMaxSize(510, 30);
        lbl.setText(i + " - " + tmpDb.get(i));


        ToggleGroup tg = new ToggleGroup();
        tg.setUserData(i + ".ToggleGroup");

        JFXRadioButton rbE = new JFXRadioButton();
        rbE.setLayoutX(525);
        rbE.setStyle("-jfx-selected-color : #9fa8da");
        rbE.setUserData(i + " EVET");
        rbE.setToggleGroup(tg);

        JFXRadioButton rbH = new JFXRadioButton();
        rbH.setLayoutX(560);
        rbH.setStyle("-jfx-selected-color : #9fa8da");
        rbH.setUserData(i + " HAYIR");
        rbH.setToggleGroup(tg);

        toggleGroups.add(tg);

        pane.getChildren().addAll(lbl, rbE, rbH);
        gridpane_in_scrollpane.add(pane, 0, i);


    }

    // Kullanıcı tarafından soru eklenmesi
    @FXML
    void q_add() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Soru ekleme kısmı");
        dialog.setHeaderText("Lütfen sorunuzu giriniz ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            tmpDb.add(result.get());
            addRowInGrid(tmpDb.size() - 1);
            scrollPane.vvalueProperty().bind(gridpane_in_scrollpane.heightProperty());


        }
    }

    // Kaydın tamamlanması
    @FXML
    void submit() {

        for (int i = 0; i < toggleGroups.size(); i++) {
            if (toggleGroups.get(i).getSelectedToggle() == null) {
                System.out.println(toggleGroups.get(i).getUserData() + "  Secim Yapılmamıs");
            } else {
                System.out.println(toggleGroups.get(i).getSelectedToggle().getUserData());
            }

        }


    }


    @FXML
    void add_evidence() {

        evidence_pane.setVisible(true);

    }


    @FXML
    void checkbox_evidence() {
        if (checkbox_evidence.isSelected()) {
            add_evidence.setVisible(true);
            evidence_count.setVisible(true);
            show_evidence.setVisible(true);
            add_evidence.setVisible(true);
        } else {
            show_evidence.setVisible(false);
            evidence_count.setVisible(false);
            evidence_pane.setVisible(false);
            add_evidence.setVisible(false);
        }
    }


    @FXML
    void show_evidence() {

        Parent invoice = null;
        try {
            invoice = FXMLLoader.load(getClass().getResource("/fxml/evidenceDetails.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setBody(invoice);
        dialog = new JFXDialog(rootStack, dialogLayout, JFXDialog.DialogTransition.CENTER);
        EvidenceDetailsController.fxd2 = dialog;
        EvidenceDetailsController.lbl = evidence_count;
        dialog.show();


    }


    @FXML
    void add_evidence_hide() {
        tmpDbEvidence.add(info_evidence.getText().toString());
        EvidenceDetailsController.tmpEvidencesDb.add(info_evidence.getText().toString());
        evidence_count.setText(EvidenceDetailsController.tmpEvidencesDb.size() + "");
    }


}
