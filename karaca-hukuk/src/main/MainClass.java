package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class MainClass extends Application {

    private static Stage primaryStage;

    private static Stack<Scene> listOfScenes = new Stack<>();

    public static void main(String[] args) {
        launch(args);
    }

    public static void closeAndReturnLast() {
        listOfScenes.pop();
        System.gc();
        primaryStage.setScene(listOfScenes.peek());
    }

    public static void createNew(Scene scene) {
        primaryStage.setScene(scene);
        listOfScenes.push(scene);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        /*
        kötü kod
        Parent splashParent = FXMLLoader.load(getClass().getResource("/fxml/splash.fxml"));
        Scene splashScene = new Scene(splashParent);
        Stage splashStage = new Stage();
        splashStage.setScene(splashScene);
        splashStage.setWidth(400);
        splashStage.setHeight(400);
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.show();
        Thread.sleep(3000);
        splashStage.hide();
        System.gc();
        */

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(root);
        arg0.setScene(scene);
        arg0.setTitle("HBO");
        arg0.setWidth(1200);
        arg0.setHeight(700);
        arg0.setResizable(false);
        arg0.show();
    }
}