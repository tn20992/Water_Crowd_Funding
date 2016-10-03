package fxapp;

import controller.*;
import model.SourceReport;
import model.User;

import model.Facade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainFXApplication extends Application {

    /**
     * the main container for the application window
     */
    private Stage mainScreen;

    private User user;

    private MainFXApplication mainFX;

    /**
     * the main layout for the main window
     */
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        mainFX = this;
        mainScreen = primaryStage;
        showWelcomeScreen(mainScreen);
        Facade.initialize();
    }

    /**
     * return a reference to the main window stage
     *
     * @return reference to main stage
     */
    public Stage getMainScreen() {
        return mainScreen;
    }

    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen the main Stage window of the application
     */
    public void initRootLayout(Stage mainScreen) {
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            rootLayout = loader.load(new FileInputStream("src/main/java/view/MainScreen.fxml"));

            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(mainFX);

            setViewProfile(controller);


            // Set the Main App title
            mainScreen.setTitle("Clean Water Reporting Program");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setup our default application view that is shown on application startup
     * This is displayed in the startup window
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * preconditions - the view is initialized and displayed
     *
     *
     */
    public void showWelcomeScreen(Stage mainScreen) {
        try {
            // Load welcome screen.
            FXMLLoader loader = new FXMLLoader();
            BorderPane WelScreen = loader.load(new FileInputStream("src/main/java/view/WelcomeScreen.fxml"));

            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(mainFX);

            mainScreen.setTitle("Clean Water Reporting Program");

            // Show the scene containing the root layout.
            Scene scene = new Scene(WelScreen);
            mainScreen.setScene(scene);
            mainScreen.show();

        } catch (IOException e) {
            //error on load, so log it
            e.printStackTrace();
        }

    }

    public void showLoginScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            AnchorPane page = loader.load(new FileInputStream("src/main/java/view/LoginScreen.fxml"));

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            LoginScreenController controller = loader.getController();

            controller.setDialogStage(dialogStage);
            controller.setRootLayout(rootLayout);
            controller.setMainApp(mainFX);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegistrationScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            AnchorPane page = loader.load(new FileInputStream("src/main/java/view/RegistrationScreen.fxml"));

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RegistrationScreenController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainFX);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the edit profile screen
     */
    public void showEditProfileScreen() {
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            BorderPane editProfile= loader.load(new FileInputStream("src/main/java/view/EditProfileScreen.fxml"));

            rootLayout.setCenter(editProfile);
            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(mainFX);
            controller.setEditProfileView(user);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the the main report screen
     */
    public void showMainReportScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            BorderPane mainReport= loader.load(new FileInputStream("src/main/java/view/MainReportScreen.fxml"));

            rootLayout.setCenter(mainReport);
            // Give the controller access to the main app.
            MainReportController controller = loader.getController();
            controller.setMainApp(mainFX);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the the submit report screen
     */
    public void showSubmitReportScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            BorderPane submitReport= loader.load(new FileInputStream("src/main/java/view/SubmitReportScreen.fxml"));

            rootLayout.setCenter(submitReport);
            // Give the controller access to the main app.
            SubmitReportController controller = loader.getController();
            controller.setMainApp(mainFX);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the the view report screen
     */
    public void showViewReportScreen(SourceReport listedReport) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            BorderPane viewReport= loader.load(new FileInputStream("src/main/java/view/ViewReportScreen.fxml"));


            rootLayout.setCenter(viewReport);
            // Give the controller access to the main app.
            ViewReportController controller = loader.getController();
            controller.setReporterName(listedReport.getReporter().getName());
            controller.setLatitude(listedReport.getLocation().getLatitude());
            controller.setLongitudes(listedReport.getLocation().getLongitude());
            controller.setWaterType(listedReport.getTypeOfWater());
            controller.setWaterCondition(listedReport.getConditionOfWater());
            controller.setReportNumber(listedReport.getSourceReportNumber());
            controller.setTimestamp(listedReport.getCreated());

            controller.setMainApp(mainFX);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user
     *
     * @param user user which use to login
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get user
     * @return user which use to login
     */
    public User getUser() {
        return user;
    }

    /**
     * Set infomation into profile screen
     * @param controller mains screen controller
     */
    private void setViewProfile(MainScreenController controller) {
        if (user != null) {
            controller.setUserNameView(user.getName());
            controller.setAccountTypeView(user.getAccountType().toString());
            controller.setUserPassView(user.getPassword());
            controller.setEmailView(user.getEmail());
            controller.setAddressView(user.getStreetAddress());
        }
    }


    /**
     * main method for main application class
     * @param args command line parameters
     */
    public static void main(String[] args) {
        launch(args);
    }

}
