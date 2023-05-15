package com.example.opencsvdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Scene scene;
    private static Stage thisStage;
    @Override
    public void start(Stage stage) throws IOException {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        thisStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
//        System.out.println("width="+0.6*screen.getWidth());
//        System.out.println("height="+0.8*screen.getHeight());
        scene = new Scene(fxmlLoader.load(), 1250, 793);
        scene.getStylesheets().add("stylesheet.css");
        stage.setTitle("ECE/CpE Thesis Management System");
        stage.setScene(scene);
        stage.show();
        System.out.println("Username: ADMIN");
        System.out.println("Password: admin\n");
        System.out.println("Username: STUDENT");
        System.out.println("Password: student");
    }
    public void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        thisStage.getScene().setRoot(fxmlLoader.load());
    }
//    public Scene getScene(){
//        return scene;
//    }

    public static void main(String[] args) {
        launch();
    }
}