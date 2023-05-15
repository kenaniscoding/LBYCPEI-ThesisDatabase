module com.example.opencsvdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires org.controlsfx.controls;

    opens com.example.opencsvdemo to javafx.fxml;
    exports com.example.opencsvdemo;
}