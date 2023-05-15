package com.example.opencsvdemo;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label prompt;
    private users userData = new users();

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        userData.loadUsers("src/main/resources/users.txt");
        MainApplication loginApp = new MainApplication();
        boolean loginWork = true;
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            prompt.setText("Enter your username & password!");
            prompt.setTextFill(Color.rgb(210, 39, 30));
        }
        for(int i=0; i<userData.getSize(); i++){
            String usernameTxt = userData.getUser(i).getUsername();
            String passwordTxt = userData.getUser(i).getPassword();
            String isAdminTxt = (userData.getUser(i).isAdmin());
            if (username.getText().equals(usernameTxt) && password.getText().equals(passwordTxt)){
                System.out.println(isAdminTxt);
                if (isAdminTxt.equals(Integer.toString(1))){
                    loginWork=false;
                    System.out.println("your are admin");
                    prompt.setTextFill(Color.rgb(21, 117, 84));
                    prompt.setText("Login SUCCESS! Admin Access Granted ...");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        try { //TODO MAKE FXML FOR ADMIN
                            loginApp.changeScene("Thesis-Database-ADMIN.fxml");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    pause.play();
                } else {
                    loginWork=false;
                    System.out.println("you are student");
                    prompt.setTextFill(Color.rgb(21, 117, 84));
                    prompt.setText("Login SUCCESS! Student Access Granted ...");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        try { //TODO MAKE FXML FOR STUDENT
                            loginApp.changeScene("Thesis-Database.fxml");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    pause.play();
                }
            }
        }
        if (loginWork){
            prompt.setText("Wrong Username or Password!");
            prompt.setTextFill(Color.rgb(210, 39, 30));
        }

        /*
        if (username.getText().toString().equals("STUDENT") && password.getText().toString().equals("student")) {
            prompt.setTextFill(Color.rgb(21, 117, 84));
            prompt.setText("Login SUCCESS! Student Access Granted ...");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                try { //TODO MAKE FXML FOR STUDENT
                    loginApp.changeScene("Thesis-Database.fxml");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pause.play();
        } else if (username.getText().toString().equals("ADMIN") && password.getText().toString().equals("admin")) {
            prompt.setTextFill(Color.rgb(21, 117, 84));
            prompt.setText("Login SUCCESS! Admin Access Granted ...");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                try { //TODO MAKE FXML FOR ADMIN
                    loginApp.changeScene("Thesis-Database-ADMIN.fxml");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pause.play();

        } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
         */

    }
    @FXML //platform
    private void onExit(ActionEvent e) {
        Platform.exit();
    }
}