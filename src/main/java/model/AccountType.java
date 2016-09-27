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

    private final String workerType;

    AccountType(String workerType) {
        this.workerType = workerType;
    }

    /*
     * Returns account types in array form
     * @return the array of AccountType with each account type
     */
    public static AccountType[] getValues() {
        return new AccountType[]{AccountType.USER, AccountType.WORKER, AccountType.MANAGER, AccountType.ADMIN};
    }

    public String toString() {
        return workerType;
    }
}
