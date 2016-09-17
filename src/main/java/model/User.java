package model;

/*
 * Class for users of the application
 * @author Bharath Kalidindi
 */
public class User {

    //attributes of User
    private String username;
    private String password;

    /*
     * Creates a new user
     * @param username the username for the user
     * @param password the password for the user
     */
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*
     * Getters + Setters for user's username and password
     */
    public String getUsername () { return username; }
    public String getPassword () { return password; }
    public void setUsername (String newUsername) { username = newUsername; }
    public void setPassword (String newPassword) { password = newPassword; }

    /*
     * Set equals to compare usernames.
     * @param obj the user instance
     * @return whether the object is a person and their username matches
     */
    @Override
    public boolean equals (Object obj) {
        if (null == obj) { return false; }
        if (this == obj) { return true; }
        if (!(obj instanceof User)) { return false; }
        User usr = (User) obj;
        return this.username.equals(usr.username);
    }

    /*
     * Set hash code equal to the username's hashcode.
     * @return the username's hashcode
     */
    @Override
    public int hashCode () {
        return username.hashCode();
    }

    /**
     *
     * @return the username's string
     */
    @Override
    public String toString () {
        return username;
    }
}
