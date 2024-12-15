package com.avaj_launcher.aircrafts;

public class AircraftFactory {

    private static AircraftFactory aircraftFactory;

    private long id = 0;

    private AircraftFactory() { }

    public static AircraftFactory getInstance() {
        if (aircraftFactory == null) {
            aircraftFactory = new AircraftFactory();
        }
        return aircraftFactory;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        switch (p_type) {
            case "Helicopter":
                return new Helicopter(++id, p_name, p_coordinates);
            case "JetPlane":
                return new JetPlane(++id, p_name, p_coordinates);
            case "Baloon":
                return new Baloon(++id, p_name, p_coordinates);
            default:
                return null;
        }
    }
}