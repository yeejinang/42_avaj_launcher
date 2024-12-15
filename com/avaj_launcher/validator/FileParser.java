package com.avaj_launcher.validator;
import com.avaj_launcher.exception.*;
import com.avaj_launcher.model.*;
import java.io.*;
import java.util.*;

public class FileParser {

    FileParser() {}

    private static final List<String> aircraftList = Arrays.asList("Helicopter", "Baloon", "JetPlane");

    public static AircraftModel parseFile(String text) {
        String[] fileInput = text.split(" ");
        if (fileInput.length != 5) {
            throw new WrongInputException("Invalid Input! Please input according to this format: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
        }
        if (!aircraftList.contains(fileInput[0])) {
            throw new WrongInputException("Invalid aircraft type!");
        }
        if (!fileInput[2].chars().allMatch(Character::isDigit)
            || !fileInput[3].chars().allMatch(Character::isDigit)
            || !fileInput[4].chars().allMatch(Character::isDigit)) {
            throw new WrongInputException("Invalid longitude, latitude or height! Longitude, latitude or height must be in digit");
        }
        int longitude, latitude, height;
        try {
            longitude = Integer.valueOf(fileInput[2]);
            latitude = Integer.valueOf(fileInput[3]);
            height = Integer.valueOf(fileInput[4]);
        } catch(NumberFormatException ex) {
            throw new WrongInputException("Wrong Input!");
        }
        if (height > 100 || height < 0 || longitude < 0 || latitude < 0) {
            throw new OutOfRangeException("Input value is out of range!");
        }
        return new AircraftModel(fileInput[0], fileInput[1], longitude, latitude, height);
    }
}