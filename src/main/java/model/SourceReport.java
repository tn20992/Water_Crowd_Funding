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
     * @param location location of the source
     * @param typeOfWater type of source water
     * @param conditionOfWater condition of source water
     */
    public SourceReport(int sourceReportNumber, User reporter, Location location, Timestamp created,
                            TypeOfWater typeOfWater, ConditionOfWater conditionOfWater) {
        this.sourceReportNumber = sourceReportNumber;
        this.reporter = reporter;
        this.created = created;
        this.location = location;
        this.typeOfWater = typeOfWater;
        this.conditionOfWater = conditionOfWater;
    }
    /**
     * Getters + Setters for location fields
     */
    public int getSourceReportNumber() { return sourceReportNumber; }
    public User getReporter() { return  reporter; }
    public ConditionOfWater getConditionOfWater() { return conditionOfWater; }
    public Location getLocation() { return location; }
    public Timestamp getCreated() { return created; }
    public TypeOfWater getTypeOfWater() { return typeOfWater; }

    public void setSourceReportNumber(int sourceReportNumber) { this.sourceReportNumber = sourceReportNumber; }
    public void setReporter(User reporter) { this.reporter = reporter; }
    public void setConditionOfWater(ConditionOfWater conditionOfWater) { this.conditionOfWater = conditionOfWater; }
    public void setCreated(Timestamp created) { this.created = created; }
    public void setLocation(Location location) { this.location = location; }
    public void setTypeOfWater(TypeOfWater typeOfWater) { this.typeOfWater = typeOfWater; }

    public String toString() { return ("Report: " + sourceReportNumber); }


    /**
     * Returns name of the reporter of the water source
     */
    public String getReporterName() {
        return reporter.getName();
    }
}
