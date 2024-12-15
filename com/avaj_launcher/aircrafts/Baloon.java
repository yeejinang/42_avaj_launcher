package com.avaj_launcher.aircrafts;
import java.util.*;
import com.avaj_launcher.validator.*;

public class Baloon extends Aircraft {

    Baloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    private static final Map<String, Coordinates> BALOON_FLY_MODE = new HashMap<String, Coordinates>(){{
        put("SUN", new Coordinates(2, 0, 4));
        put("RAIN", new Coordinates(0, 0, -5));
        put("FOG", new Coordinates(0, 0, -3));
        put("SNOW", new Coordinates(0, 0, -15));
    }};

    private static final Map<String, String> BALOON_WEATHER = new HashMap<String, String>(){{
        put("SUN", "It's so sunny that my shadow just asked for sunscreen");
        put("RAIN", "It's raining so hard, I saw fish swimming down the street.");
        put("FOG", "Flying in this fog is like playing hide-and-seek with the road!");
        put("SNOW", "It's snowing so hard, even my snow globe is jealous!");
    }};


    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        // System.out.println("[Baloon Coordinate] Before: " + coordinates.toString());
        if (BALOON_FLY_MODE.containsKey(weather)) {
            coordinates.updateCoordinate(BALOON_FLY_MODE.get(weather));
        }
        // System.out.println("[Baloon Coordinate] After: " + coordinates.toString());

        CustomFileWriter.writeIntoFile("simulator.txt", "Baloon#" + this.name +"(" + this.id + ") : " + BALOON_WEATHER.get(weather) + "\n");
        if (coordinates.getHeight() == 0) {
            CustomFileWriter.writeIntoFile("simulator.txt", "Baloon#" + this.name +"(" + this.id + ") : Landing...\n");
        }
    }
}