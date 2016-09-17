package model.exceptions;

public class NonUniqueUsernamesException extends Exception {
    public NonUniqueUsernamesException(String message) {
        super(message);
    }
}
