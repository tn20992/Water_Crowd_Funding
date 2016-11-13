package model;

import java.sql.Timestamp;

/*
 * Class for points
 */

public class Point {
    private Timestamp time;
    private double virusPPM;
    private double contaminantPPM;

    /**
     * Creates a new point
     * @param time the time the data was recorded
     * @param virusPPM the virusPPM the point must describe
     * @param contaminantPPM the contaminantPPM point must describe
     */
    public Point(Timestamp time, double virusPPM, double contaminantPPM) {
        this.time = time;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * gets the time
     * @return time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * gets the virusPPM
     * @return virusPPM
     */
    public double getVirusPPM() {
        return virusPPM;
    }

    /**
     * gets the contaminantPPM
     * @return contaminantPPM
     */
    public double getContaminantPPM() {
        return contaminantPPM;
    }

    /**
     * sets the time
     * @param time new time
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * sets the virusPPM
     * @param virusPPM new virusPPM
     */
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * sets the contaminantPPM
     * @param contaminantPPM new contaminantPPM
     */
    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }
}
