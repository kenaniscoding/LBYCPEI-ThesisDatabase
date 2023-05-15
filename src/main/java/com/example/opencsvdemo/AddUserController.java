package com.example.opencsvdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddUserController {
    @FXML
    private TextField userField;

    @FXML
    private TextField passField;

    @FXML
    private RadioButton adminButton;

    @FXML
    void addUser(ActionEvent event) throws IOException{
        writeUser();
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void writeUser(){
        String user = userField.getText();
        String pass = passField.getText();
        String admin = "";
        if (adminButton.isSelected()){
            admin = "1";
        } else {
            admin = "0";
        }
        String acc = "\n" + user + "," + pass + "," + admin;
        String filename = "C:\\Users\\Kenan\\Downloads\\OpenCsvDemo-masterWithadduser\\OpenCsvDemo-master\\src\\main\\resources\\users.txt";

        try (FileWriter writer = new FileWriter(filename, true)) {
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(acc);
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Writing success!");
    }


}
