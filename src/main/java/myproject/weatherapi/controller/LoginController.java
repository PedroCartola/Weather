package myproject.weatherapi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myproject.weatherapi.model.UsuarioDAO;
import myproject.weatherapi.model.UsuarioDTO;
import myproject.weatherapi.validator.UsuarioValidator;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    private final UsuarioValidator usuarioValidador = new UsuarioValidator();

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

    public void logar(javafx.event.ActionEvent event) throws SQLException, IOException {
        if (usuarioValidador.validarUsuario(txtEmail.getText(), txtSenha.getText())) {

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            String senha = txtSenha.getText();
            String email = txtEmail.getText();

            UsuarioDTO logarUsuario = new UsuarioDTO();
            logarUsuario.setEmail(email);
            logarUsuario.setSenha(senha);

            if (usuarioDAO.login(logarUsuario)) {
                UsuarioDTO usuarioLogado = usuarioDAO.buscarEmail(email);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/api.fxml"));
                Parent menuScene = loader.load();

                ApiController apiController = loader.getController();
                if (apiController != null) {
                    apiController.setUsuarioLogado(usuarioLogado);
                } else {
                    System.err.println("Erro: apiController é null!");
                }

                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(menuScene));
                stage.show();
            }
            else {
                mostrarAlerta("Erro", "Email ou enha incorretos");
            }
        }
        else {
            mostrarAlerta("Erro", "Preenchatodos os campos");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
