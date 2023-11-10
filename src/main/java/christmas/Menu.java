package christmas;

import static christmas.Category.APPETIZER;
import static christmas.Category.BEVERAGE;
import static christmas.Category.DESSERT;
import static christmas.Category.MAIN;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, Product> products;

    public Menu() {
        products = new HashMap<>();
        initMenu();
    }

    private void initMenu() {
        initAppetizer();
        initMain();
        initDessert();
        initBeverage();
    }

    private void initAppetizer() {
        products.put("양송이수프", new Product(APPETIZER, 6000));
        products.put("타파스", new Product(APPETIZER, 5500));
        products.put("시저샐러드", new Product(APPETIZER, 8000));
    }

    private void initMain() {
        products.put("티본스테이크", new Product(MAIN, 55_000));
        products.put("바비큐립", new Product(MAIN, 54_000));
        products.put("해산물파스타", new Product(MAIN, 35_000));
        products.put("크리스마스파스타", new Product(MAIN, 25_000));
    }

    private void initDessert() {
        products.put("초코케이크", new Product(DESSERT, 15_000));
        products.put("아이스크림", new Product(DESSERT, 5_000));
    }

    private void initBeverage() {
        products.put("제로콜라", new Product(BEVERAGE, 3_000));
        products.put("레드와인", new Product(BEVERAGE, 60_000));
        products.put("샴페인", new Product(BEVERAGE, 25_000));
    }

    public void validateNameInMenu(String name) {
        Product product = products.get(name);
        if (product == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public Category getCategory(String name) {
        Product product = products.get(name);
        if (product == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return product.category();
    }

}
