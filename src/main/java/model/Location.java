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
     * gets the longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * sets the longitude
     * @param newLongitude new longitude
     */
    public void setLongitude(double newLongitude) {
        longitude = newLongitude;
    }

    /**
     * gets the latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * sets the latitude
     * @param newLatitude new latitude
     */
    public void setLatitude(double newLatitude) {
        longitude = newLatitude;
    }

    /**
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

    @Override
    public int hashCode() {
        return 1;
    }

}
