package christmas.util;

public enum EventRule {
    INIT(0),
    MENU(1),
    QUANTITY(2),
    MINIMUM_QUANTITY(1),
    MAXIMUM_QUANTITY(20),
    FIRST_DAY(1),
    LAST_DAY(31),
    ;

    private final int value;

    EventRule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
