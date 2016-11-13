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

    /*
     * Creates a new type of water
     * @param value for the type of water
     */
    TypeOfWater(String value) {
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
