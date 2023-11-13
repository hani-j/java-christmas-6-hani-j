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
    Menu menu;
    OrderHistory orderHistory;
    ChristmasEvent christmasEvent;

    public EventService(Menu menu, OrderHistory orderHistory) {
        this.menu = menu;
        this.orderHistory = orderHistory;
    }

    public void setOrder(int day, String order) {
        orderParser(order);
        christmasEvent = new ChristmasEvent(menu, orderHistory, day);
    }

    public void orderParser(String order) {
        Matcher orderMatcher = MENU_PATTERN.matcher(order);

        while (orderMatcher.find()) {
            String name = orderMatcher.group(MENU.getValue());
            String quantity = orderMatcher.group(QUANTITY.getValue());
            orderHistory.addOrder(menu, name, Integer.parseInt(quantity));
        }
    }

    public Map<String, Integer> getOrders() {
        return orderHistory.getOrders();
    }

    public int getTotalAmount() {
        return orderHistory.getTotalAmount(menu);
    }

    public String getGiveaway() {
        return christmasEvent.getGiveaway(menu, orderHistory);
    }

    public Map<String, Integer> getDiscountDetails() {
        return christmasEvent.getDiscountDetails();
    }

    public int getTotalBenefitAmount() {
        return christmasEvent.getTotalBenefitAmount();
    }

    public int getTotalDisCountedAmount() {
        return christmasEvent.getTotalDisCountedAmount(menu, orderHistory);
    }

    public String getEventBadge() {
        return christmasEvent.getEventBadge();
    }
}
