module myproject.weatherapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.sql;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;


    opens controller to javafx.fxml;
    exports controller;

    opens myproject.weatherapi to javafx.fxml;
    exports myproject.weatherapi;
}