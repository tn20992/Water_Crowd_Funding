package model;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import model.exceptions.NonUniqueUsernameException;

/**
 * Class that abstracts the models away from the controllers
 * @author Ryan Voor
 */
public class Facade {

    /**
     * called once by MainFXApplication. Sets up the Facade class and the model
     */
    public static Facade initialize() {

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
        return null;
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
            String query               = "SELECT username, password, name, account_type, email, street_address FROM tb_entity";
            ResultSet statementResults = statement.executeQuery(query);
            ArrayList<User> results    = new ArrayList<User>();

            while (statementResults.next()) {

                User user = makeUserObject(
                    statementResults.getString(1),
                    statementResults.getString(2),
                    statementResults.getString(3),
                    statementResults.getInt(4),
                    statementResults.getString(5),
                    statementResults.getString(6)
                );

                results.add(user);

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

            String query                        = "SELECT username, password, name, account_type, email, street_address FROM tb_entity WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet statementResults = preparedStatement.executeQuery();
            ArrayList<User> results    = new ArrayList<User>();

            while (statementResults.next()) {

                User user = makeUserObject(
                    statementResults.getString(1),
                    statementResults.getString(2),
                    statementResults.getString(3),
                    statementResults.getInt(4),
                    statementResults.getString(5),
                    statementResults.getString(6)
                );
                results.add(user);

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
     * takes in information for a user and returns a User object with all that information
     * @param username the username to be put into a User object
     * @param password the password to be put into a User object
     * @param name the name to be put into a User object
     * @param accountTypeInt the account type to be put into a User object in integer form not AccountType form
     * @param email the email to be put into a User object
     * @param streetAddress the street address to be put into a User object
     * @return User a user with all the parameter information in it
     */
    private User makeUserObject(String username, String password, String name, int accountTypeInt, String email, String streetAddress) {
        User user;
        switch (accountTypeInt) {
            case 0 :
                user = new User(
                    username,
                    password,
                    name,
                    AccountType.USER
                );
                break;

            case 1 :
                user = new User(
                    username,
                    password,
                    name,
                    AccountType.WORKER
                );
                break;

            case 2 :
                user = new User(
                    username,
                    password,
                    name,
                    AccountType.MANAGER
                );
                break;

            case 3 :
                user = new User(
                    username,
                    password,
                    name,
                    AccountType.ADMIN
                );
                break;

            default :
                user = new User(
                    username,
                    password,
                    name
                );
                break;

        }

        user.setEmail(email);
        user.setStreetAddress(streetAddress);
        return user;

    }

    /**
     * creates a new user in the system with the given username and password
     * if the newly created user will be unique
     * @param username the username of the user to be created
     * @param password the password of the user to be created
     */
    public void createUser(String username, String password, String name, AccountType accountType) throws NonUniqueUsernameException {
        try {

            String query                        = "INSERT INTO tb_entity (username, password, name, account_type) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, accountType.ordinal());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            if (null != getUserByUsername(username)) {
                throw new NonUniqueUsernameException("Attempted to create a user with a username that was taken");
            }

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }
    }

    /**
     * updates the user with the given username to have the given email
     * @param username the username of the user to be updated
     * @param email the new email to give the user
     * @return User the user after the update
     */
    public User editUserEmailByUsername(String username, String email) {
        try {

            String query                        = "UPDATE tb_entity SET email = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * updates the user with the given username to have the given street address
     * @param username the username of the user to be updated
     * @param streetAddress the new street address to give the user
     * @return User the user after the update
     */
    public User editUserStreetAddressByUsername(String username, String streetAddress) {
        try {

            String query                        = "UPDATE tb_entity SET street_address = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, streetAddress);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * updates the user with the given username to have the given name
     * @param username the username of the user to be updated
     * @param name the new name to give the user
     * @return User the user after the update
     */
    public User editUserNameByUsername(String username, String name) {
        try {

            String query                        = "UPDATE tb_entity SET name = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * updates the user with the given username to have the given password
     * @param username the username of the user to be updated
     * @param password the new password to give the user
     * @return User the user after the update
     */
    public User editUserPasswordByUsername(String username, String password) {
        try {

            String query                        = "UPDATE tb_entity SET password = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: " + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * TODO
     */
    public ArrayList<SourceReport> getSourceReports() {
        try {

            Statement statement             = connection.createStatement();
            String query                    = "SELECT sr.source_report, "
                                                   + "e.username, "
                                                   + "sr.created, "
                                                   + "sr.longitude, "
                                                   + "sr.latitude, "
                                                   + "sr.type_of_water, "
                                                   + "sr.condition_of_water "
                                               +" FROM tb_source_report sr "
                                         + "INNER JOIN tb_entity e "
                                                 + "ON sr.reporter = e.entity";
            ResultSet statementResults      = statement.executeQuery(query);
            ArrayList<SourceReport> results = new ArrayList<SourceReport>();

            while (statementResults.next()) {

                SourceReport sourceReport = makeSourceReportObject(
                    statementResults.getInt(1),
                    statementResults.getString(2),
                    statementResults.getTimestamp(3),
                    statementResults.getDouble(4),
                    statementResults.getDouble(5),
                    statementResults.getInt(6),
                    statementResults.getInt(7)
                );

                results.add(sourceReport);

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
     * TODO
     */
    private SourceReport makeSourceReportObject(int sourceReportNumber, String reporterUsername,
        Timestamp created, double longitude, double latitude,
            int typeOfWaterInt, int conditionOfWaterInt) {
        TypeOfWater typeOfWater;
        switch (typeOfWaterInt) {
            case 0:
                typeOfWater = TypeOfWater.BOTTLED;
                break;
            case 1:
                typeOfWater = TypeOfWater.WELL;
                break;
            case 2:
                typeOfWater = TypeOfWater.STREAM;
                break;
            case 3:
                typeOfWater = TypeOfWater.LAKE;
                break;
            case 4:
                typeOfWater = TypeOfWater.SPRING;
                break;
            case 5:
                typeOfWater = TypeOfWater.OTHER;
                break;
            default:
                typeOfWater = TypeOfWater.OTHER;
                break;
        }

        ConditionOfWater conditionOfWater;
        switch (conditionOfWaterInt) {
            case 0:
                conditionOfWater = ConditionOfWater.WASTE;
                break;
            case 1:
                conditionOfWater = ConditionOfWater.TREATABLECLEAR;
                break;
            case 2:
                conditionOfWater = ConditionOfWater.TREATABLEMUDDY;
                break;
            case 3:
                conditionOfWater = ConditionOfWater.POTABLE;
                break;
            default:
                conditionOfWater = ConditionOfWater.POTABLE;
                break;
        }

        return new SourceReport(
            sourceReportNumber,
            getUserByUsername(reporterUsername),
            created,
            new Location(longitude, latitude),
            typeOfWater,
            conditionOfWater
        );
    }
}
