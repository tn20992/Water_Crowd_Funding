package fxapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import controller.WelcomeScreenController;
//import model.Facade;

public class MainFXApplication extends Application {

    /** the main container for the application window */
    private Stage mainScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
//        Facade.initialize();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

    
        String url = "jdbc:postgresql://localhost/thestorm";
        String user = "postgres";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                //Logger lgr = Logger.getLogger(Version.class.getName());
                //lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        } 
    }

    /**
     * return a reference to the main window stage
     * @return reference to main stage
     * */
    public Stage getMainScreen() { return mainScreen; }

    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("/view/WelcomeScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("Welcome!");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();

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
