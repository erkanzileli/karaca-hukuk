package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class CreateDiaryRecordController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("CreateDiaryRecordController.initialize");
	}

	@FXML
	void createToDo() {
		
		TextInputDialog textInputDialog = new TextInputDialog();
		textInputDialog.headerTextProperty().setValue(null);
		textInputDialog.contentTextProperty().setValue("İçerik");
		textInputDialog.titleProperty().set("Hatırlatma Ekleyin");
		Optional<String> stringOptional = textInputDialog.showAndWait();
		
		// System.out.println(stringOptional.get());
		
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

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == okButtonType) {
				return new Pair<>(title.getText(), content.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pair -> {
			// System.out.println(pair.getKey() + " " + pair.getValue());
			// todo:add-database
		});
	}

}
