package christmas.domain;

import static christmas.domain.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.Category.DESSERT;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.Map;

public class OrderHistory {
    private final Map<String, Integer> orders;

    public OrderHistory() {
        this.orders = new HashMap<>();
    }

    public void addOrder(Menu menu, String name, Integer quantity) {
        menu.validateNameInMenu(name);
        validateOrder(menu, name, quantity);
        orders.put(name, quantity);
    }

    private void validateOrder(Menu menu, String name, Integer quantity) {
        menu.validateNameInMenu(name);
        if (getAllQuantity() + quantity > 20) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public Integer getAllQuantity() {
        return orders.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalAmount(Menu menu) {
        return orders.entrySet().stream()
                .mapToInt(order -> menu.getPrice(order.getKey()) * order.getValue())
                .sum();
    }

    public int getDessertCount(Menu menu) {
        return orders.entrySet().stream()
                .filter(order -> menu.getCategory(order.getKey()) == DESSERT)
                .mapToInt(order -> order.getValue())
                .sum();
    }

    public int getMainCount(Menu menu) {
        return orders.entrySet().stream()
                .filter(order -> menu.getCategory(order.getKey()) == Category.MAIN)
                .mapToInt(order -> order.getValue())
                .sum();
    }

    // 보류
    public Map<String, Integer> getOrders() {
        return orders;
    }
}
