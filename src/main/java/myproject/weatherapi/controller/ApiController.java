package myproject.weatherapi.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import myproject.weatherapi.api.apiWeather;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import myproject.weatherapi.model.UsuarioDAO;
import myproject.weatherapi.model.UsuarioDTO;
import myproject.weatherapi.weather;
import org.hibernate.query.derived.AnonymousTupleEmbeddedEntityIdentifierMapping;

import javax.swing.*;

public class ApiController implements Initializable {

    @FXML
    private TextField txtCidade;

    @FXML
    private Label lblNome;

    private UsuarioDTO usuarioLogado;

    @FXML
    public void setUsuarioLogado(UsuarioDTO usuario) {
        this.usuarioLogado = usuario;
        atualizarLabelNome();
    }

    private void atualizarLabelNome() {
        if (usuarioLogado != null && usuarioLogado.getNome() != null) {
            lblNome.setText(usuarioLogado.getNome());
        } else {
            lblNome.setText("Usuário");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDTO usuario = new UsuarioDTO();
        lblNome.setText(usuario.getNome());
    }

    @FXML
    private void enviaInformcao (ActionEvent event) {
        try {
            String cidade = txtCidade.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/respostaApi.fxml"));
            Parent respostaApiScene = loader.load();

            respostaApiController apiController = loader.getController();

            apiController.setCidade(cidade);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(respostaApiScene));
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao mudar de tela: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void voltarTela (ActionEvent event) {
        try {
            Parent menuScene = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(menuScene));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao mudar de tela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
