module com.example.rngguesser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rngguesser to javafx.fxml;
    exports com.example.rngguesser;
}