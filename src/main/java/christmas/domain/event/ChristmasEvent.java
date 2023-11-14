package christmas.domain.event;

import static christmas.domain.event.DayType.SPECIAL_DAY;
import static christmas.domain.event.DayType.WEEKDAY;
import static christmas.domain.event.DayType.WEEKEND;
import static christmas.domain.event.DayType.getDayType;
import static christmas.domain.event.EventMessage.CHAMPAGNE;
import static christmas.domain.event.EventMessage.D_DAY_DISCOUNT;
import static christmas.domain.event.EventMessage.GIVEAWAY_EVENT;
import static christmas.domain.event.EventMessage.NOTHING;
import static christmas.domain.event.EventMessage.SPECIAL_DISCOUNT;
import static christmas.domain.event.EventMessage.WEEKDAY_DISCOUNT;
import static christmas.domain.event.EventMessage.WEEKEND_DISCOUNT;
import static christmas.domain.event.EventValue.D_DAY_TARGET;
import static christmas.domain.event.EventValue.EVENT_TARGET;
import static christmas.domain.event.EventValue.GIVEAWAY_AMOUNT;
import static christmas.domain.event.EventValue.GIVEAWAY_TARGET;

import christmas.domain.OrderHistory;
import christmas.domain.menu.Menu;
import java.util.Map;

public class ChristmasEvent {

    private final DiscountDetails discountDetails;
    private final DiscountCalculator discountCalculator;
    private final BadgeCalculator badgeCalculator;

    public ChristmasEvent(final DiscountDetails discountDetails, final DiscountCalculator discountCalculator,
                          final BadgeCalculator badgeCalculator) {
        this.discountDetails = discountDetails;
        this.discountCalculator = discountCalculator;
        this.badgeCalculator = badgeCalculator;
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
            discountDetails.addDiscount(D_DAY_DISCOUNT, discountCalculator.calculateDDayDiscount(day));
        }
    }

    public final boolean isDDayDiscount(final int day) {
        return day <= D_DAY_TARGET.getValue();
    }


    public void applyWeekdayDiscount(final Menu menu, final OrderHistory orderHistory, final int day) {
        if (isWeekdayDiscount(day)) {
            discountDetails.addDiscount(WEEKDAY_DISCOUNT,
                    discountCalculator.calculateWeekdayDiscount(menu, orderHistory));
        }
    }

    public final boolean isWeekdayDiscount(final int day) {
        return getDayType(day) == WEEKDAY || getDayType(day) == SPECIAL_DAY;
    }

    public void applyWeekendDiscount(final Menu menu, final OrderHistory orderHistory, final int day) {
        if (isWeekendDiscount(day)) {
            discountDetails.addDiscount(WEEKEND_DISCOUNT,
                    discountCalculator.calculateWeekendDiscount(menu, orderHistory));
        }
    }

    public final boolean isWeekendDiscount(final int day) {
        return getDayType(day) == WEEKEND;
    }

    public void applySpecialDiscount(final int day) {
        if (isSpecialDayDiscount(day)) {
            discountDetails.addDiscount(SPECIAL_DISCOUNT, SPECIAL_DAY.getDiscountPrice());
        }
    }

    public final boolean isSpecialDayDiscount(final int day) {
        return getDayType(day) == SPECIAL_DAY;
    }

    public void applyGiveawayDiscount(final Menu menu, final OrderHistory orderHistory) {
        if (isGiveawayTarget(orderHistory.getTotalAmount(menu))) {
            discountDetails.addDiscount(GIVEAWAY_EVENT, GIVEAWAY_AMOUNT.getValue());
        }
    }

    public final boolean isGiveawayTarget(final int amount) {
        return amount >= GIVEAWAY_TARGET.getValue();
    }

    public final String getGiveaway(final Menu menu, final OrderHistory orderHistory) {
        if (isGiveawayTarget(orderHistory.getTotalAmount(menu))) {
            return CHAMPAGNE.getMessage();
        }
        return NOTHING.getMessage();
    }

    public final Map<String, Integer> getDiscountDetails() {
        return discountDetails.getDiscountDetails();
    }

    public final int getTotalBenefitAmount() {
        return discountDetails.getTotalBenefitAmount();
    }

    public final int getTotalDisCountedAmount(final Menu menu, final OrderHistory orderHistory) {
        return discountDetails.getTotalDisCountedAmount(menu, orderHistory);
    }

    public final String getEventBadge() {
        return badgeCalculator.calculateEventBadge(getTotalBenefitAmount());
    }
}
