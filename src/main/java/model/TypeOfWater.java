package model;

/**
 * Type of Water enum
 * Stores Types Bottled, Well, Stream, Lake, Spring, and Other
 */
public enum TypeOfWater {
    BOTTLED ("Bottled"),
    WELL    ("Well"),
    STREAM  ("Stream"),
    LAKE    ("Lake"),
    SPRING  ("Spring"),
    OTHER   ("Other");

    private final String value;

    TypeOfWater(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
