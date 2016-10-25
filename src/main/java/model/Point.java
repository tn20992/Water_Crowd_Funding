package model;

import java.sql.Timestamp;


public class Point {
    private Timestamp time;
    private double virusPPM;
    private double contaminantPPM;

    public Point (Timestamp time, double virusPPM, double contaminantPPM) {
        this.time = time;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    /*
     * Getters + Setters for point fields
     */
    public Timestamp getTime() {
        return time;
    }
    public double getVirusPPM() {
        return virusPPM;
    }
    public double getContaminantPPM() {
        return contaminantPPM;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }
    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }
}
