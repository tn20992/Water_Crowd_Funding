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

    AccountType(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
