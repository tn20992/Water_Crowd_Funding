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
import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.geometry.Insets;
import model.ConditionOfWater;
import model.Facade;
import model.Location;
import model.SourceReport;
import model.TypeOfWater;
import netscape.javascript.JSObject;

import fxapp.MainFXApplication;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * Controller for map screen
 *
 */
public class WaterAvailabilityController implements Initializable
        , MapComponentInitializedListener {

    private MainFXApplication mainApplication;
    private Stage mainStage;

    private Facade facade = Facade.getFacade();

    private GoogleMapView mapView;

    private GoogleMap map;

    private Stage dialogSubmit = new Stage();

    private TextField latField = new TextField();

    private TextField longField = new TextField();

    private ComboBox<TypeOfWater> waterTypeBox = new ComboBox<>();

    private ComboBox<ConditionOfWater> waterConditionBox = new ComboBox<>();

    private ObservableList<TypeOfWater> typeOfWatersList = FXCollections
            .observableArrayList(TypeOfWater.values());

    private ObservableList<ConditionOfWater> condOfWatersList = FXCollections
            .observableArrayList(ConditionOfWater.values());

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
            LatLong loc = new LatLong(s.getLocation().getLatitude()
                    , s.getLocation().getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(s.toString());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                UIEventType.click,
                (JSObject obj) -> {
                    InfoWindowOptions infoWindowOptions;
                    infoWindowOptions = new InfoWindowOptions();
                    infoWindowOptions.content("<h2>Water " + s.toString()
                        + "</h2>"
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
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
                LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
                latField.setText("" + ll.getLatitude());
                longField.setText("" + ll.getLongitude());
                showSubmitDialog();
                dialogSubmit.showAndWait();
            });
    }

    /**
     * Show the submit water report dialog
     */
    public void showSubmitDialog() {
        waterTypeBox.setItems(typeOfWatersList);
        waterTypeBox.getSelectionModel().select(TypeOfWater.BOTTLED);
        waterTypeBox.setPrefWidth(170);

        waterConditionBox.setItems(condOfWatersList);
        waterConditionBox.getSelectionModel().select(ConditionOfWater.WASTE);
        waterConditionBox.setPrefWidth(170);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button btnSubmit = new Button("Submit");
        btnSubmit.setPrefSize(115, 20);
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                submitReportPressed();
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefSize(115, 20);
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogSubmit.close();
            }
        });

        hbox.getChildren().addAll(btnSubmit, btnCancel);

        VBox vBox = new VBox();

        AnchorPane frame = new AnchorPane();
        frame.setLayoutX(20);
        frame.setLayoutY(20);
        frame.setPrefHeight(200);
        frame.setPrefWidth(300);
        frame.setStyle("-fx-background-color: #FFFFFF;");
        GridPane grid = new GridPane();
        Label lat = new Label("Latitude: ");
        Label log = new Label("Longtitude: ");
        Label wType = new Label("Water Type : ");
        Label wCondition = new Label("Water Condition : ");
        GridPane.setConstraints(lat, 1, 1);
        GridPane.setConstraints(latField, 2, 1);
        GridPane.setConstraints(log, 1, 2);
        GridPane.setConstraints(longField, 2, 2);
        GridPane.setConstraints(wType, 1, 3);
        GridPane.setConstraints(waterTypeBox, 2, 3);
        GridPane.setConstraints(wCondition, 1, 4);
        GridPane.setConstraints(waterConditionBox, 2, 4);
        grid.getChildren().addAll(lat, latField, log, longField, wType,
                waterTypeBox, wCondition, waterConditionBox);

        vBox.getChildren().addAll(grid, hbox);

        frame.getChildren().addAll(vBox);
        Scene scene = new Scene(frame);

        dialogSubmit = new Stage();
        dialogSubmit.setTitle("Submit Water Report");
        dialogSubmit.initModality(Modality.WINDOW_MODAL);
        dialogSubmit.initOwner(mainStage);
        dialogSubmit.setScene(scene);
    }

    /**
     * Action when user click submit button
     */
    public void submitReportPressed() {
        if (latField.getText().equals("") || longField.getText()
                .equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(
                    "Longitude or Latitude cannot be empty!!!");
            alert.showAndWait();
        } else {
            try {
                DecimalFormat df = new DecimalFormat("#.###");
                double longitude = Double.parseDouble(df.format(Double
                        .parseDouble(longField.getText())));

                double latitude = Double.parseDouble(df.format(Double
                        .parseDouble(latField.getText())));

                Location location = new Location(longitude, latitude);

                TypeOfWater waterType = waterTypeBox.getValue();
                ConditionOfWater waterCondition = waterConditionBox.getValue();
                facade.createSourceReport(mainApplication.getUser()
                        .getUsername(), location, waterType, waterCondition);

                dialogSubmit.close();
                mapInitialized();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText(
                        "Longitude or Latitude cannot include letters!!!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}