package christmas.domain;

import static christmas.domain.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.Category.DESSERT;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderHistory {
    private final Map<String, Integer> orders;

    private OrderHistory() {
        this.orders = new HashMap<>();
    }

    public static OrderHistory create() {
        return new OrderHistory();
    }

    public void addOrder(final Menu menu, final String name, final Integer quantity) {
        menu.validateNameInMenu(name);
        validateOrder(menu, name, quantity);
        orders.put(name, quantity);
    }

    private void validateOrder(final Menu menu, final String name, final Integer quantity) {
        menu.validateNameInMenu(name);
        if (getAllQuantity() + quantity > 20) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public final Integer getAllQuantity() {
        return orders.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public final int getTotalAmount(final Menu menu) {
        return orders.entrySet().stream()
                .mapToInt(order -> menu.getPrice(order.getKey()) * order.getValue())
                .sum();
    }

    public final int getDessertCount(final Menu menu) {
        return orders.entrySet().stream()
                .filter(order -> menu.getCategory(order.getKey()) == DESSERT)
                .mapToInt(order -> order.getValue())
                .sum();
    }

    public final int getMainCount(final Menu menu) {
        return orders.entrySet().stream()
                .filter(order -> menu.getCategory(order.getKey()) == Category.MAIN)
                .mapToInt(order -> order.getValue())
                .sum();
    }

    public final Map<String, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }
}
