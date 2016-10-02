package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.ConditionOfWater;
import model.TypeOfWater;

/**
 * Controller for main report screen
 *
 */
public class MainReportController {

    private MainFXApplication mainApplication;

    @FXML
    private ListView<String> listViewReport;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    public void mainReportCancelPressed() {
        mainApplication.initRootLayout(mainApplication.getMainScreen());
    }

    @FXML
    public void mainReportSubmitPressed() {
        mainApplication.showSubmitReportScreen();
    }

    @FXML
    public void mainReportViewPressed() {
        mainApplication.showViewReportScreen();
    }
}
