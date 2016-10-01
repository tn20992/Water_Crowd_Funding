package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.AccountType;
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

    private ObservableList<TypeOfWater> typeOfWatersList = FXCollections
            .observableArrayList(TypeOfWater.values());

    private ObservableList<ConditionOfWater> condOfWatersList = FXCollections
            .observableArrayList(ConditionOfWater.values());

    @FXML
    private void initialize() {
        waterTypeBox.setItems(typeOfWatersList);
        waterTypeBox.getSelectionModel().select(TypeOfWater.BOTTLED);

        waterConditionBox.setItems(condOfWatersList);
        waterConditionBox.getSelectionModel().select(ConditionOfWater.POTABLE);
    }

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
