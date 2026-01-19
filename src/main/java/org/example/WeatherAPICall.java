package org.example;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class WeatherAPICall {
    private final String key = "";

    void getCurrentWeather(String location) throws Exception {
        //build the request with all headers
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("http://api.weatherapi.com/v1/current.json?key=" + key + "&q=" + location))
                .GET()
                .build();
        //initialize client
        HttpClient httpClient = HttpClient.newHttpClient();
        //store the response you send to the client
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //check if there is any errors in the API call
        switch (response.statusCode()){
            case 200:
                break;
            case 401:
                throw new IllegalStateException("Invalid API key (401 Unauthorized)");
            case 400:
                throw new IllegalArgumentException("Invalid location parameter (400 Bad Request)");
            case 403:
                throw new IllegalStateException("API limit exceeded (403 Forbidden)");
            default:
                throw new RuntimeException("Unexpected HTTP status: " + response.statusCode());
        }

        //create Gson object to read json
        Gson gson = new Gson();
        WeatherResponse realWeather = gson.fromJson(response.body(), WeatherResponse.class);
        System.out.println("The weather in: " + realWeather.getLocation().getName() + " is " +
                realWeather.getCurrent().getTemp_f() + " it is also " +
                realWeather.getCurrent().getCondition().getText().toLowerCase());

    }

    void getForecast(String location, String days) throws  Exception{
        //build the request with all headers
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + location +"&days=" + days))
                .GET()
                .build();
        //initialize client
        HttpClient httpClient = HttpClient.newHttpClient();
        //store the response you send to the client
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //check if there is any errors in the API call
        switch (response.statusCode()){
            case 200:
                break;
            case 401:
                throw new IllegalStateException("Invalid API key (401 Unauthorized)");
            case 400:
                throw new IllegalArgumentException("Invalid location parameter (400 Bad Request)");
            case 403:
                throw new IllegalStateException("API limit exceeded (403 Forbidden)");
            default:
                throw new RuntimeException("Unexpected HTTP status: " + response.statusCode());
        }

        //create Gson object to read json
        Gson gson = new Gson();
        WeatherResponse realWeather = gson.fromJson(response.body(), WeatherResponse.class);
        ArrayList<WeatherResponse.ForecastDay> forecastDayArrayList = realWeather.getForecast().getForecastday();
        for(WeatherResponse.ForecastDay day : forecastDayArrayList){
            System.out.println(day.getDate() + " " + day.getDay().getAvgtemp_f());
        }

    }
}