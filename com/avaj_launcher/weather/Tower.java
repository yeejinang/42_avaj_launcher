package com.avaj_launcher.weather;
import com.avaj_launcher.aircrafts.*;
import java.util.*;
import com.avaj_launcher.validator.*;

public class Tower {

    List<Flyable> observers;

    public Tower() {
        this.observers = new ArrayList<Flyable>();
    }

    public void register(Flyable p_flyable) {
        this.observers.add(p_flyable);
    }

    public void unregister(Flyable p_flyable) {
        this.observers.remove(p_flyable);
    }

    protected void conditionChanged() {
        List<Flyable> landedAircrafts = new ArrayList<>();
        for (Flyable observer : observers) {
            observer.updateConditions();
            if (observer.getCoordinates().getHeight() <= 0) {
                landedAircrafts.add(observer);
                String aircraftType;
                if (observer instanceof Helicopter) {
                    aircraftType = "Helicopter";
                }
                else if (observer instanceof JetPlane) {
                    aircraftType = "JetPlane";
                } else {
                    aircraftType = "Baloon";
                }
                String message = "Tower says: " + aircraftType + "#" + observer.getName() +"(" + observer.getId() + ") unregistered from weather tower.\n";
                CustomFileWriter.writeIntoFile("simulator.txt", message);
            }
        }
        for (Flyable landedAircraft : landedAircrafts) {
            unregister(landedAircraft);
        }
    }
}