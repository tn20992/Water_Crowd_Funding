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
     * gets the purity report number
     * @return the purity report number
     */
    public int getPurityReportNumber() {
        return purityReportNumber;
    }

    /**
     * gets the reporter
     * @return the reporter
     */
    public User getReporter() {
        return reporter;
    }

    /**
     * gets the time created
     * @return the time created
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * gets the location
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * gets the overall condition
     * @return the overall condition
     */
    public OverallCondition getOverallCondition() {
        return overallCondition;
    }

    /**
     * gets the virusPPM
     * @return the virusPPM
     */
    public double getVirusPPM() {
        return virusPPM;
    }

    /**
     * gets the contaminantPPM
     * @return the contaminantPPM
     */
    public double getContaminantPPM() {
        return contaminantPPM;
    }

    /**
     * sets the purity report number
     * @param purityReportNumber the purity report number
     */
    public void setPurityReportNumber(int purityReportNumber) {
        this.purityReportNumber = purityReportNumber;
    }

    /**
     * sets the reporter
     * @param reporter the reporter
     */
    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    /**
     * sets the time created
     * @param created the time created
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * sets the location
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * sets the overall condition
     * @param overallCondition the overall condition
     */
    public void setOverallCondition(OverallCondition overallCondition) {
        this.overallCondition = overallCondition;
    }

    /**
     * sets the virusPPM
     * @param virusPPM the virusPPM
     */
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * sets the contaminantPPM
     * @param contaminantPPM the contaminantPPM
     */
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