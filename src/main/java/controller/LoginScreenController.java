package controller;

import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

/**
 * The controller for the root/main window
 *
 */
public class LoginScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Login Button in Login Screen
     */
    @FXML
    private void logButtonLogPressed() {


    }

    /**
     * Cancel button in Login Screen
     */
    @FXML
    private void cancelButtonLogPressed() {


    }

}
