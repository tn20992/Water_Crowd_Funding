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

    ConditionOfWater(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
