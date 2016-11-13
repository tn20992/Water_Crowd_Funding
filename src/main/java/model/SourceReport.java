package model;

import java.sql.Timestamp;

/**
 * Class for SourceReports
 */
public class SourceReport {
    private int sourceReportNumber;
    private User reporter;
    private Timestamp created;
    private Location location;
    private TypeOfWater typeOfWater;
    private ConditionOfWater conditionOfWater;

    /**
     * Creates a new source report
     * @param sourceReportNumber unique identifier of a source report
     * @param reporter user who reports new source
     * @param created created timestamp
     * @param location location of the source
     * @param typeOfWater type of source water
     * @param conditionOfWater condition of source water
     */
    public SourceReport(int sourceReportNumber, User reporter,
        Timestamp created, Location location, TypeOfWater typeOfWater,
            ConditionOfWater conditionOfWater) {
        this.sourceReportNumber = sourceReportNumber;
        this.reporter = reporter;
        this.created = created;
        this.location = location;
        this.typeOfWater = typeOfWater;
        this.conditionOfWater = conditionOfWater;
    }
    /**
     * gets the source report number
     * @return the source report number
     */
    public int getSourceReportNumber() {
        return sourceReportNumber;
    }

    /**
     * gets the reporter
     * @return the reporter
     */
    public User getReporter() {
        return  reporter;
    }

    /**
     * gets the condition of water
     * @return the condition of water
     */
    public ConditionOfWater getConditionOfWater() {
        return conditionOfWater;
    }

    /**
     * gets the location
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * gets the time created
     * @return the time created
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * gets the type of water
     * @return the type of water
     */
    public TypeOfWater getTypeOfWater() {
        return typeOfWater;
    }

    /*
     * Set hash code equal to the sourceReportNumber.
     * @return the source report's hashcode
     */
    @Override
    public int hashCode() {
        return sourceReportNumber;
    }

    /**
     * sets the source report number
     * @param sourceReportNumber the source report number
     */
    public void setSourceReportNumber(int sourceReportNumber) {
        this.sourceReportNumber = sourceReportNumber;
    }

    /**
     * sets the reporter
     * @param reporter the reporter
     */
    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    /**
     * sets the condition of water
     * @param conditionOfWater the condition of water
     */
    public void setConditionOfWater(ConditionOfWater conditionOfWater) {
        this.conditionOfWater = conditionOfWater;
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
     * sets the type of water
     * @param typeOfWater the type of water
     */
    public void setTypeOfWater(TypeOfWater typeOfWater) {
        this.typeOfWater = typeOfWater;
    }

    /*
     * @return Report: source report number
     */
    @Override
    public String toString() {
        return ("Report: " + sourceReportNumber);
    }

    /**
     * @return name of the reporter of the water source
     */
    public String getReporterName() {
        return reporter.getName();
    }


}
