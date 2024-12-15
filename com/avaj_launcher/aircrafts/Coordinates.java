package com.avaj_launcher.aircrafts;

public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    protected Coordinates(int p_longitude, int p_latitude, int p_height) {
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        this.height = p_height;
    }

    public int getLongitude() {
        return this.longitude;
    }
    
    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }
    
    public void updateCoordinate(Coordinates coordinatesChanged) {
        this.latitude += coordinatesChanged.getLatitude();
        this.longitude += coordinatesChanged.getLongitude();
        this.height += coordinatesChanged.getHeight();
        if (this.height > 100) {
            this.height = 100;
        } else if (this.height < 0) {
            this.height = 0;
        }
    }

    public String toString() {
        return "Longitude: " + longitude + ", Latitude: " + latitude + ", Height: " + height;
    }
}