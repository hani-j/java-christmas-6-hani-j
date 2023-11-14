package christmas.domain.event;

import static christmas.domain.event.DayType.SPECIAL_DAY;
import static christmas.domain.event.DayType.WEEKDAY;
import static christmas.domain.event.DayType.WEEKEND;
import static christmas.domain.event.DayType.getDayType;
import static christmas.domain.event.EventMessage.CHAMPAGNE;
import static christmas.domain.event.EventMessage.D_DAY_DISCOUNT;
import static christmas.domain.event.EventMessage.GIVEAWAY_EVENT;
import static christmas.domain.event.EventMessage.NOTHING;
import static christmas.domain.event.EventMessage.SANTA_BADGE;
import static christmas.domain.event.EventMessage.SPECIAL_DISCOUNT;
import static christmas.domain.event.EventMessage.STAR_BADGE;
import static christmas.domain.event.EventMessage.TREE_BADGE;
import static christmas.domain.event.EventMessage.WEEKDAY_DISCOUNT;
import static christmas.domain.event.EventMessage.WEEKEND_DISCOUNT;
import static christmas.domain.event.EventValue.D_DAY_ADD_AMOUNT;
import static christmas.domain.event.EventValue.D_DAY_DIFFERENCE;
import static christmas.domain.event.EventValue.D_DAY_DISCOUNT_AMOUNT;
import static christmas.domain.event.EventValue.D_DAY_TARGET;
import static christmas.domain.event.EventValue.EVENT_TARGET;
import static christmas.domain.event.EventValue.GIVEAWAY_AMOUNT;
import static christmas.domain.event.EventValue.GIVEAWAY_TARGET;

import christmas.domain.OrderHistory;
import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChristmasEvent {

    private final Map<String, Integer> discountDetails;

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
            discountDetails.put(D_DAY_DISCOUNT.getMessage(), getDDayDiscountAmount(day));
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
            discountDetails.put(WEEKDAY_DISCOUNT.getMessage(), getWeekdayDisCountAmount(menu, orderHistory));
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
            discountDetails.put(WEEKEND_DISCOUNT.getMessage(), getWeekendDisCountAmount(menu, orderHistory));
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
            discountDetails.put(SPECIAL_DISCOUNT.getMessage(), SPECIAL_DAY.getDiscountPrice());
        }
    }

    public final boolean isSpecialDayDiscount(final int day) {
        return getDayType(day) == SPECIAL_DAY;
    }

    public void applyGiveawayDiscount(final Menu menu, final OrderHistory orderHistory) {
        if (isGiveawayTarget(orderHistory.getTotalAmount(menu))) {
            discountDetails.put(GIVEAWAY_EVENT.getMessage(), GIVEAWAY_AMOUNT.getValue());
        }
    }

    public final String getGiveaway(final Menu menu, final OrderHistory orderHistory) {
        if (isGiveawayTarget(orderHistory.getTotalAmount(menu))) {
            return CHAMPAGNE.getMessage();
        }
        return NOTHING.getMessage();
    }

    public final boolean isGiveawayTarget(final int amount) {
        return amount >= GIVEAWAY_TARGET.getValue();
    }

    public final int getTotalBenefitAmount() {
        return discountDetails.entrySet().stream()
                .mapToInt(discount -> discount.getValue())
                .sum();
    }

    public final int getTotalDiscountAmount() {
        return discountDetails.entrySet().stream()
                .filter(discount -> !discount.getKey().equals(GIVEAWAY_EVENT.getMessage()))
                .mapToInt(discount -> discount.getValue())
                .sum();
    }

    public final int getTotalDisCountedAmount(final Menu menu, final OrderHistory orderHistory) {
        return orderHistory.getTotalAmount(menu) - getTotalDiscountAmount();
    }

    public final String getEventBadge() {
        final int discountAmount = getTotalBenefitAmount();
        if (discountAmount >= 5000 && discountAmount < 10_000) {
            return STAR_BADGE.getMessage();
        }
        if (discountAmount >= 10_000 && discountAmount < 20_000) {
            return TREE_BADGE.getMessage();
        }
        if (discountAmount >= 20_000) {
            return SANTA_BADGE.getMessage();
        }
        return NOTHING.getMessage();
    }

    public final Map<String, Integer> getDiscountDetails() {
        Map<String, Integer> copyDiscountDetails = new HashMap<>(discountDetails);
        return Collections.unmodifiableMap(copyDiscountDetails);
    }
}
