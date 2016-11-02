package model;

/**
 * Overall condition
 */
public enum OverallCondition {
    SAFE          ("Safe"),
    TREATABLE     ("Treatable"),
    UNSAFE        ("Unsafe");


    private final String value;

    OverallCondition(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
