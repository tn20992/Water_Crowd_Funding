package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



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

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    public void backViewReportPressed() {
        mainApplication.showMainReportScreen();
    }

}
