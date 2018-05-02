package controller;

import com.jfoenix.controls.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import main.MainClass;
import model.EvidenceModel;
import sun.applet.Main;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Timer;

public class CreateLawsuitController implements Initializable {
    private ArrayList<String> tmpDb = new ArrayList<>();
    private ArrayList<ToggleGroup> toggleGroups = new ArrayList<>();
    public static ArrayList<EvidenceModel> tmpDbEvidence = new ArrayList<>();
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
    private JFXComboBox<?> lawsuitType;

    @FXML
    private JFXTextField kimlikNo;

    @FXML
    private FontAwesomeIconView search_customer;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField numara;


    @FXML
    private JFXTextField complainant_name;

    @FXML
    private JFXTextField defandant_name;

    @FXML
    private JFXTextField complainant_surname;

    @FXML
    private JFXTextField defandant_surname;


    @FXML
    private JFXTextField payOfLawsuit;


    @FXML
    private JFXDatePicker lawsuit_start_date;

    @FXML
    private JFXComboBox<?> lawsuit_status;

    @FXML
    private JFXComboBox<?> opponentType;

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
    private JFXTextField fromwho;


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
    private JFXComboBox<?> typePay;

    @FXML
    private FontAwesomeIconView show_evidence;

    @FXML
    private JFXComboBox<?> customeril;

    @FXML
    private JFXComboBox<?> customerilce;

    @FXML
    private JFXTextField customerMahalle;

    @FXML
    private JFXTextField customersokak;

    @FXML
    private JFXTextField customerkapino;
    @FXML
    private JFXTextField phoneNumber;

    @FXML
    private JFXTextField customerpostakodu;

    @FXML
    private JFXTextArea lawsuitDesc;

    @FXML
    private Label wrongForValidate;

