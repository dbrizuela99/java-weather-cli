package org.example;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class WeatherAPICall {

    public static void main(String[] args) throws  Exception {

        //build the request with all headers
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("http://api.weatherapi.com/v1//current.json"))
                .GET()
                .build();
        //initialize client
        HttpClient httpClient = HttpClient.newHttpClient();
        //store the response you send to the client
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //create Gson object to read json
        Gson gson = new Gson();
        WeatherResponse realWeather = gson.fromJson(response.body(), WeatherResponse.class);
        System.out.println("The weather in: " + realWeather.getLocation().getName() + " is " + realWeather.getCurrent().getTemp_f() + " it is also " + realWeather.getCurrent().getCondition().getText());

    }
}