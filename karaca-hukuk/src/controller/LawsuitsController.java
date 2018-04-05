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
    private JFXButton btnCreateLawsuit;

    @FXML
    private JFXButton btnFilter;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXTreeTableView<?> tableViewDavalar;

    @FXML
    private TreeTableColumn<?, ?> columnBeginDate;

    @FXML
    private TreeTableColumn<?, ?> columnStatus;

    @FXML
    private TreeTableColumn<?, ?> columnCourt;

    @FXML
    private TreeTableColumn<?, ?> columnLawsuitType;

    @FXML
    private TreeTableColumn<?, ?> columnAlly;

    @FXML
    private TreeTableColumn<?, ?> columnOpponent;

    @FXML
    void create(ActionEvent event) {
        Parent lawsuitFormDialog = null;
        try {
            lawsuitFormDialog = FXMLLoader.load(getClass().getResource("/fxml/createLawsuit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setBody(lawsuitFormDialog);
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.RIGHT);
        CreateLawsuitController.fxd=dialog;
        dialog.show();
    }

    @FXML
    void openFilterDialog() {
        Parent filterDialog = null;
        try {
            filterDialog = FXMLLoader.load(getClass().getResource("/fxml/lawsuitFilter.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setBody(filterDialog);
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.RIGHT);
        dialog.show();
    }

    @FXML
    void search() {

    }

    @FXML
    void listDetails() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/lawsuitsDetails.fxml"));
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
