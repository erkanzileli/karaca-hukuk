package controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class CreateLawsuitController implements Initializable {
    public static JFXDialog fxd;
    private ArrayList<String> tmpDb=new ArrayList<>();
    private ArrayList<ToggleGroup> toggleGroups=new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        which_customer_defendant.setUserData("davalıId");
        which_customer_complainant.setUserData("davacıId");


        String tmpString="Lorem Ipsum is simply dummy text of the printing and typesetting industry.";

            tmpDb.add(tmpString);
            tmpDb.add(tmpString);
            tmpDb.add(tmpString);
            tmpDb.add(tmpString);
            tmpDb.add(tmpString);
            tmpDb.add(tmpString);
            tmpDb.add(tmpString);
            tmpDb.add(tmpString);
            tmpDb.add(tmpString);


        fillGridPane();
    }


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
    private Label uyari;

    @FXML
    private JFXTextField numara;

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXToggleButton which_customer;

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
    private JFXListView<?> listview;

    @FXML
    private JFXButton submit;


    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridpane_in_scrollpane;

    @FXML
    private JFXButton q_add_button;

    @FXML
    private FontAwesomeIconView q_add_icon;


    @FXML
    private JFXRadioButton which_customer_complainant;

    @FXML
    private JFXRadioButton which_customer_defendant;


    @FXML
    void checkbox_evidence() {

    }

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

    @FXML
    void q_add() {

    }


    @FXML
    private ToggleGroup t1;



    @FXML
    void search_customer() {
        if(kimlikNo.getText().equals("12345678910")){
        name.setText("admin");
        numara.setText("10");
        adress.setText("Namık Kemal Universitesi");
        }else {

            uyari.setVisible(true);
            uyari.setText("-->  BULUNAMADI ");
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    uyari.setVisible(false);
                }
            },3000);
        }



    }


    @FXML
    void submit() {

        for (int i = 0; i < toggleGroups.size(); i++) {
            System.out.println(toggleGroups.get(i).getSelectedToggle().getUserData());
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



    void fillGridPane() {
        scrollPane.setPadding(new Insets(10,5,5,5));

        int i;
        for (i = 1; i < tmpDb.size(); i++) {

            Pane pane = new Pane();
            pane.setPrefSize(615, 30);
            Label lbl =new Label();
            lbl.setLayoutX(10);
            lbl.setFont(new Font(12));
            lbl.setMaxSize(510,30);
            lbl.setText( i +" - " + tmpDb.get(i));


            ToggleGroup tg=new ToggleGroup();
            tg.setUserData("tgId"+i);
            JFXRadioButton rbE=new JFXRadioButton();
            rbE.setLayoutX(525);
            rbE.setStyle("-jfx-selected-color : #9fa8da");
            rbE.setUserData("rbE"+i);
            rbE.setToggleGroup(tg);
            JFXRadioButton rbH=new JFXRadioButton();
            rbH.setLayoutX(560);
            rbH.setStyle("-jfx-selected-color : #9fa8da");
            rbH.setUserData("rbH"+i);
            rbH.setToggleGroup(tg);

            toggleGroups.add(tg);

            pane.getChildren().addAll(lbl,rbE,rbH);
            gridpane_in_scrollpane.add(pane,0,i);
        }
    }



    @FXML
    void closeCreateLawsuit() {
        fxd.close();
        System.gc();
    }


    @FXML
    void toogle() {
        if (t1.getSelectedToggle().getUserData().toString().equals("davalıId")){
            cleanInput();
            complainant_name.setText(name.getText().toString());
            complainant_adress.setText(adress.getText().toString());
            complainant_no.setText(numara.getText().toString());
        }else if (t1.getSelectedToggle().getUserData().toString().equals("davacıId")){
            defandant_name.setText(name.getText().toString());
            defandant_adress.setText(adress.getText().toString());
            defandant_no.setText(numara.getText().toString());
        }

    }

}
