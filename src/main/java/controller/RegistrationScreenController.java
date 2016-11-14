package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import fxapp.MainFXApplication;
import model.AccountType;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Facade;
import model.exceptions.NonUniqueUsernameException;

/**
 * Controller for registration screen
 *
 */
public class RegistrationScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;
    private Stage dialogStage;

    @FXML
    private PasswordField passFieldReg;

    @FXML
    private TextField userIdFieldReg;

    @FXML
    private PasswordField confirmPassFieldReg;

    @FXML
    private TextField nameFieldReg;

    @FXML
    private ComboBox<AccountType> accountTypeBox;

    private String name;
    private String pass;
    private String userId;
    private Facade facade = Facade.getFacade();

    private ObservableList<AccountType> accountTypeList = FXCollections
            .observableArrayList(AccountType.values());

    @FXML
    private void initialize() {
        accountTypeBox.setItems(accountTypeList);
        accountTypeBox.getSelectionModel().select(AccountType.USER);
    }


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
     * Setup info
     */
    public void setInfo() {
        pass = passFieldReg.getText();
        userId = userIdFieldReg.getText();
        name = nameFieldReg.getText();
    }
    /**
     * Login Button in Login Screen
     */
    @FXML
    private void regButtonRegPressed() throws NonUniqueUsernameException {

        if (userIdFieldReg.getText() == null
            || userIdFieldReg.getText().trim().isEmpty()) {
            alert("Invalid User ID.");
        } else if (passFieldReg.getText() == null
            || passFieldReg.getText().trim().isEmpty()) {
            alert("Invalid password.");
        } else if (nameFieldReg.getText() == null
            || nameFieldReg.getText().trim().isEmpty()) {
            alert("Invalid name.");
        } else {
            if (passFieldReg.getText().equals(confirmPassFieldReg.getText())) {
                setInfo();
                try {
                    facade.createUser(userId, pass, name,
                        AccountType.values()[accountTypeBox
                            .getSelectionModel().getSelectedIndex()]);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Register Successfully");
                    alert.setHeaderText("Your Registration was successful! ");
                    alert.setContentText("Please click OK to go back!");
                    alert.showAndWait();
                    dialogStage.close();
                } catch (Exception e) {
                    alert("Username is already taken! Try another one.");
                }
            } else {
                alert("Passwords do not match up.");
            }
        }
    }

    /**
     * Show error message
     * @param msg message
     */
    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Cancel button in Login Screen
     */
    @FXML
    private void cancelButtonRegPressed() {
        dialogStage.close();

    }

}
