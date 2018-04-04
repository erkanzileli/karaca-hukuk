package controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField textSearch;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXTreeTableView<?> tableView;

    @FXML
    private TreeTableColumn<?, ?> columnCustomerName;

    @FXML
    private TreeTableColumn<?, ?> columnCustomerPhone;

    @FXML
    private TreeTableColumn<?, ?> columnCustomerType;

    @FXML
    private TreeTableColumn<?, ?> columnDate;

    public static JFXDialog createCustomerDialog;

    @FXML
    void search() {

    }

    @FXML
    void create() {
        Parent createCustomer = null;
        try {
            createCustomer = FXMLLoader.load(getClass().getResource("/fxml/createCustomer.fxml"));
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(createCustomer);
        JFXDialog dialog = new JFXDialog(root, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.setOverlayClose(false);
        createCustomerDialog = dialog;
        dialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("CustomerController.initialize");
    }
}
