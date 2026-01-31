package org.example;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;

public class WeatherAPICall {

    private final String key = "9217383922534988a22222822261501";
    private HttpRequest httpRequest;
    private HttpClient httpClient;



    //methods

    void getCurrentWeather(String location){
        //build the request with all headers inside try-catch block
        try {
            httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://api.weatherapi.com/v1/current.json?key=" + key + "&q=" + location))
                    .GET()
                    .build();
            //initialize client
            httpClient = HttpClient.newHttpClient();
            //store the response you send to the client
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            //check if there is any errors in the API call
            switch (response.statusCode()) {
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
                    realWeather.getCurrent().getTemp_f() + " the current condition is " +
                    realWeather.getCurrent().getCondition().getText().toLowerCase());

        } catch (java.net.URISyntaxException | java.io.IOException | java.lang.InterruptedException e){
            throw new RuntimeException("Failed to fetch forecast", e);
        }

    }

    void getForecast(String location, String days){
        try {
            httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + location + "&days=" + days))
                    .GET()
                    .build();
            //initialize client
            httpClient = HttpClient.newHttpClient();
            //store the response you send to the client
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            //check if there is any errors in the API call
            switch (response.statusCode()) {
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

            //make an arrayList of all the days in the forecast
            List<WeatherResponse.ForecastDay> forecastDayArrayList = realWeather.getForecast().getForecastday();

            //formatter for the date to be displayed
            DateTimeFormatter readable = DateTimeFormatter.ofPattern("MMMM d, yyyy");

            System.out.printf("=================== Forecast for %s for %s days ===================\n", realWeather.getLocation().getName(), days);
            System.out.printf("%-18s %-19s %-15s %-15s\n", "Date", "Average Temperature", "Max Temperature", "Min Temperature");

            //print the days formatted
            for (WeatherResponse.ForecastDay day : forecastDayArrayList) {
                LocalDate date = LocalDate.parse(day.getDate());
                WeatherResponse.Day current = day.getDay();
                System.out.printf("%-18s %-19.2f %-15.2f %-15.2f\n", date.format(readable), current.getAvgtemp_f(),
                        current.getMaxtemp_f(), current.getMintemp_f());
            }
        } catch (java.net.URISyntaxException | java.io.IOException | java.lang.InterruptedException e){
            throw new RuntimeException("Failed to fetch forecast", e);
        }
    }

}