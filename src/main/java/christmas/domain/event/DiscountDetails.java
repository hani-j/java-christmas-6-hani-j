package christmas.domain.event;

import static christmas.domain.event.EventMessage.GIVEAWAY_EVENT;

import christmas.domain.OrderHistory;
import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DiscountDetails {
    private final Map<String, Integer> discountDetails;

    public DiscountDetails() {
        this.discountDetails = new HashMap<>();
    }

    public void addDiscount(final EventMessage eventMessage, final int discountAmount) {
        discountDetails.put(eventMessage.getMessage(), discountAmount);
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

    public final Map<String, Integer> getDiscountDetails() {
        return Collections.unmodifiableMap(discountDetails);
    }
}
