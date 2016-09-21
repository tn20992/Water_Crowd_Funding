package controller;


import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import fxapp.MainFXApplication;

import model.User;
import model.Facade;

public class LogoutScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;
    private Stage _dialogStage;
    private BorderPane _rootLayout;

    private User user;
    private Facade facade = Facade.getFacade();

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
        facade.logOutUser(user);
        mainApplication.showWelcomeScreen();
    }
}

