package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Facade;
import model.Location;
import model.OverallCondition;
import model.User;

/**
 * Controller for submit report screen
 */
public class SubmitPurityReportController {

    private MainFXApplication mainApplication;

    @FXML
    private TextField longtitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField virusPPMField;

    @FXML
    private TextField contaminantPPMField;

    @FXML
    private ComboBox<OverallCondition> waterConditionBox;

    private ObservableList<OverallCondition> condOfWatersList = FXCollections
            .observableArrayList(OverallCondition.values());

    private Facade facade = Facade.getFacade();
    private User user;

    private double longitude;
    private double latitude;
    private double virusPPM;
    private double contaminantPPM;
    private OverallCondition overallCondition;


    @FXML
    private void initialize() {

        waterConditionBox.setItems(condOfWatersList);
        waterConditionBox.getSelectionModel().select(OverallCondition.SAFE);
    }

    /**
     * allow for calling back to the main application code if necessary
     * @param main the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
        user = mainApplication.getUser();
    }

    /**
     * Back button press
     */
    @FXML
    public void backSubmitReportPressed() {
        mainApplication.showMainPurityReportScreen();
    }

    /**
     * Submit button press
     */
    @FXML
    public void submitSubmitReportPressed() {
        if (latitudeField.getText().equals("") || longtitudeField.getText()
                .equals("") || virusPPMField.getText().equals("")
                || contaminantPPMField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(
                    "You cannot leave any field empty!!!");
            alert.showAndWait();
        } else {
            try {
                longitude = Double.parseDouble(longtitudeField.getText());
                latitude = Double.parseDouble(latitudeField.getText());
                Location location = new Location(longitude, latitude);
                virusPPM = Double.parseDouble(virusPPMField.getText());
                contaminantPPM = Double.parseDouble(contaminantPPMField
                        .getText());

                overallCondition = waterConditionBox.getValue();
                facade.createPurityReport(user.getUsername(), location,
                        overallCondition, virusPPM, contaminantPPM);
                mainApplication.showMainPurityReportScreen();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText(
                        "You cannot include letters in any field!!!\nPlease "
                                + "check it and try again!");
                alert.showAndWait();
            }
        }
    }
}