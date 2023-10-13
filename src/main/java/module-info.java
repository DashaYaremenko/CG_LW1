module com.example.cg_lw1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cg_lw1 to javafx.fxml;
    exports com.example.cg_lw1;
}