package christmas;

import static christmas.ErrorMessage.INVALID_DAY;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum DayType {
    WEEKDAY(new HashSet<>(Set.of(4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21, 26, 27, 28)), 2023, false),
    WEEKEND(new HashSet<>(Set.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)), 2023, false),
    SPECIAL_DAY(new HashSet<>(Set.of(3, 10, 17, 24, 25, 31)), 2023, true);

    private final Set<Integer> days;
    private final int discountPrice;
    private final boolean hasSpecial;

    DayType(final Set<Integer> days, final int discountPrice, final boolean hasSpecial) {
        this.days = days;
        this.discountPrice = discountPrice;
        this.hasSpecial = hasSpecial;
    }

    public static final DayType getDayType(final int day) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> dayType.getDays().contains(day))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY.getMessage()));
    }

    private Set<Integer> getDays() {
        return days;
    }

    public final int getDiscountPrice() {
        return discountPrice;
    }
}
