package org.example;

import java.util.ArrayList;

//class that handles the JSON headers in order to extract the info
public class WeatherResponse {
    //field names for the fields we are parsing using gson
    private Location location;
    private Current current;
    private Forecast forecast;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    //inner classes for Location header with fields used
    public static class Location{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    //Current Class that has fields that are used
    public static class Current{
        //has another inner Condition class as it is another header
        private double temp_f;
        private Condition condition;

        public double getTemp_f() {
            return temp_f;
        }

        public void setTemp_f(double temp_f) {
            this.temp_f = temp_f;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }
    }

    //Condition class that shows if cloudy or sunny ect.
    public static class Condition{
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    //used for parsing the Forecast of days
    public static class Forecast {
        //Each forecast has a forecast day so place it in an arraylist
        ArrayList<ForecastDay> forecastday;

        public ArrayList<ForecastDay> getForecastday() {
            return forecastday;
        }

        public void setForecastday(ArrayList<ForecastDay> forecastday) {
            this.forecastday = forecastday;
        }
    }

    //actual forecast day with the info from it
    public static class ForecastDay {
        private String date;
        //the day is another inner header
        private Day day;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Day getDay() {
            return day;
        }

        public void setDay(Day day) {
            this.day = day;
        }
    }

    //the actual day in the forecast with its info
    public static class Day{
        private double maxtemp_f;
        private double mintemp_f;
        private double avgtemp_f;

        public double getMaxtemp_f() {
            return maxtemp_f;
        }

        public void setMaxtemp_f(double maxtemp_f) {
            this.maxtemp_f = maxtemp_f;
        }

        public double getMintemp_f() {
            return mintemp_f;
        }

        public void setMintemp_f(double mintemp_f) {
            this.mintemp_f = mintemp_f;
        }

        public double getAvgtemp_f() {
            return avgtemp_f;
        }

        public void setAvgtemp_f(double avgtemp_f) {
            this.avgtemp_f = avgtemp_f;
        }
    }
}

