package com.avaj_launcher.aircrafts;
import java.util.*;
import com.avaj_launcher.validator.*;

public class Helicopter extends Aircraft {

    Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    private static final Map<String, Coordinates> HELICOPTER_FLY_MODE = new HashMap<String, Coordinates>(){{
        put("SUN", new Coordinates(10, 0, 2));
        put("RAIN", new Coordinates(5, 0, 0));
        put("FOG", new Coordinates(1, 0, 0));
        put("SNOW", new Coordinates(0, 0, 12));
    }};

    private static final Map<String, String> HELICOPTER_WEATHER = new HashMap<String, String>(){{
        put("SUN", "It's so sunny out, even the sun needs sunglasses!");
        put("RAIN", "It's raining cats and dogs out there, and I just stepped in a poodle!");
        put("FOG", "The fog is so thick, I just saw a bird trying to use Google Maps!");
        put("SNOW", "It's snowing so hard that even the snowmen are considering retirement!");
    }};

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);

        // System.out.println("[Helicopter Coordinate] Before: " + coordinates.toString());
        if (HELICOPTER_FLY_MODE.containsKey(weather)) {
            coordinates.updateCoordinate(HELICOPTER_FLY_MODE.get(weather));
        }
        // System.out.println("[Helicopter Coordinate] After: " + coordinates.toString());

        CustomFileWriter.writeIntoFile("simulator.txt", "Helicopter#" + this.name + "(" + this.id + ") : " + HELICOPTER_WEATHER.get(weather) + "\n");
        if (coordinates.getHeight() == 0) {
            CustomFileWriter.writeIntoFile("simulator.txt", "Helicopter#" + this.name + "(" + this.id + ") : Landing...\n");
        }
    }
}