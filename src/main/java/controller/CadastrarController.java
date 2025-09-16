package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.UsuarioDAO;
import model.UsuarioDTO;
import validator.UsuarioValidator;

public class CadastrarController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private Button btnCadastrar;

    private final UsuarioValidator usuarioValidador = new UsuarioValidator();

    @FXML
    private void cadastrar(ActionEvent event) throws SQLException {
        if (usuarioValidador.validarUsuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText())) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            String nome = txtNome.getText();
            String senha = txtSenha.getText();
            String email = txtEmail.getText();
            UsuarioDTO novoUsuario = new UsuarioDTO(nome, email, senha);
            usuarioDAO.cadastrarUsuario(novoUsuario);
            voltarTela(event);
        }
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
