module com.example.practicjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens gui to javafx.fxml;
    exports gui;
}