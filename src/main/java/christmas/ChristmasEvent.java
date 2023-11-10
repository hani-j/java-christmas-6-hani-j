package christmas;

import static christmas.DayType.SPECIAL_DAY;
import static christmas.DayType.WEEKDAY;
import static christmas.DayType.WEEKEND;
import static christmas.DayType.getDayType;

public class ChristmasEvent {

    public boolean isDDayDiscount(int day) {
        return day <= 25;
    }

    public boolean isWeekdayDiscount(int day) {
        return getDayType(day) == WEEKDAY;
    }

    public boolean isWeekendDiscount(int day) {
        return getDayType(day) == WEEKEND;
    }

    public boolean isSpecialDayDiscount(int day) {
        return getDayType(day) == SPECIAL_DAY;
    }

    public boolean isGiveawayTarget(int amount) {
        return amount >= 120_000;
    }
}
