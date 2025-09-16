module myproject.weatherapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.sql;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;
    requires static lombok;
    requires jakarta.persistence;
    requires spring.context;
    requires spring.data.jpa;


    opens myproject.weatherapi.controller to javafx.fxml;
    exports myproject.weatherapi.controller;

    opens myproject.weatherapi to javafx.fxml;
    exports myproject.weatherapi;
}