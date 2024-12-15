package com.avaj_launcher.weather;
import com.avaj_launcher.aircrafts.Coordinates;

public class WeatherTower extends Tower {

    public WeatherTower() {
        super();
    }

    public String getWeather(Coordinates p_coordinates) {
        WeatherProvider weatherProvider = WeatherProvider.getInstance();
        return weatherProvider.getCurrentWeather(p_coordinates);
    }

    public void changedWeather() {
        super.conditionChanged();
    }
}