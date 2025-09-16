package myproject.weatherapi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class weather extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            Parent menuScene = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(menuScene));
            primaryStage.show();
        } catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
