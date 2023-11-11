package christmas;

import static christmas.DayType.SPECIAL_DAY;
import static christmas.DayType.WEEKDAY;
import static christmas.DayType.WEEKEND;
import static christmas.DayType.getDayType;

import java.util.Map;

public class ChristmasEvent {

    private Map<String, Integer> discountDetails;

    public final int getDDayDiscountAmount(final int day) {
        if (isDDayDiscount(day)) {
            return 1000 + (day - 1) * 100;
        }
        return 0;
    }

    public final int getWeekdayDisCountAmount(final Menu menu, final OrderHistory orderHistory, final int day) {
        if (isWeekdayDiscount(day)) {
            return orderHistory.getDessertCount(menu) * WEEKDAY.getDiscountPrice();
        }
        return 0;
    }

    public final boolean isEventTarget(final int amount) {
        return amount < 10_000;
    }

    public final boolean isDDayDiscount(final int day) {
        return day <= 25;
    }

    public final boolean isWeekdayDiscount(final int day) {
        return getDayType(day) == WEEKDAY || getDayType(day) == SPECIAL_DAY;
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
