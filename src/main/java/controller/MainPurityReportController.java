package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Facade;
import model.PurityReport;

/**
 * Controller for main purity report screen
 *
 */
public class MainPurityReportController {

    private MainFXApplication mainApplication;

    @FXML
    private ListView<PurityReport> listViewPurityReport;

    private Facade facade = Facade.getFacade();

    private ObservableList<PurityReport> reportList = FXCollections
            .observableArrayList(facade.getPurityReports());

    @FXML
    private void initialize() {
        listViewPurityReport.setItems(reportList);
    }

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    public void mainPurityReportCancelPressed() {
        mainApplication.initRootLayout(mainApplication.getMainScreen());
    }

    @FXML
    public void mainPurityReportSubmitPressed() {
        mainApplication.showSubmitPurityReportScreen();
    }

    @FXML
    public void mainPurityReportViewPressed() {
        PurityReport sourceReport
                = listViewPurityReport.getSelectionModel().getSelectedItem();
        if (sourceReport == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(
                    "Please select the report you want to view!!!");
            alert.showAndWait();
        } else {
            mainApplication.showViewPurityReportScreen(sourceReport);
        }
    }
}