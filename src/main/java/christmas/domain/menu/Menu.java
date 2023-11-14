package christmas.domain.menu;

import static christmas.domain.ErrorMessage.INVALID_ORDER;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, Product> products;

    public Menu() {
        this.products = new HashMap<>();
        initMenu();
    }

    private void initMenu() {
        MenuItem.getAllValues().stream().forEach(menuItem -> {
            products.put(menuItem.getName(), new Product(menuItem.getCategory(), menuItem.getPrice()));
        });
    }

    private Product getProduct(final String name) {
        Product product = products.get(name);
        if (product == null) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
        return product;
    }

    public void validateNameInMenu(final String name) {
        getProduct(name);
    }

    public final Category getCategory(final String name) {
        return getProduct(name).category();
    }

    public final int getPrice(final String name) {
        return getProduct(name).price();
    }
}
