package myproject.weatherapi.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myproject.weatherapi.model.UsuarioDAO;
import myproject.weatherapi.model.UsuarioDTO;
import myproject.weatherapi.validator.UsuarioValidator;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class CadastrarController{

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    private final UsuarioValidator usuarioValidador = new UsuarioValidator();


    @FXML
    private void voltarTela (javafx.event.ActionEvent event) {
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

    public void cadastrar(javafx.event.ActionEvent event) throws SQLException, IOException {
        if (usuarioValidador.validarUsuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText())) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            String nome = txtNome.getText();
            String senha = txtSenha.getText();
            String email = txtEmail.getText();
            UsuarioDTO novoUsuario = new UsuarioDTO(nome, email, senha);
            usuarioDAO.cadastrarUsuario(novoUsuario);
            Parent menuScene = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(menuScene));
            stage.show();
        }
    }
}
