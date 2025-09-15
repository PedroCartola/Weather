package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class apiWeather {

    private static String API_URL = "http://api.weatherapi.com/v1/current.json?key=";
    private static String key = "331ac421814047fc818174700250108";

    public static String api (String cidade) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + key + "&q=" + cidade))
                .build();
        String resultado = null;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                resultado = response.body();
            } else {
                resultado = "Request failed: " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            resultado = "Erro: " + e.getMessage();
        }
        return resultado;
    }
}
