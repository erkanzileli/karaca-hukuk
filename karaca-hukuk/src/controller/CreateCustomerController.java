package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import main.MainClass;

public class CreateCustomerController implements Initializable {

	@FXML
	private JFXDialogLayout root;

	@FXML
	private Pane rootPane;

	@FXML
	private JFXComboBox<String> comboProvince;

	@FXML
	private JFXComboBox<String> comboDistrict;

	@FXML
	private JFXTextField textQuarter;

	@FXML
	private JFXTextField textStreet;

	@FXML
	private JFXTextField textDoorNumber;

	@FXML
	private JFXTextField doorPostalCode;

	@FXML
	private JFXTextField textName;

	@FXML
	private JFXTextField textPhone;

	@FXML
	private JFXRadioButton radioSingular;

	@FXML
	private JFXRadioButton radioEnterprise;

	@FXML
	private Label lblIdentityNo;

	@FXML
	private JFXTextField txtIdentityNo;

	@FXML
	private Label lblSex;

	@FXML
	private JFXComboBox<String> comboSex;

	@FXML
	private Label lblCompanyName;

	@FXML
	private JFXTextField txtCompanyName;

	private List<String> provinces;

	private List<String> districts;

	private EntityManager entityManager;

	private ToggleGroup individualOrEnterprise;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		entityManager = MainClass.entityManager;
		individualOrEnterprise = new ToggleGroup();
		radioEnterprise.setToggleGroup(individualOrEnterprise);
		radioSingular.setToggleGroup(individualOrEnterprise);
		fillComboProvinces();
	}

	@FXML
	void selectEnterprise() {
		lblIdentityNo.setText("Vergi no");
		lblSex.setVisible(false);
		comboSex.setVisible(false);
		lblCompanyName.setVisible(true);
		txtCompanyName.setVisible(true);
	}

	@FXML
	void selectIndividual() {
		lblIdentityNo.setText("T.C.");
		lblSex.setVisible(true);
		comboSex.setVisible(true);
		lblCompanyName.setVisible(false);
		txtCompanyName.setVisible(false);
	}

	@FXML
	public void save() {
		RadioButton selected = (RadioButton) individualOrEnterprise.selectedToggleProperty().getValue();
		System.out.println(selected.getText());
	}

	private void fillComboProvinces() {
		Query query = entityManager.createNativeQuery("SELECT DISTINCT il_isim FROM ilceler ORDER BY il_isim ASC");
		provinces = query.getResultList();
		comboProvince.getItems().setAll(provinces);
	}

	@FXML
	public void listDistrictsOfProvince() {
		// ile göre ilçe listeleme
		Query query = entityManager.createNativeQuery("SELECT ilce_isim FROM ilceler WHERE il_isim = ?1");
		query.setParameter(1, comboProvince.getValue());
		districts = query.getResultList();
		comboDistrict.getItems().clear();
		comboDistrict.getItems().addAll(districts);
	}

	@FXML
	public void closeDialog() {
		CustomerController.createCustomerDialog.close();
	}

}
