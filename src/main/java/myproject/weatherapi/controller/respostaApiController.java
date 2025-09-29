package myproject.weatherapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import myproject.weatherapi.api.apiWeather;
import org.postgresql.gss.GSSOutputStream;

import java.io.IOException;

public class respostaApiController {
    @FXML
    private Label lblCidade;

    @FXML
    private Label lblTemperatura;

    @FXML
    private Label lblCondicao;

    @FXML
    private Label lblUmidade;

    @FXML
    private Label lblVento;

    @FXML
    private Label lblSensacao;

    @FXML
    private Label lblRegiao;

    @FXML
    private Label lblPais;

    @FXML
    private Label lblUltimaAtualizacao;

    private String cidade;

    public void setCidade(String cidade2) {
        this.cidade = cidade2;

        if (cidade != null && !cidade.isEmpty()) {
            buscarDadosClimaticos();
        }
    }

    private void buscarDadosClimaticos() {
        System.out.println(cidade);
        try {
            String resultadoJson = apiWeather.api(cidade);

            processarRespostaClima(resultadoJson);

        } catch (Exception e) {
            if (lblCidade != null) {
                lblCidade.setText("Erro ao buscar dados");
            }
            if (lblTemperatura != null) {
                lblTemperatura.setText("Tente novamente");
            }
        }
    }

    private void processarRespostaClima(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            // Extrai informações específicas do JSON
            String localizacao = root.path("location").path("name").asText("N/A");
            String regiao = root.path("location").path("region").asText("N/A");
            String pais = root.path("location").path("country").asText("N/A");
            String temperatura = root.path("current").path("temp_c").asText("N/A");
            String sensacao = root.path("current").path("feelslike_c").asText("N/A");
            String condicao = root.path("current").path("condition").path("text").asText("N/A");
            String umidade = root.path("current").path("humidity").asText("N/A");
            String vento = root.path("current").path("wind_kph").asText("N/A");
            String ultimaAtualizacao = root.path("current").path("last_updated").asText("N/A");

            // Atualiza as labels com os dados extraídos
            atualizarLabelsClima(localizacao, regiao, pais, temperatura, sensacao,
                                    condicao, umidade, vento, ultimaAtualizacao);

        } catch (Exception e) {
            System.out.println("Erro ao processar JSON: " + e.getMessage());

            atualizarLabelsClima("Erro", "N/A", "N/A", "N/A", "N/A",
                    "Não foi possível carregar", "N/A", "N/A", "N/A");
        }
    }

    private void atualizarLabelsClima(String localizacao, String regiao, String pais,
                                      String temperatura, String sensacao, String condicao,
                                      String umidade, String vento, String ultimaAtualizacao) {

        if (lblCidade != null) {
            lblCidade.setText("Cidade: " + localizacao);
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblRegiao != null) {
            lblRegiao.setText("Região: " + regiao);
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblPais != null) {
            lblPais.setText("País: " + pais);
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblTemperatura != null) {
            lblTemperatura.setText("Temperatura: " + temperatura + "°C");
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblSensacao != null) {
            lblSensacao.setText("Sensação Térmica: " + sensacao + "°C");
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblCondicao != null) {
            lblCondicao.setText("Condição: " + condicao);
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblUmidade != null) {
            lblUmidade.setText("Umidade: " + umidade + "%");
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblVento != null) {
            lblVento.setText("Vento: " + vento + " km/h");
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
        if (lblUltimaAtualizacao != null) {
            lblUltimaAtualizacao.setText("Última Atualização: " + ultimaAtualizacao);
            System.out.println("lblCidade atualizada");
        } else {
            System.out.println("lblCidade é NULL!");
        }
    }

    @FXML
    private void initialize() {

    }

    @FXML
    public void VoltarTela (ActionEvent event) {
        try {
            Parent menuScene = FXMLLoader.load(getClass().getResource("/view/api.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(menuScene));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao mudar de tela: " + e.getMessage());
            e.printStackTrace();
        }
    }

}