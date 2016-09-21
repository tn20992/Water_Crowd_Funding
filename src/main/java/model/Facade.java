package model;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.exceptions.NonUniqueUsernameException;

/**
 * Class that abstracts the models away from the controllers
 * @author Ryan Voor
 */
public class Facade {

    /**
     * called once by MainFXApplication. Sets up the Facade class and the model
     */
    public static void initialize() {

        // initialize the one facade instance for the application
        facade = new Facade();

        // set up database connection
        String url = "jdbc:postgresql://localhost/thestorm";
        String user = "postgres";
        String password = "password";

        try {

            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }
    }

    /** the single connection to the database that gets set up by
    initialize and used by every database method in this class */
    private static Connection connection;

    /** The single instance of the Facade class that all controllers will use
    to interact with the model */
    private static Facade facade;

    /** the users that are currently logged in. If no user is logged in
    then this will be empty */
    private ArrayList<User> loggedInUsers;

    /**
     * private no-args constructor for the Facade class
     */
    private Facade() {

        // initialized non-connection instance variables
        loggedInUsers = new ArrayList<User>();

    }

    /**
     * returns the single instance of facade that gets used by
     * everything in the application to access data
     * @return Facade the single instance of facade that will get used
     * everywhere in the application
     */
    public static Facade getFacade() {
        return facade;
    }

    /**
     * checks if the username and password are valid, if so attemps to log the
     * user in and return true, if not valid or cannot log the user in then
     * returns false
     * @param username the username typed in by the user that's attempting to log in
     * @param password the password typed in by the user that's attempting to log in
     * @return whether the user was successfully logged in or not
     */
    public boolean logInUser(String username, String password) throws NonUniqueUsernameException {
        User user = getUserByUsername(username);

        if (null == user) {
            return false;
        }

        if (password.equals(user.getPassword())) {
            if (loggedInUsers.contains(user)) {
                // this case should hypothetically never happen
                // since if a user is logged in then they shouldn't
                // be trying to log in
                return true;
            }
            loggedInUsers.add(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * logs out the user that is currently logged in
     * @return boolean true if the user was logged out successfully, false otherwise
     */
    public boolean logOutUser(User user) {
        return loggedInUsers.remove(user);
    }

    /**
     * returns whether the given user is logged in
     * @param user the user in question
     * @return whether the given user is logged in
     */
    public boolean isUserLoggedIn(User user) {
        return loggedInUsers.contains(user);
    }

    /**
     * retrieves and returns all the users in the system
     * @return ArrayList<User> all the users in the system
     */
    public ArrayList<User> getUsers() {
        try {

            Statement statement        = connection.createStatement();
            String query               = "SELECT username, password FROM tb_entity";
            ResultSet statementResults = statement.executeQuery(query);
            ArrayList<User> results    = new ArrayList<User>();

            while (statementResults.next()) {
                results.add(
                    new User(
                        statementResults.getString(1),
                        statementResults.getString(2)
                    )
                );
            }

            return results;

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * returns the user with the given username in the system if one exists
     * Null otherwise
     * @param username the username of the User that will be returned
     * @return User the user that matches the given username
     */
    public User getUserByUsername(String username) {
        try {

            String query                        = "SELECT username, password FROM tb_entity WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet statementResults = preparedStatement.executeQuery();
            ArrayList<User> results    = new ArrayList<User>();

            while (statementResults.next()) {
                results.add(
                    new User(
                        statementResults.getString(1),
                        statementResults.getString(2)
                    )
                );
            }

            // going to assume that there is only 1 User in the results set
            // since the username column in the database is unique
            if (results.size() > 0) {
                return results.get(0);
            } else {
                return null;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * creates a new user in the system with the given username and password
     * if the newly created user will be unique
     * @param username the username of the user to be created
     * @param password the password of the user to be created
     * @return User the newly created user, null if this user would not be unique
     */
    public void createUser(String username, String password) throws NonUniqueUsernameException {
        try {

            String query                        = "INSERT INTO tb_entity (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            if (null != getUserByUsername(username)) {
                throw new NonUniqueUsernameException("Attempted to create a user with a username that was taken");
            }

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }
    }
}
