module com.example.partnervermittlung {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.partnervermittlung to javafx.fxml;
    exports com.example.partnervermittlung;
}