package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utility.EntityManagerUtility;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

public class CreateEmployeeController implements Initializable {

	@FXML
	private JFXTextField txtName;

	@FXML
	private JFXTextField txtSurname;

	@FXML
	private JFXTextField txtTC;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtMobilePhone;

	@FXML
	private JFXComboBox<String> comboPosition;

	@FXML
	private JFXComboBox<String> comboSex;

	@FXML
	private JFXTextField txtPhone;

	private EntityManager entityManager;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		entityManager=EntityManagerUtility.getEntityManager();
		comboPosition.getItems().addAll("Avukat", "Sekreter");
		comboSex.getItems().addAll("Kadın", "Erkek");
	}

	@FXML
	void closeDialog(ActionEvent event) {
		EmployeeController.getCreateEmployeeDialog().close();
	}

	@FXML
	void save(ActionEvent event) {

	}
}
