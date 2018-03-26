package controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LawsuitsController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTreeTableView<?> tableViewDavalar;

    @FXML
    private TreeTableColumn<?, ?> columnAcilisTarihi;

    @FXML
    private TreeTableColumn<?, ?> columnDurumu;

    @FXML
    private TreeTableColumn<?, ?> columnMahkemeAdi;

    @FXML
    private TreeTableColumn<?, ?> columnMuvekkilAdi;

    @FXML
    private TreeTableColumn<?, ?> columnKarsiTaraf;

    @FXML
    private JFXDatePicker dateStart;

    @FXML
    private JFXDatePicker dateEnd;

    @FXML
    private JFXComboBox<String> comboBoxStatus;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    void listDetails() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/details.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setBody(parent);
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LawsuitsController.initialize");
    }
}
