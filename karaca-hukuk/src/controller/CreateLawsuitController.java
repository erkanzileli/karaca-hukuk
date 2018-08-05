package controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import main.MainClass;
import model.EvidenceModel;
import utility.EntityManagerUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class CreateLawsuitController implements Initializable {
    private ArrayList<String> tmpDb = new ArrayList<>();
    private ArrayList<ToggleGroup> toggleGroups = new ArrayList<>();
    public static ArrayList<EvidenceModel> tmpDbEvidence = new ArrayList<>();
    List<Lawsuit> lawsuitList = new ArrayList<>();
    JFXDialog dialog;
    public static BorderPane borderPane;
    public static long selectedItemTc;
    public static Date selectedItemDate;
    Parent lawsuits = null;


    @FXML
    private StackPane rootStack;


    @FXML
    private JFXToggleButton customer_type;

    @FXML
    private JFXComboBox<String> lawsuitType;

    @FXML
    private JFXTextField kimlikNo;

    @FXML
    private FontAwesomeIconView editForLawsuit;

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
    private JFXComboBox<String> lawsuit_status;

    @FXML
    private JFXComboBox<String> opponentType;

    @FXML
    private JFXCheckBox checkbox_evidence;

    @FXML
    private FontAwesomeIconView add_evidence;

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
    private JFXComboBox<String> type_evidence;

    @FXML
    private JFXDatePicker date_evidence;

    @FXML
    private JFXButton add_evidence_hide;

    @FXML
    private Label evidence_count;

    @FXML
    private JFXComboBox<String> typePay;

    @FXML
    private FontAwesomeIconView show_evidence;

    @FXML
    private JFXComboBox<String> customeril;

    @FXML
    private JFXComboBox<String> customerilce;

    @FXML
    private JFXTextField customerMahalle;


    @FXML
    private JFXComboBox<String> gender;

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
        which_customer_defendant.setUserData("davali");
        which_customer_complainant.setUserData("davaci");

        System.gc();
        // Combobox dolumu
        gender.getItems().addAll("Erkek", "Kadın");
        type_evidence.getItems().addAll("Döküman", "Görüntü", "Ses Kaydı", "Suç Aleti");
        lawsuitType.getItems().addAll("Ceza", "İcra", "Hukuk");
        opponentType.getItems().addAll("Bireysel", "Kurumsal");
        lawsuit_status.getItems().addAll("Aktif", "Beklemede", "Pasif");
        typePay.getItems().addAll("Nakit", "KrediKarti (Tek Cekim)", "KrediKarti (3 Taksit)", "KrediKarti (6 Taksit)", "KrediKarti (9 Taksit)");

        try {
            lawsuits = FXMLLoader.load(getClass().getResource("/fxml/lawsuits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        entityManager = EntityManagerUtility.getEntityManager();
        fillComboProvinces();
        if (selectedItemDate != null) {
            System.out.println("bu bir edit ekranıdır");
            editForLawsuit.setVisible(true);
            System.out.println("BAYRAK 0");
            Query query1 = entityManager.createNativeQuery("SELECT * FROM Lawsuit WHERE date=?1", Lawsuit.class);
            System.out.println("BAYRAK 11");
            query1.setParameter(1, selectedItemDate);
            System.out.println("BAYRAK 22");
            System.out.println(query1.getResultList().size() + "----------");
            Lawsuit l1 = new Lawsuit();
            lawsuitList = query1.getResultList();
            System.out.println("BAYRAK 33");
            Query query2 = entityManager.createNativeQuery("SELECT * FROM Customer WHERE tc=?2", Customer.class);
            System.out.println("BAYRAK 44");
            query2.setParameter(2, selectedItemTc);
            System.out.println("BAYRAK 55");
            Customer c1 = (Customer) query2.getResultList().get(0);
            System.out.println("BAYRAK 66");
            for (Lawsuit lawsuit : lawsuitList) {
                if (lawsuit.getIdCustomer() == c1.getIdCustomer()) {
                    l1 = lawsuit;
                }
            }
            System.out.println("BAYRAK 77");
            Query query3 = entityManager.createNativeQuery("SELECT * FROM Adress WHERE idCustomer=?3", Adress.class);
            System.out.println("BAYRAK 88");
            System.out.println("************"+c1.getIdCustomer());
            query3.setParameter(3, c1.getIdCustomer());
            System.out.println("BAYRAK 99");
            System.out.println("************"+query3.getResultList().size());
            Adress a1 = (Adress) query3.getResultList().get(0);
            System.out.println("BAYRAK 1010");
            Query query4 = entityManager.createNativeQuery("SELECT * FROM Opponent WHERE idOpponent=?4", Opponent.class);
            System.out.println("BAYRAK 1111");
            query4.setParameter(4, l1.getIdOpponent());
            System.out.println("BAYRAK 1212");
            Opponent o1 = (Opponent) query4.getResultList().get(0);
            System.out.println("BAYRAK 1313");
            name.setText(c1.getName());
            numara.setText(c1.getSurname());
            phoneNumber.setText(String.valueOf(c1.getPhoneNumber()));
            gender.getSelectionModel().select(c1.getGender());
            customeril.getSelectionModel().select(a1.getCounty());
            customerMahalle.setText("Resadiye Mah.");
            customersokak.setText(a1.getStreet());
            customerkapino.setText(a1.getDoorNumber() + "");
            customerpostakodu.setText(a1.getPostalCode() + "");
            lawsuit_start_date.setValue(l1.getDate().toLocalDate());
            opponentType.setValue(o1.getType());
            kimlikNo.setText(selectedItemTc + "");
            Query query5 = entityManager.createNativeQuery("SELECT ilce_isim FROM Provinces WHERE il_isim = ?1");
            query5.setParameter(1, customeril.getValue());
            customerilce.getItems().clear();
            customerilce.getItems().addAll(query5.getResultList());
            customerilce.getSelectionModel().select(a1.getCity());
            if (l1.getSide().equals("davaci")) {
                t1.selectToggle(which_customer_complainant);
                complainant_name.setText(c1.getName());
                complainant_surname.setText(c1.getSurname());
                defandant_surname.setText(o1.getName());
                defandant_name.setText(o1.getName());
            } else {
                t1.selectToggle(which_customer_defendant);
                complainant_surname.setText(o1.getSurname());
                complainant_name.setText(o1.getName());
                defandant_name.setText(c1.getName());
                defandant_surname.setText(c1.getSurname());
            }

            lawsuitType.getSelectionModel().select(l1.getType());
            lawsuitDesc.setText(l1.getDescription());
            lawsuit_start_date.setValue(l1.getDate().toLocalDate());
            lawsuit_status.getSelectionModel().select(l1.getStatus());
            opponentType.getSelectionModel().select(o1.getType());
            typePay.getSelectionModel().select(l1.getTypePay());
            payOfLawsuit.setText(String.valueOf(l1.getPayment()));


            selectedItemDate = null;
            //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        } else {
            System.out.println("bu bir create ekranıdır");
            editForLawsuit.setVisible(false);
            lawsuit_start_date.setValue(LocalDate.now());
            date_evidence.setValue(LocalDate.now());
        }

        // Tasarım icin gecici db
        String tmpString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";

        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        tmpDb.add(tmpString);
        // Sorular kısmı için gridpane oluşturma
        fillGridPane();

/*
        if(selectedItemDate==null){

        }else{
            Lawsuit l1= (Lawsuit) query1.getResultList().get(0);


            Query query3=entityManager.createNativeQuery("SELEC");


            name.setText(c1.getName());
            numara.setText(c1.getSurname());
            phoneNumber.setText(String.valueOf(c1.getPhoneNumber()));
            gender.setPromptText(c1.getGender());
            gender.getSelectionModel().select(c1.getGender());
            customeril.getSelectionModel().select(a1.getCounty());
            customerilce.getSelectionModel().select(a1.getCity());
            customerMahalle.setText("Resadiye Mah.");
            customersokak.setText(a1.getStreet());
            customerkapino.setText(a1.getDoorNumber() + "");
            customerpostakodu.setText(a1.getPostalCode() + "");


        }
*/

    }

    // Bireysel - Kurumsal seçimi
    @FXML
    void customer_type() {
        if (customer_type.getText().equals("Bireysel")) {
            customer_type.setText("Kurumsal");
            kimlikNo.setPromptText("Vergi Numarası");
            gender.getSelectionModel().select(0);
            gender.setVisible(false);
        } else {
            customer_type.setText("Bireysel");
            kimlikNo.setPromptText("T.C");
            gender.setVisible(true);
        }
    }

    // Kayıtlı müşteri araması
    @FXML
    void search_customer() {

        Query query = entityManager.createNativeQuery("SELECT * FROM Customer WHERE tc=?1", Customer.class);

        query.setParameter(1, Long.valueOf(kimlikNo.getText()));
        if (!query.getResultList().isEmpty()) {
            System.out.println("1");
            Customer n1 = (Customer) query.getResultList().get(0);
            System.out.println("2");
            Query q = entityManager.createNativeQuery("SELECT * FROM Adress WHERE idCustomer=?1", Adress.class);
            System.out.println("3");
            q.setParameter(1, n1.getIdCustomer());
            System.out.println("4");
            Adress a1 = (Adress) q.getResultList().get(0);
            System.out.println("5");
            name.setText(n1.getName());
            numara.setText(n1.getSurname());
            phoneNumber.setText(String.valueOf(n1.getPhoneNumber()));
            gender.setPromptText(n1.getGender());
            gender.getSelectionModel().select(n1.getGender());
            customeril.getSelectionModel().select(a1.getCounty());
            customerilce.getSelectionModel().select(a1.getCity());
            customerMahalle.setText("Resadiye Mah.");
            customersokak.setText(a1.getStreet());
            customerkapino.setText(a1.getDoorNumber() + "");
            customerpostakodu.setText(a1.getPostalCode() + "");


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


        if (t1.getSelectedToggle().getUserData().toString().equals("davaci")) {
            System.out.println("davaci");
            cleanInput();
            complainant_name.setText(name.getText());
            complainant_surname.setText(numara.getText());
        } else if (t1.getSelectedToggle().getUserData().toString().equals("davali")) {
            System.out.println("davali");
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
                    if (selectedItemTc != 0) {
                        updateLawsuit();
                    } else {
                        addLawsuit();
                    }

                    System.gc();
                    screenSwitch();


                }
            }


        } else {
            submit.setStyle("-fx-border-color: red");
        }


    }

    private void screenSwitch() {
        borderPane.setCenter(lawsuits);

    }

    // CEVAPLAR

    private void answers() {
        for (int i = 0; i < toggleGroups.size(); i++) {
            if (toggleGroups.get(i).getSelectedToggle() == null) {
                toggleGroups.get(i).setUserData("CEVAPSIZ");
                System.out.println(toggleGroups.get(i).getUserData());
            } else {
                toggleGroups.get(i).setUserData(toggleGroups.get(i).getSelectedToggle().getUserData());
            }
        }
    }

    private void updateLawsuit() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("SELECT * FROM Customer WHERE tc=?1", Customer.class);
        query.setParameter(1, selectedItemTc);
        System.out.println("************"+query.getResultList().size());
        Customer c1 = (Customer) query.getResultList().get(0);
        c1.setName(name.getText());
        c1.setPhoneNumber(Long.valueOf(phoneNumber.getText()));
        c1.setSurname(numara.getText());
        c1.setGender(gender.getValue());
        c1.setType(customer_type.getText());
        Query query1=entityManager.createNativeQuery("SELECT  * FROM Lawsuit WHERE idCustomer=?2",Lawsuit.class);
        query1.setParameter(2,c1.getIdCustomer());

        System.out.println("************"+query1.getResultList().size());
        Lawsuit l1= (Lawsuit) query1.getResultList().get(0);
        l1.setDate(Date.valueOf(lawsuit_start_date.getValue()));
        l1.setDescription(lawsuitDesc.getText());
        l1.setPayment(Integer.valueOf(payOfLawsuit.getText()));
        l1.setSide(t1.getSelectedToggle().getUserData().toString());
        l1.setTypePay(typePay.getSelectionModel().getSelectedItem());
        l1.setStatus(lawsuit_status.getSelectionModel().getSelectedItem());
        Query query2=entityManager.createNativeQuery("SELECT * FROM Opponent WHERE idOpponent=?3",Opponent.class);
        query2.setParameter(3,l1.getIdOpponent());
        System.out.println("************"+query2.getResultList().size());
        Opponent o1= (Opponent) query2.getResultList().get(0);
        if(t1.getSelectedToggle().getUserData().equals("davaci")){
            o1.setName(defandant_name.getText());
            o1.setSurname(defandant_surname.getText());


        }else{
            o1.setName(complainant_name.getText());
            o1.setSurname(complainant_surname.getText());


        }
        o1.setType(opponentType.getValue());

        Query query3=entityManager.createNativeQuery("SELECT * FROM Adress WHERE idCustomer=?4",Adress.class);
        query3.setParameter(4,c1.getIdCustomer());
        System.out.println("************"+query3.getResultList().size());
        Adress a1= (Adress) query3.getResultList().get(0);
        a1.setCity(customerilce.getSelectionModel().getSelectedItem());
        a1.setCounty(customeril.getSelectionModel().getSelectedItem());
        a1.setDoorNumber(customerkapino.getText());
        a1.setPostalCode(Integer.valueOf(customerpostakodu.getText()));
        a1.setStreet(customersokak.getText());



        entityManager.getTransaction().commit();


    }


    ///// VERİTABANINA DAVA KAYDI

    public void addLawsuit() {

        Customer c1;
        Query query = entityManager.createNativeQuery("SELECT * FROM Customer WHERE tc=?1", Customer.class);
        query.setParameter(1, Long.valueOf(kimlikNo.getText()));
        if (!query.getResultList().isEmpty()) {

            System.out.println("1");
            c1 = (Customer) query.getResultList().get(0);
        } else {
            c1 = null;
        }

        Date a = Date.valueOf(lawsuit_start_date.getValue());


        try {
            entityManager.getTransaction().begin();
            Opponent o1 = new Opponent(defandant_name.getText().trim(), defandant_surname.getText().trim(), opponentType.getSelectionModel().getSelectedItem(), Date.valueOf(LocalDate.now()));
            System.out.println("BAYRAK  1");
            entityManager.persist(o1);
            System.out.println("BAYRAK 1.1");
            System.out.println(defandant_name.getText());
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            if (customer_type.getText().equals("Bireysel")) {
                if (c1 != null) {
///
                } else {
                    c1 = new Customer(Long.valueOf(kimlikNo.getText()), customer_type.getText().trim(), name.getText().trim(), numara.getText().trim(), Long.valueOf(phoneNumber.getText().trim()), gender.getValue());
                    entityManager.persist(c1);
                }
                System.out.println("BAYRAK  2");
            } else {
                if (c1 != null) {
///
                } else {
                    c1 = new Customer(Long.valueOf(kimlikNo.getText()), customer_type.getText().trim(), name.getText().trim(), numara.getText().trim(), Long.valueOf(phoneNumber.getText().trim()));
                    entityManager.persist(c1);
                }
                System.out.println("BAYRAK  3");
            }

            System.out.println("BAYRAK  4");


            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            System.out.println("BAYRAK  5");
            Lawsuit l1 = new Lawsuit(MainClass.member.getIdMember(), c1.getIdCustomer(), o1.getIdOpponent(), lawsuitType.getSelectionModel().getSelectedItem().trim(), lawsuit_status.getSelectionModel().getSelectedItem(), a, lawsuitDesc.getText(), t1.getSelectedToggle().getUserData().toString(), typePay.getSelectionModel().getSelectedItem(), Integer.valueOf(payOfLawsuit.getText()));
            System.out.println("BAYRAK  6");
            entityManager.persist(l1);
            entityManager.getTransaction().commit();
            Adress a1 = new Adress(customerilce.getSelectionModel().getSelectedItem(), customeril.getSelectionModel().getSelectedItem(), customersokak.getText(), Integer.valueOf(customerpostakodu.getText()), Long.valueOf(phoneNumber.getText()), customerkapino.getText(), "", c1.getIdCustomer());
            System.out.println("BAYRAK  7");
            entityManager.getTransaction().begin();
            entityManager.persist(a1);
            entityManager.getTransaction().commit();
            System.out.println("" + l1.getIdLawsuit());
            for (int i = 0; i < tmpDb.size() - 1; i++) {
                entityManager.getTransaction().begin();
                System.out.println("BAYRAK 7.1");
                entityManager.persist(new Question(l1.getIdLawsuit(), tmpDb.get(i), toggleGroups.get(i).getUserData().toString()));
                entityManager.getTransaction().commit();
            }

            System.out.println("BAYRAK  8");
            System.out.println(tmpDbEvidence.size() + "   ///////////////");
            for (int i = 0; i < tmpDbEvidence.size(); i++) {
                System.out.println("BAYRAK 8.1");
                entityManager.getTransaction().begin();
                System.out.println(l1.getIdLawsuit() + "--" + tmpDbEvidence.get(i).getFromWho() + "--" + tmpDbEvidence.get(i).getType() + "--" + tmpDbEvidence.get(i).getDesc() + "--" + tmpDbEvidence.get(i).getDate().toString());
                entityManager.persist(new Evidence(l1.getIdLawsuit(), tmpDbEvidence.get(i).getFromWho(), tmpDbEvidence.get(i).getType().trim(), tmpDbEvidence.get(i).getDesc(), tmpDbEvidence.get(i).getDate()));
                System.out.println("BAYRAK 8.2");
                entityManager.getTransaction().commit();
            }
            System.out.println("BAYRAK  9");


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
        if (phoneNumber.getText().isEmpty() || gender.getSelectionModel().getSelectedItem() == null || lawsuitDesc.getText().isEmpty() || customeril.getSelectionModel().getSelectedItem() == null || customerilce.getSelectionModel().getSelectedItem() == null || customerkapino.getText().isEmpty() || customerMahalle.getText().isEmpty() || customerpostakodu.getText().isEmpty() || customersokak.getText().isEmpty() || payOfLawsuit.getText().isEmpty() || kimlikNo.getText().isEmpty() || name.getText().isEmpty() || numara.getText().isEmpty() || t1.getSelectedToggle() == null || defandant_name.getText().isEmpty() || complainant_name.getText().isEmpty() || defandant_surname.getText().isEmpty() || complainant_surname.getText().isEmpty() || defandant_name.getText().isEmpty() || lawsuitType.getSelectionModel().getSelectedItem() == null || lawsuit_status.getSelectionModel().getSelectedItem() == null || opponentType.getSelectionModel().getSelectedItem() == null || typePay.getSelectionModel().getSelectedItem() == null) {
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
        Date b = Date.valueOf(date_evidence.getValue());
        if (info_evidence.getText().isEmpty() || type_evidence.getSelectionModel().getSelectedItem() == null || fromwho.getText().isEmpty()) {
            wrongForValidate.setText("Kanıt Bölümündeki girdileri Tamamlayınız");

        } else {

            tmpDbEvidence.add(new EvidenceModel(fromwho.getText(), b, type_evidence.getValue(), info_evidence.getText()));
            evidence_count.setText(tmpDbEvidence.size() + "");
        }
    }

    ////////////////////////////////////////////////////////////////////////////


    private void fillComboProvinces() {
        Query query = entityManager.createNativeQuery("SELECT DISTINCT il_isim FROM Provinces ORDER BY il_isim ASC");
        customeril.getItems().setAll(query.getResultList());
    }

    @FXML
    public void listDistrictsOfProvince() {
        // ile göre ilçe listeleme
        Query query = entityManager.createNativeQuery("SELECT ilce_isim FROM Provinces WHERE il_isim = ?1");
        query.setParameter(1, customeril.getValue());
        customerilce.getItems().clear();
        customerilce.getItems().addAll(query.getResultList());
    }


}
