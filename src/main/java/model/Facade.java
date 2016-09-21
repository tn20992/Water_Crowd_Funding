package model;

import java.util.ArrayList;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

import model.exceptions.NonUniqueUsernamesException;

/**
 * Class that abstracts the models away from the controllers
 * @author Ryan Voor
 */
public class Facade {

    /**
     * called once by MainFXApplication. Sets up the Facade class and the model
     */
    public static void initialize() /*throws SQLException*/ {
        // TODO
        // for now this method will just create the Facade instance
        // in the future it may interact with the database and set up
        // collections of data objects etc.

        facade = new Facade();

        // set up database connection
        //String url = "jdbc:postgresql://localhost/thestorm";
        //String user = "postgres";
        //String password = "password";

        //connection = DriverManager.getConnection(url, user, password);
    }

    //private static Connection connection;

    /** The single instance of the Facade class that all controllers will use
    to interact with the model */
    private static Facade facade;

    public static Facade getFacade() {
        return facade;
    }

    /** the users that are currently logged in. If no user is logged in
    then this will be empty */
    private ArrayList<User> loggedInUsers;

    /* all the users in the system. This variable should never be accessed
    directly, even from within the Facade class. You should always use
    the getUsers() method to access this variable */
    // TODO
    // later we are going to get rid of this variable since we'll just call the database every time
    private ArrayList<User> users;

    /**
     * private no-args constructor for the Facade class
     */
    private Facade() {}

    /**
     * checks if the username and password are valid, if so attemps to log the
     * user in and return true, if not valid or cannot log the user in then
     * returns false
     * @param username the username typed in by the user that's attempting to log in
     * @param password the password typed in by the user that's attempting to log in
     * @return whether the user was successfully logged in or not
     */
    public boolean logInUser(String username, String password) throws NonUniqueUsernamesException {
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
     * TODO
     */
    public boolean isUserLoggedIn(User user) {
        return loggedInUsers.contains(user);
    }

    /**
     * returns the user with the given username in the system if one exists
     * Null otherwise
     * @param username the username of the User that will be returned
     * @return User the user that matches the given username
     */
    public User getUserByUsername(String username) throws NonUniqueUsernamesException {
        ArrayList<User> users = getUsers();
        ArrayList<User> matchedUsers = new ArrayList<User>();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                matchedUsers.add(user);
            }
        }

        if (matchedUsers.size() > 1) {
            // this should hypothetically never happen since we are checking that
            // users are unique when we create users
            throw new NonUniqueUsernamesException("There are 2 or more "
                + "users with the same username");
        }

        if (matchedUsers.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    /**
     * retrieves and returns all the users in the system
     * @return ArrayList<User> all the users in the system
     */
    public ArrayList<User> getUsers() {
        // TODO
        // for now this will just return the 'users' variable
        // from this class. Later on it will get a list from
        // the database

        return users;
    }

    /**
     * creates a new user in the system with the given username and password
     * if the newly created user will be unique
     * @param username the username of the user to be created
     * @param password the password of the user to be created
     * @return User the newly created user, null if this user would not be unique
     */
    public User createUser(String username, String password) throws NonUniqueUsernamesException {
        // TODO
        // for now this is just going to put the user object into the
        // 'users' variable in this class. Later on it will put an
        // entry in the database.

        User user = new User(username, password);

        if (users.contains(user)) {
            throw new NonUniqueUsernamesException("Attempted to create a user "
                + "with the same username as an existing user");
        } else {
            users.add(user);
            return user;
        }
    }
}
