package christmas;

public enum DayType {
    WEEKDAY(2023, false),
    WEEKEND(2023, false),
    SPECIAL_DAY(2023, true);

    private final int discountPrice;
    private final boolean hasSpecial;

    DayType(final int discountPrice, final boolean hasSpecial) {
        this.discountPrice = discountPrice;
        this.hasSpecial = hasSpecial;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
