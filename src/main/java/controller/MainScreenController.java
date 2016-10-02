package controller;

import fxapp.MainFXApplication;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Facade;
import model.User;

/**
 * The controller for the root/main window
 *
 */
public class MainScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    private BorderPane _editBorderPane;

    private User user;
    private Facade facade = Facade.getFacade();

    @FXML
    private TextField usernameView;

    @FXML
    private TextField passwordView;

    @FXML
    private TextField accountTypeView;

    @FXML
    private TextField emailView;

    @FXML
    private TextArea addressView;

    @FXML
    private TextArea addressEdit;

    @FXML
    private TextField nameEdit;

    @FXML
    private TextField passwordEdit;

    @FXML
    private TextField emailEdit;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Sets the BorderPane of edit dialog.
     *
     * @param editBorderPane the BorderPane for edit dialog
     */
    public void setEditBorderPane(BorderPane editBorderPane) {
        _editBorderPane = editBorderPane;
    }

    /**
     * Close menu item event handler
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

    /**
     * About menu item event handler
     */
    @FXML
    private void handleAboutMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Project");
        alert.setHeaderText("Information");
        alert.setContentText("The Storm Team");
        alert.showAndWait();

    }

    /**
     * Go back to the welcome screen when logout
     */
    @FXML
    private void logoutActionClicked() {
        facade.logOutUser(user);
        mainApplication.showWelcomeScreen();
    }

    /**
     * Go to the edit screen
     */
    @FXML
    private void EditPressed() {
        mainApplication.setEditProfileScreen(mainApplication.getMainScreen());
    }

    /**
     * Set information into edit screen
     * @param user user contains information
     */
    public void setEditProfileView(User user) {
        nameEdit.setText(user.getName());
        passwordEdit.setText(user.getPassword());
        emailEdit.setText(user.getEmail());
        addressEdit.setText(user.getStreetAddress());
    }

    /**
     * Comeback to the main screen with updated information
     */
    @FXML
    private void updatePressed() {
        if (passwordEdit.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Password is empty!!!");
            alert.showAndWait();
        } else {
            updateUserInfo();
            mainApplication.initRootLayout(mainApplication.getMainScreen());
        }
    }

    /**
     * Update information of user
     */
    private void updateUserInfo() {
        mainApplication.setUser(facade.editUserEmailByUsername(mainApplication.getUser()
                .getUsername(), emailEdit.getText()));
        mainApplication.setUser(facade.editUserStreetAddressByUsername(mainApplication.getUser()
                .getUsername(), addressEdit.getText()));
        mainApplication.setUser(facade.editUserNameByUsername(mainApplication.getUser()
                .getUsername(), nameEdit.getText()));
        mainApplication.setUser(facade.editUserPasswordByUsername(mainApplication.getUser()
                .getUsername(), passwordEdit.getText()));
    }

    @FXML
    private void cancelUpdatePressed() {
        mainApplication.initRootLayout(mainApplication.getMainScreen());
    }

    /**
     * Set name of user in profile view
     * @param username the name of user
     */
    public void setUserNameView(String username) {
        if (username == null || username.equals("")) {
            usernameView.setText("Not Yet Entered");
        } else {
            usernameView.setText(username);
        }
    }

    /**
     * Set pass of user in profile view
     * @param password the password of user
     */
    public void setUserPassView(String password) {
        if (password == null || password.equals("")) {
            passwordView.setText("Not Yet Entered");
        } else {
            passwordView.setText(password);
        }
    }

    public void setAccountTypeView(String accountView) { accountTypeView.setText(accountView);

    }

    /**
     * Set email in profile view
     * @param email email of user
     */
    public void setEmailView(String email) {
        if (email == null || email.equals("")) {
            emailView.setText("Not Yet Entered");
        } else {
            emailView.setText(email);
        }
    }

    /**
     * Set address in profile view
     * @param address address of user
     */
    public void setAddressView(String address) {
        if (address == null || address.equals("")) {
            addressView.setText("Not Yet Entered");
        } else {
            addressView.setText(address);
        }
    }
}
