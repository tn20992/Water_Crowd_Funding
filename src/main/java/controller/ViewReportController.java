package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.ConditionOfWater;
import model.TypeOfWater;

import java.sql.Timestamp;


/**
 * Controller for view report screen
 */
public class ViewReportController {

    private MainFXApplication mainApplication;

    @FXML
    private Label reporterName;
    @FXML
    private Label timestamp;
    @FXML
    private Label longtitude;
    @FXML
    private Label latitude;
    @FXML
    private Label waterType;
    @FXML
    private Label waterCondition;
    @FXML
    private Label reportNumber;

    /**
     * allow for calling back to the main application code if necessary
     * @param main the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Back button press
     */
    @FXML
    public void backViewReportPressed() {
        mainApplication.showMainReportScreen();
    }

    /**
     * Set report number
     * @param number nunmber
     */
    public void setReportNumber(int number) {
        reportNumber.setText("" + number);
    }

    /**
     * Set report name
     * @param name name
     */
    public void setReporterName(String name) {
        reporterName.setText(name);
    }

    /**
     * Set timestamp
     * @param stamp timesatmp
     */
    public void setTimestamp(Timestamp stamp) {
        timestamp.setText("" + stamp);
    }

    /**
     * Set longitude
     * @param longP longitude
     */
    public void setLongitudes(double longP) {
        longtitude.setText("" + longP);
    }

    /**
     * Set latitude
     * @param lat latitude
     */
    public void setLatitude(double lat) {
        latitude.setText("" + lat);
    }

    /**
     * Set water type
     * @param type type
     */
    public void setWaterType(TypeOfWater type) {
        waterType.setText("" + type);
    }

    /**
     * Set water caondition
     * @param condition condition
     */
    public void setWaterCondition(ConditionOfWater condition) {
        waterCondition.setText("" + condition);
    }
}
