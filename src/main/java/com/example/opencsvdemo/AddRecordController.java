package com.example.opencsvdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class AddRecordController {


    @FXML
    private TextField adviserField;

    @FXML
    private TextField groupField;

    @FXML
    private TextField groupNumField;

    @FXML
    private TextField membersField;

    @FXML
    private TextField panelChairField;

    @FXML
    private TextField panelist1Field;

    @FXML
    private TextField panelist2Field;

    @FXML
    private TextField statusField;

    @FXML
    private TextField syField;

    @FXML
    private TextField termField;

    @FXML
    public TextField titleField;
//    private ObservableList<ThesisRecord> thesisObservableList;

    @FXML
    void addRecord(ActionEvent event) throws IOException {
        writeRecord();
        closeStage(event);
    }


    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    private void writeRecord() {

        String title = titleField.getText();
        String group = groupField.getText();
        String term = termField.getText();
        String sy = syField.getText();
        String grp_number = groupNumField.getText();
        String members = membersField.getText();
        String adviser = adviserField.getText();
        String chair_panel = panelChairField.getText();
        String panelist1 = panelist1Field.getText();
        String panelist2 = panelist2Field.getText();
        String status = statusField.getText();

        String[] record = {
                title,
                group,
                term,
                sy,
                grp_number,
                members,
                adviser,
                chair_panel,
                panelist1,
                panelist2,
                status};

        wrapComma(record);
        //TODO 3change this to your file location
        String filename = "src/main/resources/" + HelloController.CSV_FILENAME;
        try (FileWriter writer = new FileWriter(filename, true)) {
            BufferedWriter bw = new BufferedWriter(writer);
            if (!newLineExists(filename)) {
                bw.newLine();
            }
            bw.write(String.join(",", record));
//            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Writing success!");
    }

    private void wrapComma(String[] record) {
        // Wraps string entries containing comma with quotes "
        for (int i = 0; i < record.length; i++) {
            if (record[i].contains(",")) {
                record[i] = "\"" + record[i] + "\"";
            }
        }
    }

    private boolean newLineExists(String filename) throws IOException {
        // Checks for end of file new line
        // Source: https://stackoverflow.com/questions/28795440/check-if-a-new-line-exists-at-end-of-file
        File file = new File(filename);
        RandomAccessFile fileHandler = new RandomAccessFile(file, "r");
        long fileLength = fileHandler.length() - 1;
        if (fileLength < 0) {
            fileHandler.close();
            return true;
        }
        fileHandler.seek(fileLength);
        byte readByte = fileHandler.readByte();
        fileHandler.close();

        if (readByte == 0xA || readByte == 0xD) {
            return true;
        }
        return false;
    }

}

