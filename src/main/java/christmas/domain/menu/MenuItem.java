package christmas.domain.menu;

import static christmas.domain.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.Category.APPETIZER;
import static christmas.domain.menu.Category.BEVERAGE;
import static christmas.domain.menu.Category.DESSERT;
import static christmas.domain.menu.Category.MAIN;

import java.util.Arrays;
import java.util.List;

public enum MenuItem {
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    T_BONE_STAKE(MAIN, "티본스테이크", 55_000),
    BARBECUE_LIBS(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),

    CHOCO_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_SCREAM(DESSERT, "아이스크림", 5_000),

    ZERO_COKE(BEVERAGE, "제로콜라", 3_000),
    RED_WINE(BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(BEVERAGE, "샴페인", 25_000);

    private final Category category;
    private final String name;
    private final int price;

    MenuItem(final Category category, final String name, final int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static final List<MenuItem> getAllValues() {
        return Arrays.asList(MenuItem.values());
    }

    public static final Category getCategoryByName(String name) {
        return getAllValues().stream()
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.getMessage()))
                .getCategory();
    }

    public final Category getCategory() {
        return category;
    }

    public final String getName() {
        return name;
    }

    public final int getPrice() {
        return price;
    }
}
