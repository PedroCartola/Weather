package myproject.weatherapi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class weather extends Application {

    static Parent menuScene;
    static Parent apiScene;
    String scr = "menu";

    @FXML
    public void start(Stage Primarystage) throws Exception {

        switch (scr) {
            case "api":
                FXMLLoader FXMLapi = new FXMLLoader(getClass().getResource("view/FXMLApi.fxml"));
                apiScene = FXMLapi.load();
                Stage stageApi = new Stage();
                stageApi.setScene(new Scene(menuScene));
                stageApi.show();
                break;
            case "menu":
                FXMLLoader FXMLmenu = new FXMLLoader(getClass().getResource("view/FXMLMenu.fxml"));
                menuScene = FXMLmenu.load();
                Stage stageMenu = new Stage();
                stageMenu.setScene(new Scene(menuScene));
                stageMenu.show();
                break;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
