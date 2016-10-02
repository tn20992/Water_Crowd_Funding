package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;

/**
 * Controller for main report screen
 *
 */
public class MainReportController {

    private MainFXApplication mainApplication;

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
