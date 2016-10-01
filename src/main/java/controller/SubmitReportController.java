package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.ConditionOfWater;
import model.TypeOfWater;

/**
 * Controller for submit report screen
 */
public class SubmitReportController {

    private MainFXApplication mainApplication;

    @FXML
    private TextField nameOfReporterField;

    @FXML
    private TextField longtitudeFiled;

    @FXML
    private TextField latitudeField;

    @FXML
    private ComboBox<TypeOfWater> waterTypeBox;

    @FXML
    private ComboBox<ConditionOfWater> waterConditionBox;

    @FXML
    private TextField commentBox;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    public void backSubmitReportPressed() {
        mainApplication.showMainReportScreen();
    }

    @FXML
    public void submitSubmitReportPressed() {
        mainApplication.showMainReportScreen();
    }
}
