module com.example.lab7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    requires java.desktop;
    requires javafx.swing;

    opens com.example.lab7 to javafx.fxml;
    exports com.example.lab7;
}