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

import entity.Adress;
import entity.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
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
	private JFXTextField textAdressPhone;

	@FXML
	private JFXTextField textStreet;

	@FXML
	private JFXTextField textDoorNumber;

	@FXML
	private JFXTextField txtPostalCode;

	@FXML
	private JFXTextField textName;

	@FXML
	private JFXTextField textSurname;

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
		entityManager = EntityManagerUtility.getEntityManager();
		fillComboProvinces();
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
		// adress
		String province = comboProvince.getValue();
		String district = comboDistrict.getValue();
		String adressPhone = textAdressPhone.getText().trim();
		String street = textStreet.getText().trim();
		String doorNumber = textDoorNumber.getText().trim();
		// general
		String type = selected.getText();
		String name = textName.getText().trim() == null ? "" : textName.getText().trim();
		String surname = textSurname.getText().trim() == null ? "" : textSurname.getText().trim();

		int postalCode = 0;
		try {
			postalCode = Integer.parseInt(txtPostalCode.getText().trim());
		} catch (Exception e) {
			// int değil
		}
		long phoneNumber = 0;
		try {
			phoneNumber = Long.parseLong(textPhone.getText().trim());
		} catch (Exception e) {
			// long değil
		}
		long adressPhoneNumber = 0;
		try {
			adressPhoneNumber = Long.parseLong(adressPhone);
		} catch (Exception e) {
			// long değil
		}
		if (postalCode != 0 || phoneNumber != 0 || adressPhoneNumber != 0) {
			// bireysel
			if ("Bireysel".equals(type)) {
				long tc = 0;
				String sex = comboSex.getValue();

				try {
					tc = Long.parseLong(txtIdentityNo.getText().trim());
				} catch (Exception e) {
					createAlertDialog(AlertType.INFORMATION, "Bilgi", null, "T.C. nümerik olmalıdır.").show();
				}
				if (tc != 0) {
					// save
					entityManager.getTransaction().begin();
					entityManager.persist(new Customer(tc, 0, type, name, surname, phoneNumber));
					entityManager.getTransaction().commit();
					Query query = entityManager.createNativeQuery("SELECT idCustomer FROM Customer WHERE tc=?1");
					query.setParameter(1, tc);
					List<Integer> result = query.getResultList();
					// oluşturulan musterinin idCustomer degeri idAdress tablosuna yazilir
					entityManager.getTransaction().begin();
					Adress adress = new Adress();
					adress.setCountry("Turkey");
					adress.setCounty(province);
					adress.setCity(district);
					adress.setStreet(street);
					adress.setDoorNumber(doorNumber);
					adress.setPostalCode(postalCode);
					adress.setIdCustomer(result.get(0));
					adress.setPhoneNumber(adressPhoneNumber);
					adress.setType(type);
					entityManager.persist(adress);
					entityManager.getTransaction().commit();
				}
			} else { // kurumsal
				String taxNumber = txtIdentityNo.getText().trim();
				String companyName = txtCompanyName.getText().trim();

			}
		} else {
			createAlertDialog(AlertType.INFORMATION, "Bilgi", null,
					"Posta kodu veya telefon numaralarından biri boş geçiliyor!").show();
		}
	}

	private Alert createAlertDialog(Alert.AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.headerTextProperty().setValue(header);
		alert.titleProperty().set(title);
		alert.contentTextProperty().set(content);
		return alert;
	}

	private void fillComboProvinces() {
		System.out.println("fillcomboprovinces");
		Query query = entityManager.createNativeQuery("SELECT DISTINCT il_isim FROM Provinces ORDER BY il_isim ASC");
		provinces = query.getResultList();
		comboProvince.getItems().setAll(provinces);
	}

	@FXML
	public void listDistrictsOfProvince() {
		// ile göre ilçe listeleme
		Query query = entityManager.createNativeQuery("SELECT ilce_isim FROM Provinces WHERE il_isim = ?1");
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
