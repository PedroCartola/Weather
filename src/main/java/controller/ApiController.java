package controller;

import api.apiWeather;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import myproject.weatherapi.weather;

public class ApiController implements Initializable {

    @FXML
    private TextField txtCidade;

    @FXML
    private TextFlow txfResposta;

    @FXML
    private void mudarTelaMenu(ActionEvent e){
        weather.launch("menu");
    }

    @FXML
    private void apiFunction(ActionEvent a){
        txfResposta.getChildren().clear();
        String resposta = txtCidade.getText();
        String cidade = apiWeather.api(resposta);
        Text text = new Text(cidade);
        txfResposta.getChildren().add(text);
        txtCidade.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
