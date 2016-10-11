package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Facade;
import model.User;
import model.TypeOfWater;
import model.ConditionOfWater;
import model.Location;


/**
 * Controller for submit report screen
 */
public class SubmitReportController {

    private MainFXApplication mainApplication;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private ComboBox<TypeOfWater> waterTypeBox;

    @FXML
    private ComboBox<ConditionOfWater> waterConditionBox;


    private ObservableList<TypeOfWater> typeOfWatersList = FXCollections
            .observableArrayList(TypeOfWater.values());

    private ObservableList<ConditionOfWater> condOfWatersList = FXCollections
            .observableArrayList(ConditionOfWater.values());

    private Facade facade = Facade.getFacade();
    private User user;

    private double longitude;
    private double latitude;
    private TypeOfWater waterType;
    private ConditionOfWater waterCondition;


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
        user = mainApplication.getUser();
    }

    @FXML
    public void backSubmitReportPressed() {
        mainApplication.showMainReportScreen();
    }

    @FXML
    public void submitSubmitReportPressed() {
        if (latitudeField.getText() == null
                || longitudeField.getText() == null
                || latitudeField.getText().trim().isEmpty()
                || longitudeField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(
                    "Longitude or latitude cannot be null!!!");
            alert.showAndWait();
        } else {
            longitude = Double.parseDouble(longitudeField.getText());
            latitude = Double.parseDouble(latitudeField.getText());

            Location location = new Location(longitude, latitude);
            waterType = waterTypeBox.getValue();
            waterCondition = waterConditionBox.getValue();
            facade.createSourceReport(
                    user.getName(), location, waterType, waterCondition);
            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.setTitle("Success!");
            confirm.setHeaderText("Success!");
            confirm.setContentText("Water report successfully created!");
            confirm.showAndWait();
            mainApplication.showMainReportScreen();
        }
    }
}
