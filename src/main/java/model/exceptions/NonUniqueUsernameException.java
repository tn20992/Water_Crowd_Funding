package model.exceptions;

public class NonUniqueUsernameException extends Exception {
    public NonUniqueUsernameException(String message) {
        super(message);
    }
}
