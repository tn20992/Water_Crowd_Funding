package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Facade;
import model.SourceReport;

import javax.xml.transform.Source;
import java.util.ArrayList;

/**
 * Controller for main report screen
 *
 */
public class MainReportController {

    private MainFXApplication mainApplication;

    @FXML
    private ListView<SourceReport> viewList;

    private Facade facade = Facade.getFacade();

    private ArrayList<SourceReport> list = facade.getSourceReports();

    private ObservableList<SourceReport> obsList = FXCollections
            .observableArrayList(list);
    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    public void initialize() {
        viewList.setItems(obsList);
        viewList.getSelectionModel().select(0);
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
        SourceReport reportSelected = viewList.getSelectionModel().getSelectedItem();
        mainApplication.showViewReportScreen(reportSelected);
    }
}
