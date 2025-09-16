package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private void voltarTela (ActionEvent event) {
        try {
            Parent menuScene = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(menuScene));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao mudar para tela de login: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