    private EntityManager entityManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entityManager = EntityManagerUtility.getEntityManager();
        lawsuit_start_date.setValue(LocalDate.now());
        date_evidence.setValue(LocalDate.now());
        ObservableList observableListTmpil = FXCollections.observableArrayList("İstanbul", "Ankara", "İzmir");
        ObservableList observableListTmpilce = FXCollections.observableArrayList("Zeytinburnu", "Esenler", "Mamak", "Kızılay");
        ObservableList observableListStatus = FXCollections.observableArrayList("Aktif", "Beklemede", "Pasif");
        ObservableList observableListOpponentType = FXCollections.observableArrayList("Bireysel", "Kurumsal");
        ObservableList observableListLawsuitType = FXCollections.observableArrayList("Ceza", "İcra", "Hukuk");
        ObservableList observableListTypePay = FXCollections.observableArrayList("Nakit", "KrediKartı (Tek Çekim)", "KrediKartı (3 Taksit)", "KrediKartı (6 Taksit)", "KrediKartı (9 Taksit)");
        ObservableList observableListEvidenceType = FXCollections.observableArrayList("Döküman", "Görüntü", "Ses Kaydı", "Suç Aleti");
        customeril.setItems(observableListTmpil);
        customerilce.setItems(observableListTmpilce);
        type_evidence.setItems(observableListEvidenceType);
        lawsuitType.setItems(observableListLawsuitType);
        opponentType.setItems(observableListOpponentType);
        lawsuit_status.setItems(observableListStatus);
        typePay.setItems(observableListTypePay);
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
        // Sorular kısmı için gridpane oluşturma
        fillGridPane();
    }

    // Bireysel - Kurumsal seçimi
    @FXML
    void customer_type() {
        if (customer_type.getText().equals("Bireysel")) {
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
            phoneNumber.setText("05350248022");
            defandant_name.setText("asdas");
            defandant_surname.setText("asdasdas");
            payOfLawsuit.setText("123");
            lawsuitDesc.setText("asdasd");

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
            complainant_name.setText(name.getText());
            complainant_surname.setText(numara.getText());
        } else if (t1.getSelectedToggle().getUserData().toString().equals("davalıId")) {
            System.out.println("davalı");
            cleanInput();
            defandant_name.setText(name.getText());
            defandant_surname.setText(numara.getText());
        }

    }

    void cleanInput() {
        defandant_surname.clear();
        defandant_name.clear();
        complainant_surname.clear();
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
        rbE.setUserData("EVET");
        rbE.setToggleGroup(tg);

        JFXRadioButton rbH = new JFXRadioButton();
        rbH.setLayoutX(560);
        rbH.setStyle("-jfx-selected-color : #9fa8da");
        rbH.setUserData("HAYIR");
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
        int kimlik = 0;
        int tax = 0;

        if (customer_type.getText().equals("Bireysel")) {
            kimlik = kimlikNo.getText().trim().length();
            tax = 10;
        } else {
            tax = kimlikNo.getText().trim().length();
            kimlik = 11;
        }


        if (validationForEmpty()) {
            System.out.println(kimlikNo.getText().trim().length() + "");
            if (kimlik != 11 || tax != 10 || phoneNumber.getText().length() != 11) {
                System.out.println("BAYRAK 1");
                if (kimlik != 11) {
                    wrongForValidate.setText("T.C. 11 HANELİ OLMALI :)");
                } else if (tax != 10) {
                    wrongForValidate.setText("VERGİ NUMARASI 10 HANELİ OLMALI :)");
                } else {
                    wrongForValidate.setText("Numara 11 haneli olmalıdır");
                }


            } else {
                System.out.println("BAYRAK 2");
                if (!validationForNumericInput()) {
                    wrongForValidate.setText("Lütfen rakam olması gereken yerlere harf girmeyelim :)");
                } else {
                    System.out.println("BAYRAK 2.2");
                    //////////////////              EKLEME SORGUSU FONKSİYONU           ////////////////////
                    wrongForValidate.setText("");
                    answers();
                    addLawsuit();

                }
            }


        } else {
            submit.setStyle("-fx-border-color: red");
        }


    }

    // CEVAPLAR

    private void answers() {
        for (int i = 0; i < toggleGroups.size(); i++) {
            if (toggleGroups.get(i).getSelectedToggle() == null) {
                toggleGroups.get(i).setUserData("CEVAPSIZ");
                System.out.println(toggleGroups.get(i).getUserData());
            } else {
                System.out.println(toggleGroups.get(i).getSelectedToggle().getUserData());
            }
        }
    }


    ///// VERİTABANINA DAVA KAYDI

    public void addLawsuit() {
        String tax;
        String tc;
        try {
            Opponent o1 = new Opponent(MainClass.member.getIdMember(), defandant_name.toString(), defandant_surname.toString(), opponentType.getSelectionModel().getSelectedItem().toString(), LocalDate.now());
            if (customer_type.getText().equals("Bireysel")) {

                tax = null;
                tc = kimlikNo.getText().trim();
            } else {
                tc = null;
                tax =kimlikNo.getText().trim();
            }


            Customer c1 = new Customer(Long.valueOf(tc), Long.valueOf(tax), customer_type.getText().trim(), name.getText().trim(), numara.getText().trim(), Long.valueOf(phoneNumber.getText().trim()));
            entityManager.persist(o1);
            Lawsuit l1 = new Lawsuit(MainClass.member.getIdMember(), c1.getIdCustomer(), o1.getIdOpponent(), lawsuitType.getSelectionModel().getSelectedItem().toString().trim(), lawsuit_status.getSelectionModel().getSelectedItem().toString().trim(), lawsuit_start_date.getValue(), lawsuitDesc.toString());
            entityManager.persist(l1);

            for (int i = 0; i < tmpDb.size(); i++) {
                entityManager.persist(new Question(l1.getIdLawsuit(), tmpDb.get(i), toggleGroups.get(i).getUserData().toString()));
            }
            for (int i = 0; i < tmpDbEvidence.size(); i++) {
                entityManager.persist(new Evidence(l1.getIdLawsuit(), tmpDbEvidence.get(i).getFromWho(), tmpDbEvidence.get(i).getType(), tmpDbEvidence.get(i).getDesc(), tmpDbEvidence.get(i).getDate()));
            }


        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    /// SAYI İÇERİĞİ KONTROLU

    private boolean validationForNumericInput() {
        try {
            Integer.valueOf(customerkapino.getText().trim());
            Long.valueOf(phoneNumber.getText().trim());
            Integer.valueOf(customerpostakodu.getText().trim());
            Integer.valueOf(payOfLawsuit.getText().trim());
            Long.valueOf(kimlikNo.getText().trim());
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    ///  INPUT DOLU-BOS KONTROLU

    private boolean validationForEmpty() {
        if (phoneNumber.getText().isEmpty() || lawsuitDesc.getText().isEmpty() || customeril.getSelectionModel().getSelectedItem() == null || customerilce.getSelectionModel().getSelectedItem() == null || customerkapino.getText().isEmpty() || customerMahalle.getText().isEmpty() || customerpostakodu.getText().isEmpty() || customersokak.getText().isEmpty() || payOfLawsuit.getText().isEmpty() || kimlikNo.getText().isEmpty() || name.getText().isEmpty() || numara.getText().isEmpty() || t1.getSelectedToggle() == null || defandant_name.getText().isEmpty() || complainant_name.getText().isEmpty() || defandant_surname.getText().isEmpty() || complainant_surname.getText().isEmpty() || defandant_name.getText().isEmpty() || lawsuitType.getSelectionModel().getSelectedItem() == null || lawsuit_status.getSelectionModel().getSelectedItem() == null || opponentType.getSelectionModel().getSelectedItem() == null || typePay.getSelectionModel().getSelectedItem() == null) {
            wrongForValidate.setText("Az çok demeyelim boş geçmeyelim :)");
            return false;

        } else {
            if (evidence_pane.isVisible()) {

                if (info_evidence.getText().isEmpty() || type_evidence.getSelectionModel().getSelectedItem() == null || fromwho.getText().isEmpty()) {
                    wrongForValidate.setText("Kanıt Bölümündeki girdileri Tamamlayınız");
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }


    }

    // KANIT TİKİ SONRASI EKLEME PANE AÇILIŞI

    @FXML
    void add_evidence() {

        evidence_pane.setVisible(true);

    }

    // KANIT TİKİ

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

    // EKLENEN KANIT GÖRÜNTÜLENMESİ

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

    // KANI PANEDE EKLEME

    @FXML
    void add_evidence_hide() {
        if (info_evidence.getText().isEmpty() || type_evidence.getSelectionModel().getSelectedItem() == null || fromwho.getText().isEmpty()) {
            wrongForValidate.setText("Kanıt Bölümündeki girdileri Tamamlayınız");

        } else {

            tmpDbEvidence.add(new EvidenceModel(fromwho.getText(), date_evidence.getValue(), type_evidence.getSelectionModel().getSelectedItem().toString(), info_evidence.getText()));
            evidence_count.setText(tmpDbEvidence.size() + "");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    @FXML
    void editForLawsuit() {

        kimlikNo.setText("12345678910");
        name.setText("Admin");
        numara.setText("02122123212");
        which_customer_complainant.setSelected(true);
        toogle();
        defandant_name.setText("Mod");
        defandant_surname.setText("02824329921");
        lawsuit_start_date.setValue(LocalDate.of(2018, 01, 12));
        payOfLawsuit.setText("7000");
        checkbox_evidence.setSelected(true);
        checkbox_evidence();
        evidence_count.setText("" + 3);
        typePay.getSelectionModel().select(2);
        lawsuit_status.getSelectionModel().select(0);
        for (int i = 0; i < toggleGroups.size(); i++) {
            if (i % 2 == 0) {
                toggleGroups.get(i).getToggles().get(0).setSelected(true);

            } else {

                toggleGroups.get(i).getToggles().get(1).setSelected(true);
            }
        }


    }


}
