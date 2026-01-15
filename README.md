# Java Weather CLI

A simple Java command-line application that fetches real-time weather data
using the WeatherAPI.

## Features
- Fetch current weather by location
- Uses Java HttpClient
- Parses JSON with Gson
- Secure API key handling via environment variables

## Setup
Set the environment variable:

WEATHER_API_KEY=your_api_key_here

## Run
java WeatherAPICall <city_or_zip>
