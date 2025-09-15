package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import myproject.weatherapi.weather;

public class MenuController implements Initializable {

    @FXML
    private void mudarTelaApi(ActionEvent e){
        weather.launch("api");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
