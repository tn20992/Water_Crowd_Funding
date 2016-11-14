package model;

/**
 * Condition of Water enum
 * Stores Types Waste, Treatable-Clear, Treatable-Muddy, Potable
 */
public enum ConditionOfWater {
    WASTE          ("Waste"),
    TREATABLECLEAR ("Treatable-Clear"),
    TREATABLEMUDDY ("Treatable-Muddy"),
    POTABLE        ("Potable");


    private final String value;

    /*
     * Creates a new condition of water
     * @param value for the condition of water
     */
    ConditionOfWater(String value) {
        this.value = value;
    }

    /**
     *
     * @return the description of the condition of water
     */
    @Override
    public String toString() {
        return value;
    }
}
