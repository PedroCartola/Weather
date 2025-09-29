package myproject.weatherapi.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class apiWeather {

    private static String API_URL = "http://api.weatherapi.com/v1/current.json?key=";
    private static String key = "331ac421814047fc818174700250108";

    public static String api(String cidade) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String urlCompleta = API_URL + key + "&q=" + cidade;
            System.out.println("URL da API: " + urlCompleta);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlCompleta))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "{\"error\": \"HTTP " + response.statusCode() + "\", \"message\": \"Request failed\"}";
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Exception na API: " + e.getMessage());
            return "{\"error\": \"Exception\", \"message\": \"" + e.getMessage() + "\"}";
        }
    }
}
