package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    public void backViewReportPressed() {
        mainApplication.showMainReportScreen();
    }

    public void setReportNumber(int number) {
        reportNumber.setText("" + number);
    }

    public void setReporterName(String name) {
        reporterName.setText(name);
    }

    public void setTimestamp(Timestamp stamp) {
        timestamp.setText("" + stamp);
    }

    public void setLongitudes(double longP) {
        longtitude.setText("" + longP);
    }

    public void setLatitude(double lat) {
        latitude.setText("" + lat);
    }

    public void setWaterType(TypeOfWater type) {
        waterType.setText("" + type);
    }

    public void setWaterCondition(ConditionOfWater condition) {
        waterCondition.setText("" + condition);
    }
}
