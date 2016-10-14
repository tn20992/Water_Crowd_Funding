package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.event.UIEventType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import netscape.javascript.JSObject;

import javafx.application.Application;
import javafx.scene.Scene;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import model.Facade;
import model.SourceReport;

/**
 * Controller for map screen
 *
 */
public class WaterAvailabilityController implements Initializable, MapComponentInitializedListener {

    private MainFXApplication mainApplication;
    private Stage mainStage;

    private GoogleMapView mapView;

    private GoogleMap map;

    /**
     * Make a new constructor
     * @param main  reference to the FX application
     * @param stage the stage we want this map to be displayed in
     */
    public WaterAvailabilityController(MainFXApplication main, Stage stage) {
        mainApplication = main;
        mainStage = stage;
        setUpMap(stage);
    }

    /**
     * Construct the google map, set up the different parts of the layout
     *
     * @param stage the stage to put the map scene into
     */
    private void setUpMap(Stage stage) {
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
        mapView.setPrefHeight(Region.USE_COMPUTED_SIZE);
        mapView.setPrefWidth(Region.USE_COMPUTED_SIZE);

        Button btnBack = new Button("Back");
        btnBack.setLayoutX(10);
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApplication.initRootLayout(mainStage);
            }
        });

        GoogleMapView.setTopAnchor(btnBack, 80.00);
        mapView.getChildren().add(btnBack);

        mainApplication.getRootLayout().setCenter(mapView);
    }

    @Override
    public void mapInitialized() {
        Facade fc = Facade.getFacade();
        List<SourceReport> sourceReports = fc.getSourceReports();
        SourceReport sourceReport = sourceReports.get(0);
        double lat = sourceReport.getLocation().getLatitude();
        double longt = sourceReport.getLocation().getLongitude();

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(lat, longt))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(3);

        map = mapView.createMap(mapOptions);

        for (SourceReport s : sourceReports) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(s.getLocation().getLatitude(), s.getLocation().getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(s.toString());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>Water " + s.toString() + "</h2>"
                                + "Reporter: " + s.getReporterName()
                                + "<br>Condition: " + s.getConditionOfWater().toString()
                                + "<br>Type: " + s.getTypeOfWater().toString()
                                + "<br>Location: " + s.getLocation().getLatitude()
                                + ", " + s.getLocation().getLongitude()
                                + "<br>Time: " + s.getCreated().toString());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });
            map.addMarker(marker);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}