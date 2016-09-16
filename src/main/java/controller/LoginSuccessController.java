package controller;

import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * The controller for the root/main window
 *
 */
public class LoginSuccessController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    private Stage _dialogStage;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
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
    private void logoutPressed() {


    }

}
