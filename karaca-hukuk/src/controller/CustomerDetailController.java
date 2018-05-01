package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;

public class CustomerDetailController implements Initializable {
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

	public static Customer selectedCustomer;

	private ToggleGroup individualOrEnterprise;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// toggle-group-settings
		individualOrEnterprise = new ToggleGroup();
		radioEnterprise.setToggleGroup(individualOrEnterprise);
		radioSingular.setToggleGroup(individualOrEnterprise);
		
		if ("Bireysel".equals(selectedCustomer.getType())) {
			individualOrEnterprise.selectToggle(radioSingular);
			selectIndividual();
			comboProvince.setEditable(false);
			
		} else {
			individualOrEnterprise.selectToggle(radioEnterprise);
			selectEnterprise();
		}
	}

	
	@FXML
	void closeDialog(ActionEvent event) {
		CustomerController.customerDetailsDialog.close();
	}

	@FXML
	void listDistrictsOfProvince() {

	}

	@FXML
	void save() {

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

}
