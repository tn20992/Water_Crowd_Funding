package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Facade;
import model.SourceReport;

/**
 * Controller for main report screen
 *
 */
public class MainReportController {

    private MainFXApplication mainApplication;

    @FXML
    private ListView<SourceReport> listViewReport;

    private Facade facade = Facade.getFacade();

    private ObservableList<SourceReport> reportList = FXCollections
            .observableArrayList(facade.getSourceReports());

    @FXML
    private void initialize() {
        listViewReport.setItems(reportList);
    }
    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Cancel press
     */
    @FXML
    public void mainReportCancelPressed() {
        mainApplication.initRootLayout(mainApplication.getMainScreen());
    }

    /**
     * Submit press
     */
    @FXML
    public void mainReportSubmitPressed() {
        mainApplication.showSubmitReportScreen();
    }

    /**
     * View press
     */
    @FXML
    public void mainReportViewPressed() {
        SourceReport sourceReport
            = listViewReport.getSelectionModel().getSelectedItem();
        if (sourceReport == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(
                "Please select the report you want to view!!!");
            alert.showAndWait();
        } else {
            mainApplication.showViewReportScreen(sourceReport);
        }
    }
}