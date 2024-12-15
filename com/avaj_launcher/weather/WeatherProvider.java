package com.avaj_launcher.weather;
import java.util.*;
import com.avaj_launcher.aircrafts.*;

public class WeatherProvider {

    private static WeatherProvider weatherProvider;

    private List<String> weatherList = Arrays.asList("SUN", "RAIN", "FOG", "SNOW");

    private static final int MAX_LONGITUDE = 360;

    private static final int MAX_LATITUDE = 180;

    private static final int MAX_HEIGHT = 100;

    private WeatherProvider() { }

    public static WeatherProvider getInstance() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        List<String> currentWeatherList = new ArrayList<>();
        currentWeatherList.addAll(weatherList);
        if (p_coordinates.getHeight() < MAX_HEIGHT / 2) {
            Collections.reverse(currentWeatherList);
        }
        if (p_coordinates.getLongitude() < MAX_LONGITUDE / 2 && p_coordinates.getLatitude() < MAX_LATITUDE / 2) {
            return currentWeatherList.get(0);
        } else if (p_coordinates.getLongitude() > MAX_LONGITUDE / 2 && p_coordinates.getLatitude() > MAX_LATITUDE / 2) {
            return currentWeatherList.get(1);
        } else if (p_coordinates.getLongitude() < MAX_LONGITUDE / 2 && p_coordinates.getLatitude() > MAX_LATITUDE / 2) {
            return currentWeatherList.get(2);
        } else {
            return currentWeatherList.get(3);
        }
    }

}
