module com.example.partnervermittlung {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;


    opens com.example.partnervermittlung to javafx.fxml;
    exports com.example.partnervermittlung;
    exports com.example.partnervermittlung.view;
    exports com.example.partnervermittlung.service;
    opens com.example.partnervermittlung.view to javafx.fxml;
}