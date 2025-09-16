package myproject.weatherapi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myproject.weatherapi.infrastructure.entitys.Usuario;
import myproject.weatherapi.infrastructure.repository.usuarioRepository;
import org.springframework.stereotype.Service;

import java.awt.event.ActionEvent;
import java.io.IOException;

@Service

public class CadastrarController {

    private final usuarioRepository repository;

    public CadastrarController(usuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

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
