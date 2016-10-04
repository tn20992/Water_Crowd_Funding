package controller;

import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Facade;
import model.exceptions.NonUniqueUsernameException;

/**
 * The controller for the root/main window
 *
 */
public class LoginScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    private Stage dialogStage;

    private BorderPane rootLayout;

    @FXML
    private TextField userIdFieldLog;

    @FXML
    private PasswordField passFieldLog;

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
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the rootLayout of MainScreen.
     *
     * @param rootLayout the BorderPane of the MainScreen
     */
    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    /**
     * Login Button in Login Screen
     */
    @FXML
    private void logButtonLogPressed() throws NonUniqueUsernameException {
        Facade temp = Facade.getFacade();
        boolean match = temp.logInUser(userIdFieldLog.getText(),
            passFieldLog.getText());
        if (match) {
            mainApplication.setUser(temp.getUserByUsername(
                userIdFieldLog.getText()));
            mainApplication.initRootLayout(mainApplication.getMainScreen());
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Username or Password is incorrect");
            alert.setContentText("Username or Password is not matched"
                    + " with what we have in the system");
            alert.showAndWait();
        }
    }

    /**
     * Cancel button in Login Screen
     */
    @FXML
    private void cancelButtonLogPressed() {
        dialogStage.close();

    }

}
