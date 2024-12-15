package com.avaj_launcher.aircrafts;
import com.avaj_launcher.weather.*;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    // Flyable(WeatherTower weatherTower) {
    //     this.weatherTower = weatherTower;
    // }

    public abstract void updateConditions();

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        System.out.println("Flyable:  registered to weather tower.");
    }

    public abstract long getId();

    public abstract String getName();

    public abstract Coordinates getCoordinates();
}