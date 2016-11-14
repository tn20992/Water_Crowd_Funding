package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.OverallCondition;
import java.sql.Timestamp;


/**
 * Controller for view report screen
 */
public class ViewPurityReportController {

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
    private Label virusPPM;
    @FXML
    private Label contaminantPPM;
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
        mainApplication.showMainPurityReportScreen();
    }

    /**
     * Set report number
     * @param number number
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
     * @param stamp timestamp
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
     * Set VirusPPM
     * @param virus virusPPM
     */
    public void setVirusPPM(double virus) {
        virusPPM.setText("" + virus);
    }

    /**
     * Set contaminant
     * @param contaminant contaminant
     */
    public void setContaminantPPM(double contaminant) {
        contaminantPPM.setText("" + contaminant);
    }

    /**
     * Set Condition
     * @param condition overal condition
     */
    public void setOverallCondition(OverallCondition condition) {
        waterCondition.setText("" + condition);
    }
}
