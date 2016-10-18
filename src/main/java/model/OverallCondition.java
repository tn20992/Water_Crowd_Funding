package model;

/**
 * Created by Bharath on 10/18/16.
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
