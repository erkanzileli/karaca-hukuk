package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClass extends Application {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");

	public static EntityManager entityManager;

	public static void main(String[] args) {
		entityManager = entityManagerFactory.createEntityManager();
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		/*
		 * kötü kod Parent splashParent =
		 * FXMLLoader.load(getClass().getResource("/fxml/splash.fxml")); Scene
		 * splashScene = new Scene(splashParent); Stage splashStage = new Stage();
		 * splashStage.setScene(splashScene); splashStage.setWidth(400);
		 * splashStage.setHeight(400); splashStage.initStyle(StageStyle.TRANSPARENT);
		 * splashStage.show(); Thread.sleep(3000); splashStage.hide(); System.gc();
		 */

		Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));
		Scene scene = new Scene(root);
		arg0.setScene(scene);
		arg0.setTitle("Hukuk Bürosu Otomasyonu");
		arg0.initStyle(StageStyle.TRANSPARENT);
		arg0.setResizable(false);
		arg0.show();
	}
}