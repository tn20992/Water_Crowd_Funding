package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.ConditionOfWater;
import model.Facade;
import model.Location;
import model.TypeOfWater;
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
    private ComboBox<ConditionOfWater> waterConditionBox;

    private ObservableList<ConditionOfWater> condOfWatersList = FXCollections
            .observableArrayList(ConditionOfWater.values());

    private Facade facade = Facade.getFacade();
    private User user;

    private double longitude;
    private double latitude;
    private ConditionOfWater waterCondition;


    @FXML
    private void initialize() {

        waterConditionBox.setItems(condOfWatersList);
        waterConditionBox.getSelectionModel().select(ConditionOfWater.POTABLE);
    }

    /**
     * allow for calling back to the main application code if necessary
     * @param main the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
        user = mainApplication.getUser();
    }

    @FXML
    public void backSubmitReportPressed() {
        mainApplication.showMainPurityReportScreen();
    }

    @FXML
    public void submitSubmitReportPressed() {
        if (latitudeField.getText().equals("") || longtitudeField.getText()
                .equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(
                    "Longitude or Latitude cannot be empty!!!");
            alert.showAndWait();
        } else {
            try {
                longitude = Double.parseDouble(longtitudeField.getText());
                latitude = Double.parseDouble(latitudeField.getText());
                Location location = new Location(longitude, latitude);

                waterCondition = waterConditionBox.getValue();

                mainApplication.showMainPurityReportScreen();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText(
                        "Longitude or Latitude cannot include letters!!!");
                alert.showAndWait();
            }
        }
    }
}