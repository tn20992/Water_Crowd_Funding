package controller;
import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Facade;
import model.Location;
import model.Point;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Controller for water quality history controller
 */
public class QualityHistoryController {

    private MainFXApplication mainApplication;
    private Facade facade = Facade.getFacade();
    private double longitude;
    private double latitude;
    private int year;
    private String vOrC = "";

    @FXML
    private TextField longField;

    @FXML
    private TextField latField;

    @FXML
    private ComboBox<String> virusOrContaminant;

    @FXML
    private TextField yearField;

    @FXML
    private ScatterChart chartHistorical;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private String[] virusOrContaminantList = {"VirusPPM", "ContaminantPPM"};

    @FXML
    private void initialize() {
        virusOrContaminant.setItems(FXCollections
                .observableArrayList(virusOrContaminantList));
        virusOrContaminant.getSelectionModel().select("VirusPPM");
    }

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    @SuppressWarnings("unchecked")
    public void initGraph(Location location, Integer year, String vOrC
            , ArrayList<Point> points) {
        chartHistorical.getData().clear();
        chartHistorical.setTitle("Historical Chart of " + vOrC + " in "
                + String.valueOf(year));
        xAxis.setLabel("Months");
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(12);
        xAxis.setTickUnit(1);
        yAxis.setLabel(vOrC);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(vOrC);

        for(Point i : points) {
            Timestamp ts = i.getTime();
            int month = Integer.parseInt(ts.toString().substring(5, 7));

            if (vOrC.equals("VirusPPM")) {
                series1.getData().add(new XYChart.Data(month, i.getVirusPPM()));
            } else {
                series1.getData().add(new XYChart.Data(month
                        , i.getContaminantPPM()));
            }
        }
        chartHistorical.getData().addAll(series1);
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
                year = Integer.parseInt(yearField.getText());

                if(virusOrContaminant.getValue().equals("VirusPPM")) {
                    vOrC = "VirusPPM";
                } else {
                    vOrC = "ContaminantPPM";
                }

                ArrayList<Point> pointList = facade.getHistoryByLocation
                        (location, year);
                initGraph(location, year, vOrC, pointList);


            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText(
                        "Longitude or Latitude"
                                + " or Year cannot include letters!!!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnBackPressed() {
        mainApplication.initRootLayout(mainApplication.getMainScreen());
    }
}
