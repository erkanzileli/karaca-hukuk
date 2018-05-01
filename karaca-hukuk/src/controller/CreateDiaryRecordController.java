package controller;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import entity.Agenda;
import entity.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import main.MainClass;
import utility.EntityManagerUtility;

public class CreateDiaryRecordController implements Initializable {

	private Member currentMember;

	private EntityManager entityManager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("CreateDiaryRecordController.initialize");
		currentMember = MainClass.member;
		entityManager = EntityManagerUtility.getEntityManager();
	}

	@FXML
	void createNote() {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Not Ekleyin");

		// Set the button types.
		ButtonType okButtonType = new ButtonType("Ekle", ButtonBar.ButtonData.OK_DONE);
		ButtonType closeButtonType = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(closeButtonType, okButtonType);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(8);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));

		TextField title = new TextField();
		title.setPromptText("Konu");
		TextArea content = new TextArea();
		content.setPromptText("İçerik");

		gridPane.add(new Label("Konu:"), 0, 0);
		gridPane.add(title, 1, 0);
		gridPane.add(new Label("İçerik"), 0, 1);
		gridPane.add(content, 1, 1);

		dialog.getDialogPane().setContent(gridPane);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == okButtonType) {
				return new Pair<>(title.getText(), content.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pair -> {
			System.out.println(pair.getKey() + " " + pair.getValue());

			String titleText = pair.getKey().trim();
			String contentText = pair.getValue().trim();

			if (!"".equals(titleText) && !"".equals(contentText)) {
				Agenda record = new Agenda();
				record.setIdMember(currentMember.getIdMember());
				record.setHeader(titleText);
				record.setDescription(contentText);
				record.setDate(Date.valueOf(LocalDate.now()));
				entityManager.getTransaction().begin();
				entityManager.persist(record);
				entityManager.getTransaction().commit();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Eksik veri girişi.");
				alert.setHeaderText(null);
				alert.setContentText("Eksik kısım bırakmayınız.");
				alert.show();
			}
		});
	}

}
