package com.avaj_launcher.aircrafts;
import com.avaj_launcher.validator.*;
import java.util.*;

public class JetPlane extends Aircraft {

    JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    private static final Map<String, Coordinates> JET_PLANE_FLY_MODE = new HashMap<String, Coordinates>(){{
        put("SUN", new Coordinates(0, 10, 2));
        put("RAIN", new Coordinates(0, 5, 0));
        put("FOG", new Coordinates(0, 1, 0));
        put("SNOW", new Coordinates(0, 0, -7));
    }};

    private static final Map<String, String> JETPLANE_WEATHER = new HashMap<String, String>(){{
        put("SUN", "The sun is out and shining bright – it's practically showing off at this point.");
        put("RAIN", "The rain is really coming down – I just saw a duck with an umbrella!");
        put("FOG", "The fog is so thick, I need a compass just to find my front door.");
        put("SNOW", "The weather outside is frightful, but my hot chocolate is so delightful!");
    }};

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        if (JET_PLANE_FLY_MODE.containsKey(weather)) {
            System.out.println("[JetPlane Coordinate] Before: " + coordinates.toString());
            coordinates.updateCoordinate(JET_PLANE_FLY_MODE.get(weather));
            System.out.println("[JetPlane Coordinate] After: " + coordinates.toString());
        }
        CustomFileWriter.writeIntoFile("simulator.txt", "JetPlane#" + this.name + "(" + this.id + ") : " + JETPLANE_WEATHER.get(weather) + "\n");
        if (coordinates.getHeight() <= 0) {
            CustomFileWriter.writeIntoFile("simulator.txt", "JetPlane#" + this.name + "(" + this.id + ") : Landing...\n");
        }
    }
}