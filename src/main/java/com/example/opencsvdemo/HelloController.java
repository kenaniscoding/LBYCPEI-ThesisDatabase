package com.example.opencsvdemo;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    private static int index=1;
    private static int rowCount=0;

    public static final String CSV_FILENAME= "ECE_CpE_Thesis_Groups_2nd_Term_AY2021_2022.csv";

    private final ObservableList<ThesisRecord> database = FXCollections.observableArrayList();

    @FXML
    private TableView<ThesisRecord> tableView;

    @FXML
    TableColumn<ThesisRecord, String> titleColumn;

    @FXML
    TableColumn<ThesisRecord, String> groupColumn;

    @FXML
    TableColumn<ThesisRecord, String> trmColumn;

    @FXML
    TableColumn<ThesisRecord, String> syColumn;

    @FXML
    TableColumn<ThesisRecord, String> noColumn;

    @FXML
    TableColumn<ThesisRecord, String> membersColumn;

    @FXML
    TableColumn<ThesisRecord, String> adviserColumn;

    @FXML
    TableColumn<ThesisRecord, String> chairColumn;

    @FXML
    TableColumn<ThesisRecord, String> panelist1Column;

    @FXML
    TableColumn<ThesisRecord, String> panelist2Column;

    @FXML
    TableColumn<ThesisRecord, String> statusColumn;
    @FXML
    TextField filterField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //        String filename = "C:\\Users\\ECE\\IdeaProjects\\OpenCsvDemo\\target\\classes\\ECE_CpE_Thesis_Groups_2nd_Term_AY2021_2022.csv";
        //TODO CHANGE THIS TO YOUR FILE LOCATION IF THE CSV FILE DOESN'T READ

        // Associate columns to data
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        trmColumn.setCellValueFactory(new PropertyValueFactory<>("term"));
        syColumn.setCellValueFactory(new PropertyValueFactory<>("sy"));
        noColumn.setCellValueFactory(new PropertyValueFactory<>("grp_number"));
        membersColumn.setCellValueFactory(new PropertyValueFactory<>("members"));
        adviserColumn.setCellValueFactory(new PropertyValueFactory<>("adviser"));
        chairColumn.setCellValueFactory(new PropertyValueFactory<>("chair_panel"));
        panelist1Column.setCellValueFactory(new PropertyValueFactory<>("panelist1"));
        panelist2Column.setCellValueFactory(new PropertyValueFactory<>("panelist2"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadCSV();
        tableView.getItems().setAll(database);

        //Documentation
        //https://www.youtube.com/watch?v=FeTrcNBVWtg
        FilteredList<ThesisRecord> filteredData = new FilteredList<>(database,b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(name ->{
                if (newValue ==null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (name.getTitle().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
        });
        });
        SortedList<ThesisRecord> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private void loadCSV(){
        //TODO 1change this to your file location
        String filename = "src/main/resources/" + CSV_FILENAME; // relative to project root directory
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).withSkipLines(1).build()) // Skip header or 1st row
        {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) //Read one line at a time
            {
                System.out.println(Arrays.toString(nextLine));
                ThesisRecord thesisRecord = new ThesisRecord(nextLine);
                database.add(thesisRecord);
                rowCount++;
            }
            System.out.println("Row Count="+rowCount);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
    public void addRecord(ActionEvent actionEvent) throws IOException {
        // Open a pop-up window, then return data
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-record-view.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 1100, 720);
        scene.getStylesheets().add("stylesheet.css");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        tableView.refresh();
    }
    public void onRefreshClick(ActionEvent actionEvent) {
//        tableView.getItems().setAll();
        loadNewCSV();
//        ThesisRecord record = new ThesisRecord();
//        tableView.getItems().add(record);
//        tableView.getItems().setAll(database);
    }
    public void exitProgram(ActionEvent e){
        Platform.exit();
    }
    public void menuProgram(ActionEvent e) throws IOException {
        rowCount=0;
        index=1;
        System.out.println("rowCount="+rowCount+" \nindex="+index);
        String menu = "login-view.fxml";
        MainApplication app = new MainApplication();
        app.changeScene(menu);
    }
    private void loadNewCSV(){
        //TODO 2change this to your file location
        String filename = "src/main/resources/" + CSV_FILENAME; // relative to project root directory
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).withSkipLines(rowCount+index).build()) // Skip header or 1st row
        {
            String[] nextLine;
            int numCheck=0;
            while ((nextLine = reader.readNext()) != null) //Read one line at a time
            {
                System.out.println(Arrays.toString(nextLine));
                ThesisRecord thesisRecord = new ThesisRecord(nextLine);
                database.add(thesisRecord);
                numCheck++;
            }
            System.out.println("numCheck="+numCheck);
            if(numCheck!=0){
                System.out.println("index="+index);
                index++;
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}