module com.example.csd214test3baswinsureshbabu {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.csd214test3baswinsureshbabu to javafx.fxml;
    exports com.example.csd214test3baswinsureshbabu;
}