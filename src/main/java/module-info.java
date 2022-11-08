module com.example.portofolie3sdversion2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.portofolie3sdversion2 to javafx.fxml;
    exports com.example.portofolie3sdversion2;
}