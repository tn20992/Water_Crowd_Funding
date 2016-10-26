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
     * @param username the username typed in by the user that's attempting
     * to log in
     * @param password the password typed in by the user that's attempting
     * to log in
     * @return whether the user was successfully logged in or not
     */
    public boolean logInUser(String username,
        String password) throws NonUniqueUsernameException {
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
     * @return boolean true if the user was logged out
     * successfully, false otherwise
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
            String query               = "SELECT username, "
                                              + "password, "
                                              + "name, "
                                              + "account_type, "
                                              + "email, "
                                              + "street_address "
                                         + "FROM tb_entity";
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

            System.out.println("Could not connect to the database: "
                + e.getMessage());
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

            String query = "SELECT username, "
                                + "password, "
                                + "name, "
                                + "account_type, "
                                + "email, "
                                + "street_address "
                           + "FROM tb_entity "
                          + "WHERE username = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
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

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * takes in information for a user and returns a User object with
     * all that information
     * @param username the username to be put into a User object
     * @param password the password to be put into a User object
     * @param name the name to be put into a User object
     * @param accountTypeInt the account type to be put into a User object
     * in integer form not AccountType form
     * @param email the email to be put into a User object
     * @param streetAddress the street address to be put into a User object
     * @return User a user with all the parameter information in it
     */
    private User makeUserObject(String username, String password,
        String name, int accountTypeInt, String email, String streetAddress) {
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
    public void createUser(String username, String password,
        String name, AccountType accountType)
            throws NonUniqueUsernameException {
        try {

            String query
                = "INSERT INTO tb_entity "
                    + "(username, password, name, account_type) "
                        + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, accountType.ordinal());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            if (null != getUserByUsername(username)) {
                throw new NonUniqueUsernameException("Attempted to create "
                    + "a user with a username that was taken");
            }

            System.out.println("Could not connect to the database: "
                + e.getMessage());
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

            String query
                = "UPDATE tb_entity SET email = ? WHERE username = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * updates the user with the given username to have the given
     * street address
     * @param username the username of the user to be updated
     * @param streetAddress the new street address to give the user
     * @return User the user after the update
     */
    public User editUserStreetAddressByUsername(String username,
        String streetAddress) {
        try {

            String query
                = "UPDATE tb_entity SET street_address = ? WHERE username = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setString(1, streetAddress);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
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

            String query
                = "UPDATE tb_entity SET name = ? WHERE username = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
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

            String query
                = "UPDATE tb_entity SET password = ? WHERE username = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            return getUserByUsername(username);

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * Returns a list of all the SourceReports in the database
     * @return ArrayList<SourceReport> a list of all the SourceReports
     * in the database
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
                                             + " FROM tb_source_report sr "
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

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * returns the SourceReport with the given Report Number, returns
     * null if no SourceReport with that Report Number exists
     * @param sourceReportNumber the Report Number of the SourceReport
     * to return
     * @return SourceReport the SourceReport with the given Report Number
     */
    public SourceReport getSourceReportByReportNumber(int sourceReportNumber) {
        try {

            String query = "SELECT sr.source_report, "
                                + "e.username, "
                                + "sr.created, "
                                + "sr.longitude, "
                                + "sr.latitude, "
                                + "sr.type_of_water, "
                                + "sr.condition_of_water "
                          + " FROM tb_source_report sr "
                     + "INNER JOIN tb_entity e "
                             + "ON sr.reporter = e.entity "
                          + "WHERE sr.source_report = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setInt(1, sourceReportNumber);

            ResultSet statementResults = preparedStatement.executeQuery();
            ArrayList<SourceReport> results    = new ArrayList<SourceReport>();

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

            // going to assume that there is only 1 SourceReport
            // in the results set since the report_number
            // column in the database is unique
            if (results.size() > 0) {
                return results.get(0);
            } else {
                return null;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * uses the paramter data to create a SourceReport in the database
     * @param username the username of the User that submitted
     * this SourceReport
     * @param location the Location of the water source of this
     * SourceReport
     * @param typeOfWater the type of the water of this SourceReport
     * @param conditionOfWater the condition of the water of this SourceReport
     */
    public void createSourceReport(String username, Location location,
        TypeOfWater typeOfWater, ConditionOfWater conditionOfWater) {
        try {

            String query
                = "SELECT entity FROM tb_entity WHERE username = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet statementResults = preparedStatement.executeQuery();

            int userID = -1; /* this should cause an error if this every
                is the actual value passed to the database */
            while (statementResults.next()) {

                // this loop should only ever happen once since
                // username on tb_entity has a unique constraint
                userID = statementResults.getInt(1);

            }

            if (userID == -1) {
                throw new SQLException("Username " + username
                    + " was not found in the database.");
            }

            query             = "INSERT INTO tb_source_report "
                + "(reporter, longitude, latitude, type_of_water, "
                    + "condition_of_water) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setDouble(2, location.getLongitude());
            preparedStatement.setDouble(3, location.getLatitude());
            preparedStatement.setInt(4, typeOfWater.ordinal());
            preparedStatement.setInt(5, conditionOfWater.ordinal());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }
    }

    /**
     * takes in a bunch of data for a Source Report and returns a SourceReport
     * object with all that instance data
     * @param sourceReportNumber the unique ID number associated with
     * the SourceReport
     * @param reporterUsername the username of the User in that submitted
     * this SourceReport
     * @param created the time that this SourceReport was created
     * @param longitude the longitude coordinate of the location of the water
     * source of this SourceReport
     * @param latitude the latitude coordinate of the location of th water
     * source of this SourceReport
     * @param typeOfWaterInt the integer representation of the TypeOfWater
     * enum of the type of water of the water source of this SourceReport
     * @param conditionOfWaterInt the integer representation of the
     * ConditionOfWater enum of the condition of water of the water source
     * of this SourceReport
     * @return SourceReport the SourceReport object with all the parameter data
     */
    private SourceReport makeSourceReportObject(int sourceReportNumber,
        String reporterUsername, Timestamp created,
            double longitude, double latitude, int typeOfWaterInt,
                int conditionOfWaterInt) {
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


    /**
     * Returns a list of all the PurityReports in the database
     * @return ArrayList<PurityReport> a list of all the PurityReports
     * in the database
     */
    public ArrayList<PurityReport> getPurityReports() {
        try {

            Statement statement             = connection.createStatement();
            String query                    = "SELECT pr.purity_report, "
                                                   + "e.username, "
                                                   + "pr.created, "
                                                   + "pr.longitude, "
                                                   + "pr.latitude, "
                                                   + "pr.overall_condition, "
                                                   + "pr.virus_ppm, "
                                                   + "pr.contaminant_ppm "
                                             + " FROM tb_purity_report pr "
                                        + "INNER JOIN tb_entity e "
                                                + "ON pr.reporter = e.entity";
            ResultSet statementResults      = statement.executeQuery(query);
            ArrayList<PurityReport> results = new ArrayList<PurityReport>();

            while (statementResults.next()) {

                PurityReport purityReport = makePurityReportObject(
                    statementResults.getInt(1),
                    statementResults.getString(2),
                    statementResults.getTimestamp(3),
                    new Location(statementResults.getDouble(4),
                        statementResults.getDouble(5)),
                    statementResults.getInt(6),
                    statementResults.getDouble(7),
                    statementResults.getDouble(8)
                );

                results.add(purityReport);

            }

            return results;

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }


    /**
     * returns the Purity Report that has the given report number
     * @param purityReportNumber the reportNumber of the Purity Report to be
     * returned
     * @return PurityReport the purity report with the given reportNumber
     */
    public PurityReport getPurityReportByReportNumber(int purityReportNumber) {
        try {

            String query = "SELECT pr.purity_report, "
                                + "e.username, "
                                + "pr.created, "
                                + "pr.longitude, "
                                + "pr.latitude, "
                                + "pr.overall_condition, "
                                + "pr.virus_ppm, "
                                + "pr.contaminant_ppm "
                          + " FROM tb_purity_report pr "
                     + "INNER JOIN tb_entity e "
                             + "ON pr.reporter = e.entity "
                          + "WHERE pr.source_report = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setInt(1, purityReportNumber);

            ResultSet statementResults = preparedStatement.executeQuery();
            ArrayList<PurityReport> results    = new ArrayList<PurityReport>();

            while (statementResults.next()) {

                PurityReport purityReport = makePurityReportObject(
                    statementResults.getInt(1),
                    statementResults.getString(2),
                    statementResults.getTimestamp(3),
                    new Location(statementResults.getDouble(4),
                        statementResults.getDouble(5)),
                    statementResults.getInt(6),
                    statementResults.getDouble(7),
                    statementResults.getDouble(8)
                );
                results.add(purityReport);

            }

            // going to assume that there is only 1 PurityReport
            // in the results set since the report_number
            // column in the database is unique
            if (results.size() > 0) {
                return results.get(0);
            } else {
                return null;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }


    /**
     * uses the paramter data to create a PurityReport in the database
     * @param username the username of the User that submitted
     * this SourceReport
     * @param location the Location of the water source of this
     * SourceReport
     * @param overallCondition the OverallCondition (enum) of this SourceReport
     * @param virusPPM the type of the water of this SourceReport
     * @param contaminantPPM the condition of the water of this SourceReport
     */
    public void createPurityReport(String username, Location location,
        OverallCondition overallCondition, double virusPPM,
            double contaminantPPM) {
        try {

            // TODO this could be abstracted into its own helper method
            String query
                = "SELECT entity FROM tb_entity WHERE username = ?";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet statementResults = preparedStatement.executeQuery();

            int userID = -1; /* this should cause an error if this every
                is the actual value passed to the database */
            while (statementResults.next()) {

                // this loop should only ever happen once since
                // username on tb_entity has a unique constraint
                userID = statementResults.getInt(1);

            }

            if (userID == -1) {
                throw new SQLException("Username " + username
                    + " was not found in the database.");
            }

            query             = "INSERT INTO tb_purity_report "
                + "(reporter, longitude, latitude, overall_condition, "
                    + "virus_ppm, contaminant_ppm) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setDouble(2, location.getLongitude());
            preparedStatement.setDouble(3, location.getLatitude());
            preparedStatement.setInt(4, overallCondition.ordinal());
            preparedStatement.setDouble(5, virusPPM);
            preparedStatement.setDouble(6, contaminantPPM);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }
    }


    /**
     * returns a PurityReport object with the given data
     * @param sourceReportNumber the report number of the PurityReport
     * to be created
     * @param reporterUsername the username of the report of the PurityReport
     * to be created
     * @param created the created time of the PurityReport
     * to be created
     * @param longitude the longitude of the PurityReport
     * to be created
     * @param latitude the latitude of the PurityReport
     * to be created
     * @param overallConditionInt the int of the OverallCondition (enum)
     * of the PurityReport to be created
     * @param virusPPM the virusPPM of the PurityReport
     * to be created
     * @param contaminantPPM the contaminantPPM of the PurityReport
     * to be created
     * @return PurityReport a PurityReport object with all the parameter data
     */
    private PurityReport makePurityReportObject(int sourceReportNumber,
        String reporterUsername, Timestamp created, Location location,
            int overallConditionInt, double virusPPM,
                double contaminantPPM) {
        double longitude = location.getLongitude();
        double latitude  = location.getLatitude();

        OverallCondition overallCondition;
        switch (overallConditionInt) {
        case 0:
            overallCondition = OverallCondition.SAFE;
            break;
        case 1:
            overallCondition = OverallCondition.TREATABLE;
            break;
        case 2:
            overallCondition = OverallCondition.UNSAFE;
            break;
        default:
            overallCondition = OverallCondition.UNSAFE;
            break;
        }

        return new PurityReport(
            sourceReportNumber,
            getUserByUsername(reporterUsername),
            created,
            new Location(longitude, latitude),
            overallCondition,
            virusPPM,
            contaminantPPM
        );
    }

    /**
     * returns a list of Points with data for the given location in
     * the given year
     * @param the location for the Points to relate to
     * @param year the year for the Points to be within
     * @return ArrayList<Point>
     */
    public ArrayList<Point> getHistoryByLocation(Location location, int year) {
        try {

            String date                     = year + "-01-01 00:00:00";
            String query = "SELECT pr.created, "
                                + "pr.virus_ppm, "
                                + "pr.contaminant_ppm "
                           + "FROM tb_purity_report pr "
                          + "WHERE pr.longitude = ? "
                            + "AND pr.latitude = ? "
                            + "AND pr.created > ?::timestamp "
                            + "AND pr.created "
                            + "< ?::timestamp + '1 year'::interval";
            PreparedStatement preparedStatement
                = connection.prepareStatement(query);
            preparedStatement.setDouble(1, location.getLongitude());
            preparedStatement.setDouble(2, location.getLatitude());
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, date);
            ResultSet statementResults = preparedStatement.executeQuery();
            ArrayList<Point> results = new ArrayList<Point>();

            while (statementResults.next()) {

                Point point = makePointObject(
                    statementResults.getTimestamp(1),
                    statementResults.getDouble(2),
                    statementResults.getDouble(3)
                );

                results.add(point);

            }

            return results;

        } catch (SQLException e) {

            System.out.println("Could not connect to the database: "
                + e.getMessage());
            System.exit(0);

        }

        // this is needed for compilation
        // execution should never reach this line
        return null;
    }

    /**
     * returns a Point object with all the parameter data
     * @param time the time for the Point object to be created
     * @param virusPPM the virusPPM for the Point
     * object to be created
     * @param contaminantPPM the contaminantPPM for
     * the Point object to be created
     * @return Point the created Point object
     */
    private Point makePointObject(Timestamp time,
        double virusPPM, double contaminantPPM) {
        return new Point(time, virusPPM, contaminantPPM);
    }
}
