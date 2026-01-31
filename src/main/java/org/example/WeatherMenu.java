package org.example;

import java.util.Scanner;

public class WeatherMenu {
    public static void main(String[] args) {
        WeatherAPICall apiCall = new WeatherAPICall();
        Scanner scanner = new Scanner(System.in);
        String location;
        String numberOfDays;
        while (true){
            System.out.println("======= Weather Checker Menu =======");
            System.out.println("1. Show current weather");
            System.out.println("2. Show forecast weather");
            System.out.println("3. Terminate Program");
            System.out.println("Please choose one of the above options (eg. 1)");
            switch (scanner.next()){
                case "1":
                    System.out.println("Enter the location you want to check the weather for (eg. Dallas or 75220)");
                    location = scanner.next();
                    //try catch blocks for the actual api call
                    try {
                        apiCall.getCurrentWeather(location);
                    }
                    catch (IllegalArgumentException | IllegalStateException e){
                        System.out.println("Error: " + e.getMessage() + " please try again....");
                    }
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Enter the location you want to check the weather for (eg. Dallas or 75220)");
                    location = scanner.next();
                    System.out.println("Enter the amount of days you want to see the forecast for: ");
                    numberOfDays = scanner.next();

                    //try catch blocks for the actual api call
                    try {
                        apiCall.getForecast(location, numberOfDays);
                    }
                    catch (IllegalArgumentException | IllegalStateException e){
                        System.out.println("Error: " + e.getMessage() + " please try again....");
                    }
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Thanks for using weather app CLI");
                    System.out.println("Goodbye...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unexpected choice please select a correct one");
                    break;

            }

        }
    }
}
