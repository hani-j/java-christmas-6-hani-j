package christmas.domain.event;

import static christmas.domain.event.DayType.SPECIAL_DAY;
import static christmas.domain.event.DayType.WEEKDAY;
import static christmas.domain.event.DayType.WEEKEND;
import static christmas.domain.event.DayType.getDayType;
import static christmas.domain.event.Event.D_DAY_ADD_AMOUNT;
import static christmas.domain.event.Event.D_DAY_DIFFERENCE;
import static christmas.domain.event.Event.D_DAY_DISCOUNT_AMOUNT;
import static christmas.domain.event.Event.D_DAY_TARGET;
import static christmas.domain.event.Event.EVENT_TARGET;
import static christmas.domain.event.Event.GIVEAWAY_AMOUNT;
import static christmas.domain.event.Event.GIVEAWAY_TARGET;

import christmas.domain.OrderHistory;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.Map;

public class ChristmasEvent {
    private final static String D_DAY_DISCOUNT = "크리스마스 디데이 할인";
    private final static String WEEKDAY_DISCOUNT = "평일 할인";
    private final static String WEEKEND_DISCOUNT = "주말 할인";
    private final static String SPECIAL_DISCOUNT = "특별 할인";
    private final static String GIVEAWAY_EVENT = "증정 이벤트";

    private Map<String, Integer> discountDetails;

    public ChristmasEvent(final Menu menu, final OrderHistory orderHistory, final int day) {
        discountDetails = new HashMap<>();
        applyDiscount(menu, orderHistory, day);
    }

    public void applyDiscount(final Menu menu, final OrderHistory orderHistory, final int day) {
        if (isNotEventTarget(orderHistory.getTotalAmount(menu))) {
            return;
        }
        applyDDayDiscount(day);
        applyWeekdayDiscount(menu, orderHistory, day);
        applyWeekendDiscount(menu, orderHistory, day);
        applySpecialDiscount(day);
        applyGiveawayDiscount(menu, orderHistory);
    }

    public final boolean isNotEventTarget(final int amount) {
        return amount < EVENT_TARGET.getValue();
    }

    public void applyDDayDiscount(final int day) {
        if (isDDayDiscount(day)) {
            discountDetails.put(D_DAY_DISCOUNT, getDDayDiscountAmount(day));
        }
    }

    public final boolean isDDayDiscount(final int day) {
        return day <= D_DAY_TARGET.getValue();
    }

    public final int getDDayDiscountAmount(final int day) {
        return D_DAY_DISCOUNT_AMOUNT.getValue() +
                (day - D_DAY_DIFFERENCE.getValue()) * D_DAY_ADD_AMOUNT.getValue();
    }

    public void applyWeekdayDiscount(final Menu menu, final OrderHistory orderHistory, final int day) {
        if (isWeekdayDiscount(day)) {
            discountDetails.put(WEEKDAY_DISCOUNT, getWeekdayDisCountAmount(menu, orderHistory));
        }
    }

    public final int getWeekdayDisCountAmount(final Menu menu, final OrderHistory orderHistory) {
        return orderHistory.getDessertCount(menu) * WEEKDAY.getDiscountPrice();
    }

    public final boolean isWeekdayDiscount(final int day) {
        return getDayType(day) == WEEKDAY || getDayType(day) == SPECIAL_DAY;
    }

    public void applyWeekendDiscount(final Menu menu, final OrderHistory orderHistory, final int day) {
        if (isWeekendDiscount(day)) {
            discountDetails.put(WEEKEND_DISCOUNT, getWeekendDisCountAmount(menu, orderHistory));
        }
    }

    public final boolean isWeekendDiscount(final int day) {
        return getDayType(day) == WEEKEND;
    }

    public final int getWeekendDisCountAmount(final Menu menu, final OrderHistory orderHistory) {
        return orderHistory.getMainCount(menu) * WEEKDAY.getDiscountPrice();
    }

    public void applySpecialDiscount(final int day) {
        if (isSpecialDayDiscount(day)) {
            discountDetails.put(SPECIAL_DISCOUNT, SPECIAL_DAY.getDiscountPrice());
        }
    }

    public final boolean isSpecialDayDiscount(final int day) {
        return getDayType(day) == SPECIAL_DAY;
    }

    public void applyGiveawayDiscount(final Menu menu, final OrderHistory orderHistory) {
        if (isGiveawayTarget(orderHistory.getTotalAmount(menu))) {
            discountDetails.put(GIVEAWAY_EVENT, GIVEAWAY_AMOUNT.getValue());
        }
    }

    public final boolean isGiveawayTarget(final int amount) {
        return amount >= GIVEAWAY_TARGET.getValue();
    }

    public final int getTotalBenefitAmount() {
        return discountDetails.entrySet().stream()
                .mapToInt(discount -> discount.getValue())
                .sum();
    }

    public final int getTotalDisCountAmount() {
        return discountDetails.entrySet().stream()
                .filter(discount -> !discount.getKey().equals(GIVEAWAY_EVENT))
                .mapToInt(discount -> discount.getValue())
                .sum();
    }

    public final int getTotalDisCountedAmount(final Menu menu, final OrderHistory orderHistory) {
        return orderHistory.getTotalAmount(menu) - getTotalDisCountAmount();
    }
}
