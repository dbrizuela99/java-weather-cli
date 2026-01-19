package org.example;

public class WeatherMenu {
    public static void main(String[] args) {
        WeatherAPICall apiCall = new WeatherAPICall();
        try{
        apiCall.getForecast("Cancun","2");
        apiCall.getCurrentWeather("Cancun");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
