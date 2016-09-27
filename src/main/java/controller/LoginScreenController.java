package controller;

import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Facade;
import model.User;
import model.exceptions.NonUniqueUsernameException;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * The controller for the root/main window
 *
 */
public class LoginScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    private Stage _dialogStage;

    private BorderPane _rootLayout;

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
        _dialogStage = dialogStage;
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
     * Login Button in Login Screen
     */
    @FXML
    private void logButtonLogPressed() throws NonUniqueUsernameException{
        Facade temp = Facade.getFacade();
        boolean match = temp.logInUser(userIdFieldLog.getText(), passFieldLog.getText());
        if (match) {
            mainApplication.initRootLayout(mainApplication.getMainScreen());
            _dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("The passwords did not match.");
            alert.showAndWait();
        }
    }

    /**
     * Cancel button in Login Screen
     */
    @FXML
    private void cancelButtonLogPressed() {
        _dialogStage.close();

    }

}
