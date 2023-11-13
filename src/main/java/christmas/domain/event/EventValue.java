package christmas.domain.event;

public enum EventValue {
    EVENT_TARGET(10_000),
    MAXIMUM_ORDER(20),
    D_DAY_TARGET(25),
    D_DAY_DISCOUNT_AMOUNT(1000),
    D_DAY_ADD_AMOUNT(100),
    D_DAY_DIFFERENCE(1),
    GIVEAWAY_TARGET(120_000),
    GIVEAWAY_AMOUNT(25_000),

    ;

    private final int value;

    EventValue(final int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }
}
