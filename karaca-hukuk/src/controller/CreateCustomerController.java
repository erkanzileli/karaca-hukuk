package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import entity.Provinces;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import main.MainClass;
import utility.EntityManagerUtility;

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
		TypedQuery<Provinces> query = (TypedQuery<Provinces>) entityManager
				.createNativeQuery("SELECT DISTINCT il_isim FROM ilceler ORDER BY il_isim;", Provinces.class);
		query.getResultList().forEach(p -> {
			provinces.add(p.getProvinceName());
		});
		comboProvince.getItems().addAll(provinces);
	}

	@FXML
	public void listDistrictsOfProvince() {
		// ile göre ilçe listeleme

	}

	@FXML
	public void closeDialog() {
		CustomerController.createCustomerDialog.close();
	}

}
