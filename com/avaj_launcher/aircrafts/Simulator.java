package com.avaj_launcher.aircrafts;
import com.avaj_launcher.validator.*;
import com.avaj_launcher.exception.*;
import com.avaj_launcher.model.*;
import com.avaj_launcher.weather.*;
import java.io.*;
import java.util.*;

public class Simulator {

    public static void main(String args[]) {

        try {
            if (args.length != 1) {
                throw new WrongInputException("Invalid argument!");
            }

            File file = new File(args[0]);
            String expectedExtension = ".txt";

            if (!file.exists()) {
                throw new WrongInputException("File does not exist!");
            }

            if (!isValidExtension(file, expectedExtension)) {
                throw new WrongInputException("Invalid file extension!");
            }

            int weatherChangedCount = 0;
            List<AircraftModel> pendingToCreateAircraft = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));) {
                String line = reader.readLine();
                
                try {
                    weatherChangedCount = Integer.valueOf(line);
                } catch(NumberFormatException ex) {
                    throw new WrongInputException("Invalid value!");
                }
                if (weatherChangedCount < 0) {
                    throw new OutOfRangeException("Number of times the simulation run must be a positive number!");
                }
                line = reader.readLine();

                while (line != null) {
                    System.out.println(line);
                    AircraftModel aircraft = FileParser.parseFile(line);
                    pendingToCreateAircraft.add(aircraft);
                    line = reader.readLine();
                }
                reader.close();

                AircraftFactory aircraftFactory = AircraftFactory.getInstance();
                WeatherTower weatherTower = new WeatherTower();

                for (AircraftModel aircraft : pendingToCreateAircraft) {
                    Flyable createdAircraft = aircraftFactory.newAircraft(aircraft.getType(), aircraft.getName(), new Coordinates(aircraft.getLongitude(), aircraft.getLatitude(), aircraft.getHeight()));
                    createdAircraft.registerTower(weatherTower);
                    weatherTower.register(createdAircraft);
                    CustomFileWriter.writeIntoFile("simulator.txt", "Tower says: " + aircraft.getType() + "#" + createdAircraft.getName() + "(" + createdAircraft.getId() + ") registered to weather tower.\n");
                }

                for (int i = 0; i < weatherChangedCount; i++) {
                    CustomFileWriter.writeIntoFile("simulator.txt", "-------------- Simulator #" + i + " -------------- \n");
                    weatherTower.changedWeather();
                }
            }
            catch (Exception ex) {
                System.err.println("error: " + ex.getMessage());
            }
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
        }
    }

    public static boolean isValidExtension(File file, String expectedExtension) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(expectedExtension.toLowerCase());
    }
}
