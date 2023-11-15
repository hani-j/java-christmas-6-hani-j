package christmas.domain.event;

import static christmas.domain.event.DayType.WEEKDAY;
import static christmas.domain.event.DayType.WEEKEND;
import static christmas.domain.event.EventValue.D_DAY_ADD_AMOUNT;
import static christmas.domain.event.EventValue.D_DAY_DIFFERENCE;
import static christmas.domain.event.EventValue.D_DAY_DISCOUNT_AMOUNT;

import christmas.domain.OrderHistory;
import christmas.domain.menu.Menu;

public class DiscountCalculator {

    private DiscountCalculator() {
        
    }

    public static DiscountCalculator create() {
        return new DiscountCalculator();
    }

    public final int calculateDDayDiscount(final int day) {
        return D_DAY_DISCOUNT_AMOUNT.getValue() +
                (day - D_DAY_DIFFERENCE.getValue()) * D_DAY_ADD_AMOUNT.getValue();
    }

    public final int calculateWeekdayDiscount(final Menu menu, final OrderHistory orderHistory) {
        return orderHistory.getDessertCount(menu) * WEEKDAY.getDiscountPrice();
    }

    public final int calculateWeekendDiscount(final Menu menu, final OrderHistory orderHistory) {
        return orderHistory.getMainCount(menu) * WEEKEND.getDiscountPrice();
    }
}
