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

    /*
     * Set equals to compare locations.
     * @param obj the location instance
     * @return whether the object is a location and their coordinates matches
     */
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Location loc = (Location) obj;
        return this.longitude == loc.longitude && this.latitude == loc.latitude;
    }

}
