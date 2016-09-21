package controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Facade;
import model.User;
import model.exceptions.NonUniqueUsernameException;

/**
 * The controller for the root/main window
 *
 */
public class RegistrationScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;
    private Stage _dialogStage;
    private BorderPane _rootLayout;

    @FXML
    private PasswordField passFieldReg;

    @FXML
    private TextField userIdFieldReg;

    @FXML
    private PasswordField confirmPassFieldReg;

    private String pass;
    private String confirmPass;
    private String userId;
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

    public void setInfo() {
        pass = passFieldReg.getText();
        userId = userIdFieldReg.getText();
    }
    /**
     * Login Button in Login Screen
     */
    @FXML
    private void regButtonRegPressed() throws NonUniqueUsernameException{
        mainApplication.initRootLayout(mainApplication.getMainScreen());
        if (passFieldReg.getText().equals(confirmPassFieldReg.getText())) {
            setInfo();
            facade.createUser(userId,pass);
            _dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Passwords do not match");
            alert.setContentText("The passwords did not match.");
        }
    }

    /**
     * Cancel button in Login Screen
     */
    @FXML
    private void cancelButtonRegPressed() {
        _dialogStage.close();

    }

}
