package model;

/**
 * Account Type enum
 * Stores Types User, Worker, Manager, Admin
 */
public enum AccountType {
    USER ("User"),
    WORKER ("Worker"),
    MANAGER ("Manager"),
    ADMIN ("Admin");

    private final String value;

    /*
     * Creates a new account type
     * @param value for the account type
     */
    AccountType(String value) {
        this.value = value;
    }

    /**
     *
     * @return the account type name
     */
    @Override
    public String toString() {
        return value;
    }
}
