package christmas;

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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public Integer getAllQuantity() {
        return orders.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
