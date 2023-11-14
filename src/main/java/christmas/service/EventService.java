package christmas.service;

import static christmas.util.EventPattern.MENU_PATTERN;
import static christmas.util.EventRule.MENU;
import static christmas.util.EventRule.QUANTITY;

import christmas.domain.OrderHistory;
import christmas.domain.event.ChristmasEvent;
import christmas.domain.menu.Menu;
import java.util.Map;
import java.util.regex.Matcher;

public class EventService {
    private final Menu menu;
    private final OrderHistory orderHistory;
    private final ChristmasEvent christmasEvent;

    public EventService(final Menu menu, final OrderHistory orderHistory, ChristmasEvent christmasEvent) {
        this.menu = menu;
        this.orderHistory = orderHistory;
        this.christmasEvent = christmasEvent;
    }

    public void setOrder(final int day, final String order) {
        orderParser(order);
        christmasEvent.applyDiscount(menu, orderHistory, day);
    }

    private void orderParser(final String order) {
        final Matcher orderMatcher = MENU_PATTERN.matcher(order);

        while (orderMatcher.find()) {
            final String name = orderMatcher.group(MENU.getValue());
            final String quantity = orderMatcher.group(QUANTITY.getValue());
            orderHistory.addOrder(menu, name, Integer.parseInt(quantity));
        }
    }

    public final Map<String, Integer> getOrders() {
        return orderHistory.getOrders();
    }

    public final int getTotalAmount() {
        return orderHistory.getTotalAmount(menu);
    }

    public final String getGiveaway() {
        return christmasEvent.getGiveaway(menu, orderHistory);
    }

    public final Map<String, Integer> getDiscountDetails() {
        return christmasEvent.getDiscountDetails();
    }

    public final int getTotalBenefitAmount() {
        return christmasEvent.getTotalBenefitAmount();
    }

    public final int getTotalDisCountedAmount() {
        return christmasEvent.getTotalDisCountedAmount(menu, orderHistory);
    }

    public final String getEventBadge() {
        return christmasEvent.getEventBadge();
    }
}
