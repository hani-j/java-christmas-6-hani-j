package christmas;

import java.util.EnumMap;
import java.util.List;

public class Menu {
    private final EnumMap<Category, List<Product>> products;

    public Menu() {
        products = new EnumMap<>(Category.class);
        initMenu();
    }

    private void initMenu() {
        initAppetizer();
        initMain();
        initDessert();
        initBeverage();
    }

    private void initAppetizer() {
        products.put(
                Category.APPETIZER,
                List.of(new Product("양송이수프", 6000),
                        new Product("타파스", 5500),
                        new Product("시저샐러드", 8000)));
    }

    private void initMain() {
        products.put(
                Category.MAIN,
                List.of(new Product("티본스테이크", 55_000),
                        new Product("바비큐립", 54_000),
                        new Product("해산물파스타", 35_000),
                        new Product("크리스마스파스타", 25_000)));
    }

    private void initDessert() {
        products.put(
                Category.DESSERT,
                List.of(new Product("초코케이크", 15_000),
                        new Product("아이스크림", 5_000)));
    }

    private void initBeverage() {
        products.put(
                Category.BEVERAGE,
                List.of(new Product("제로콜라", 3_000),
                        new Product("레드와인", 60_000),
                        new Product("샴페인", 25_000)));
    }

    public void validateNameInMenu(String name) {
        boolean isNameInMenu = products.values().stream()
                .anyMatch(product ->
                        product.stream().anyMatch(food -> food.isSameName(name)));
        if (isNameInMenu == false) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
