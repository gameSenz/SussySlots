module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.SussySlots to javafx.fxml;
    exports com.example.SussySlots;
}