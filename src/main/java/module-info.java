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
    requires org.hibernate.orm.core;
    requires com.fasterxml.jackson.databind;
    requires org.postgresql.jdbc;


    opens myproject.weatherapi.controller to javafx.fxml;
    exports myproject.weatherapi.controller;

    opens myproject.weatherapi to javafx.fxml;
    exports myproject.weatherapi;
}