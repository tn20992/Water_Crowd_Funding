package controller;

import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * The controller for the root/main window
 *
 */
public class RegistrationScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;
    private Stage _dialogStage;
    private BorderPane _rootLayout;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Sets the rootLayout of MainScreen.
     *
     * @param rootLayout the BorderPane of the MainScreen
     */
    public void setRootLayout(BorderPane rootLayout) {
        _rootLayout = rootLayout;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Login Button in Login Screen
     */
    @FXML
    private void regButtonRegPressed() {
        try {
            // Load welcome screen.
            FXMLLoader loader = new FXMLLoader();
            AnchorPane LogSuccessScreen = loader.load(new FileInputStream("src/main/java/view/RegistrationSuccessScreen.fxml"));

            // Set welcome screen into the center of root layout.
            _rootLayout.setCenter(LogSuccessScreen);

            // Give the controller access to the main app.
            RegistrationSuccessController controller = loader.getController();
            controller.setMainApp(mainApplication);

        } catch (IOException e) {
            //error on load, so log it
            e.printStackTrace();
        }

        _dialogStage.close();

    }

    /**
     * Cancel button in Login Screen
     */
    @FXML
    private void cancelButtonRegPressed() {
        _dialogStage.close();

    }

}
