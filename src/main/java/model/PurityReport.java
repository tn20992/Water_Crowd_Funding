package model;

import java.sql.Timestamp;

/**
 * Class for purityReports
 */
public class PurityReport {
    private int purityReportNumber;
    private User reporter;
    private Timestamp created;
    private Location location;
    private OverallCondition overallCondition;
    private double virusPPM;
    private double contaminantPPM;

    /**
     * Creates a new purity report
     *
     * @param purityReportNumber unique identifier of a purity report
     * @param reporter           user who reports new purity
     * @param created            created timestamp
     * @param location           location of the purity
     * @param overallCondition   overall condition
     * @param virusPPM           measurement of virus in parts per million
     * @param contaminantPPM     measurement of contaminant in parts per million
     */
    public PurityReport(int purityReportNumber, User reporter,
                        Timestamp created, Location location,
                        OverallCondition overallCondition, double virusPPM,
                        double contaminantPPM) {
        this.purityReportNumber = purityReportNumber;
        this.reporter = reporter;
        this.created = created;
        this.location = location;
        this.overallCondition = overallCondition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * Getters + Setters for report fields
     */
    public int getPurityReportNumber() {
        return purityReportNumber;
    }
    public User getReporter() {
        return reporter;
    }
    public Timestamp getCreated() {
        return created;
    }
    public Location getLocation() {
        return location;
    }
    public OverallCondition getOverallCondition() {
        return overallCondition;
    }
    public double getVirusPPM() {
        return virusPPM;
    }
    public double getContaminantPPM() {
        return contaminantPPM;
    }
    public void setPurityReportNumber(int purityReportNumber) {
        this.purityReportNumber = purityReportNumber;
    }
    public void setReporter(User reporter) {
        this.reporter = reporter;
    }
    public void setCreated(Timestamp created) {
        this.created = created;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setOverallCondition(OverallCondition overallCondition) {
        this.overallCondition = overallCondition;
    }
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }
    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * @return name of the reporter
     */
    public String getReporterName() {
        return reporter.getName();
    }

    /*
     * Set hash code equal to the purityReportNumber.
     * @return the purity report's hashcode
     */
    @Override
    public int hashCode() {
        return purityReportNumber;
    }

    /*
     * @return Report: purity report number
     */
    @Override
    public String toString() {
        return ("Report: " + purityReportNumber);
    }
}