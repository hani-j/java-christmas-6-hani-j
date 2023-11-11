package christmas;

import static christmas.Category.DESSERT;
import static christmas.ErrorMessage.INVALID_ORDER;

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
}
