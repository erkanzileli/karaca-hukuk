package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import entity.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import utility.EntityManagerUtility;

public class CustomerController implements Initializable {

	@FXML
	private StackPane root;

	@FXML
	private JFXTextField textSearch;

	@FXML
	private JFXButton btnCreate;

	@FXML
	private JFXTreeTableView<CustomerModel> tableView;

	@FXML
	private TreeTableColumn<CustomerModel, String> columnCustomerName;

	@FXML
	private TreeTableColumn<CustomerModel, String> columnCustomerPhone;

	@FXML
	private TreeTableColumn<CustomerModel, String> columnCustomerType;

	@FXML
	private TreeTableColumn<CustomerModel, String> columnDate;

	public static JFXDialog createCustomerDialog;

	private EntityManager entityManager;

	private List<Customer> customers;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("CustomerController.initialize");
		entityManager = EntityManagerUtility.getEntityManager();
		customers = getData();
		/*
		 * getData().forEach(c -> { System.out.println(c.getTc()); list.add(new
		 * CustomerModel(c.getName(),"",c.getType(),"")); });
		 */
		/*
		 * final TreeItem<CustomerModel> treeItem = new
		 * RecursiveTreeItem<>(list,RecursiveTreeObject::getChildren);
		 * tableView.getColumns().setAll(columnCustomerName,columnCustomerPhone,
		 * columnCustomerType,columnDate); tableView.setRoot(treeItem);
		 * tableView.setShowRoot(false);
		 */
	}

	private List<Customer> getData() {
		TypedQuery<Customer> query = (TypedQuery<Customer>) entityManager.createNativeQuery("SELECT * FROM Customer");
		return query.getResultList();

	}

	class CustomerModel extends RecursiveTreeObject<CustomerModel> {
		String customerName;
		String customerPhone;
		String customerType;
		String registerDate;

		public CustomerModel(String customerName, String customerPhone, String customerType, String registerDate) {
			this.customerName = customerName;
			this.customerPhone = customerPhone;
			this.customerType = customerType;
			this.registerDate = registerDate;
		}
	}

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

}
