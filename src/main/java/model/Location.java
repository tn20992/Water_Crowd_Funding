package model;
/*
 * Class for locations
 */

public class Location {
    private double longitude;
    private double latitude;

    /**
     * Creates a new location
     * @param longitude the longitude coordinate
     * @param latitude the latitude coordinate
     */
    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Getters + Setters for location fields
     */
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double newLongitude) {
        longitude = newLongitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double newLatitude) {
        longitude = newLatitude;
    }
}
