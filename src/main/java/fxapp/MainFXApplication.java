package fxapp;

import controller.LoginScreenController;
import controller.MainScreenController;
import controller.RegistrationScreenController;
import controller.WelcomeScreenController;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;


//import model.Facade;

public class MainFXApplication extends Application {

    /**
     * the main container for the application window
     */
    private Stage mainScreen;

    /**
     * the main layout for the main window
     */
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        showWelcomeScreen();
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
    private void initRootLayout(Stage mainScreen) {
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            rootLayout = loader.load(new FileInputStream("src/main/java/view/MainScreen.fxml"));

            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("theSTORM");

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
    public void showWelcomeScreen() {
        try {
            // Load welcome screen.
            FXMLLoader loader = new FXMLLoader();
            BorderPane RegScreen = loader.load(new FileInputStream("src/main/java/view/WelcomeScreen.fxml"));

            // Set welcome screen into the center of root layout.
            rootLayout.setCenter(RegScreen);

            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);

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
            controller.setMainApp(this);

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
            controller.setRootLayout(rootLayout);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
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
