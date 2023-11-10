package christmas;

import static christmas.DayType.SPECIAL_DAY;
import static christmas.DayType.WEEKDAY;
import static christmas.DayType.WEEKEND;
import static christmas.DayType.getDayType;

public class ChristmasEvent {
    public final boolean isEventTarget(final int amount) {
        return amount >= 10_000;
    }

    public final boolean isDDayDiscount(final int day) {
        return day <= 25;
    }

    public final boolean isWeekdayDiscount(final int day) {
        return getDayType(day) == WEEKDAY;
    }

    public final boolean isWeekendDiscount(final int day) {
        return getDayType(day) == WEEKEND;
    }

    public final boolean isSpecialDayDiscount(final int day) {
        return getDayType(day) == SPECIAL_DAY;
    }

    public final boolean isGiveawayTarget(final int amount) {
        return amount >= 120_000;
    }
}
