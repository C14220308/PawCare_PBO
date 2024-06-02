module com.example.project_pbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project_pbo to javafx.fxml;
    exports com.example.project_pbo;
    exports com.example.project_pbo.Admin;
    opens com.example.project_pbo.Admin to javafx.fxml;
}