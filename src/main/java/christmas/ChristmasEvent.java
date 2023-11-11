package christmas;

import static christmas.DayType.SPECIAL_DAY;
import static christmas.DayType.WEEKDAY;
import static christmas.DayType.WEEKEND;
import static christmas.DayType.getDayType;

import java.util.HashMap;
import java.util.Map;

public class ChristmasEvent {

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
        return amount < 10_000;
    }

    public void applyDDayDiscount(final int day) {
        if (isDDayDiscount(day)) {
            discountDetails.put("크리스마스 디데이 할인", getDDayDiscountAmount(day));
        }
    }

    public final boolean isDDayDiscount(final int day) {
        return day <= 25;
    }

    public final int getDDayDiscountAmount(final int day) {
        return 1000 + (day - 1) * 100;
    }

    public void applyWeekdayDiscount(final Menu menu, final OrderHistory orderHistory, final int day) {
        if (isWeekdayDiscount(day)) {
            discountDetails.put("평일 할인", getWeekdayDisCountAmount(menu, orderHistory));
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
            discountDetails.put("주말 할인", getWeekendDisCountAmount(menu, orderHistory));
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
            discountDetails.put("특별 할인", 1000);
        }
    }

    public final boolean isSpecialDayDiscount(final int day) {
        return getDayType(day) == SPECIAL_DAY;
    }

    public void applyGiveawayDiscount(final Menu menu, final OrderHistory orderHistory) {
        if (isGiveawayTarget(orderHistory.getTotalAmount(menu))) {
            discountDetails.put("증정 이벤트", 25_000);
        }
    }

    public final boolean isGiveawayTarget(final int amount) {
        return amount >= 120_000;
    }

    public final int getTotalBenefitAmount() {
        return discountDetails.entrySet().stream()
                .mapToInt(discount -> discount.getValue())
                .sum();
    }

    public final int getTotalDisCountAmount() {
        return discountDetails.entrySet().stream()
                .filter(discount -> !discount.getKey().equals("증정 이벤트"))
                .mapToInt(discount -> discount.getValue())
                .sum();
    }

    public final int getTotalDisCountedAmount(final Menu menu, final OrderHistory orderHistory) {
        return orderHistory.getTotalAmount(menu) - getTotalDisCountAmount();
    }
}
