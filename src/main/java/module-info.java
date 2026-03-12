module com.example.miniproyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.miniproyecto1 to javafx.fxml;
    opens com.example.miniproyecto1.view to javafx.fxml;
    opens com.example.miniproyecto1.util to javafx.fxml;
    opens com.example.miniproyecto1.controller to javafx.fxml;
    exports com.example.miniproyecto1;
}