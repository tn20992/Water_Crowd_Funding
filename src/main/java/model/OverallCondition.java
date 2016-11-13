package model;

/**
 * Overall Condition enum
 * Stores Types Safe, Treatable, Unsafe
 */
public enum OverallCondition {
    SAFE          ("Safe"),
    TREATABLE     ("Treatable"),
    UNSAFE        ("Unsafe");


    private final String value;

    /*
     * Creates a new overall condition
     * @param value for the overall condition
     */
    OverallCondition(String value) {
        this.value = value;
    }

    /**
     *
     * @return the description of the overall condition
     */
    @Override
    public String toString() {
        return value;
    }
}
