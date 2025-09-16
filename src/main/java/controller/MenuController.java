package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myproject.weatherapi.weather;

public class MenuController{

    @FXML
    private void mudarTelaLogin (ActionEvent event) {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loginRoot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao mudar para tela de login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void mudarTelaCadastro (ActionEvent event) {
        try {
            Parent cadastroRoot = FXMLLoader.load(getClass().getResource("/view/cadastrar.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(cadastroRoot));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao mudar para tela de login: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
