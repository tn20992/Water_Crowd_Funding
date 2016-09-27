package model;

/**
 * Account Type enum
 * Stores Types User, Worker, Manager, Admin
 */
public enum AccountType {
    USR ("User"),
    WKR ("Worker"),
    MNG ("Manager"),
    ADM ("Admin");

    private final String workerType;

    AccountType(String workerType) {
        this.workerType = workerType;
    }

    /*
     * Returns account types in array form
     * @return the array of AccountType with each account type
     */
    public static AccountType[] getValues() {
        return new AccountType[]{AccountType.USR, AccountType.WKR, AccountType.MNG, AccountType.ADM};
    }

    public String toString() {
        return workerType;
    }
}
