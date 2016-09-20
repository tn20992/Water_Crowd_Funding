package main.java.controller;


import javafx.fxml.FXML;
import main.java.fxapp.MainFXApplication;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.model.Facade;
import main.java.model.User;


public class LogoutScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;
    private Stage _dialogStage;
    private BorderPane _rootLayout;

    private User user;

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

    @FXML
    public void logoutActionClicked() {
        Facade.logOutUser(user);
        mainApplication.showWelcomeScreen();

    }


}

