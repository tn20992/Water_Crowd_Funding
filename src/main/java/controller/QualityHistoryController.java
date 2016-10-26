package controller;
import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Facade;
import model.Location;
import model.Point;
import model.User;

import java.util.ArrayList;


/**
 * Controller for water quality history controller
 */
public class QualityHistoryController {

    private MainFXApplication mainApplication;
    private Facade facade = Facade.getFacade();
    private User user;
    private double longitude;
    private double latitude;
    private double year;
    private Boolean virus = false;
    private Boolean contaminant = false;

    @FXML
    private TextField longField;

    @FXML
    private TextField latField;

    @FXML
    private ComboBox<String> virusOrContaminant;

    @FXML
    private TextField yearField;

    private String[] virusOrContaminantList = {"VirusPPM","ContaminantPPM"};

    @FXML
    private void initialize() {
        virusOrContaminant.setItems(FXCollections
                .observableArrayList(virusOrContaminantList));

    }

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    public void btnViewPressed() {
        if (latField.getText().equals("") || longField.getText()
                .equals("") || yearField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(
                    "Longitude or Latitude or Year cannot be empty!!!");
            alert.showAndWait();
        } else {
            try {
                longitude = Double.parseDouble(longField.getText());
                latitude = Double.parseDouble(latField.getText());
                Location location = new Location(longitude, latitude);
                year = Double.parseDouble(yearField.getText());

                if(virusOrContaminant.getValue().equals("VirusPPM")) {
                    virus = true;
                } else {
                    contaminant = true;
                }

                ArrayList<Point> pointList = facade.getHistoryByLocation
                        (location, (int) year);

                mainApplication.showMainReportScreen();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText(
                        "Longitude or Latitude or Year cannot include letters!!!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnBackPressed() {
        mainApplication.initRootLayout(mainApplication.getMainScreen());
    }
}
