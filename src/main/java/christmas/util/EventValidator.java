package christmas.util;

import static christmas.domain.ErrorMessage.INVALID_DAY;
import static christmas.domain.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.Category.BEVERAGE;
import static christmas.util.EventPattern.MENU_PATTERN;
import static christmas.util.EventPattern.NUMBER_PATTERN;
import static christmas.util.EventPattern.ORDER_PATTERN;
import static christmas.util.EventRule.FIRST_DAY;
import static christmas.util.EventRule.INIT;
import static christmas.util.EventRule.LAST_DAY;
import static christmas.util.EventRule.MAXIMUM_QUANTITY;
import static christmas.util.EventRule.MENU;
import static christmas.util.EventRule.MINIMUM_QUANTITY;
import static christmas.util.EventRule.QUANTITY;

import christmas.domain.menu.MenuItem;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class EventValidator {


    public static void validateDay(String number) {
        try {
            validateNumber(number);
            validateNumberSize(number);
            validateInRange(number);
        } catch (Exception exception) {
            throw new IllegalArgumentException(INVALID_DAY.getMessage());
        }
    }

    private static void validateNumber(String number) {
        if (!NUMBER_PATTERN.matcher(number).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateNumberSize(final String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateInRange(String number) {
        int day = Integer.parseInt(number);

        if (day < FIRST_DAY.getValue() || day > LAST_DAY.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOrder(String order) {
        try {
            validateOrderFormat(order);
            validateMenuAmount(order);
            validateMenuDuplicate(order);
            validateOnlyBeverage(order);
            validateQuantity(order);
        } catch (Exception exception) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validateOrderFormat(String order) {
        if (!ORDER_PATTERN.matcher(order).matches()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateMenuAmount(String order) {
        Matcher orderMatcher = MENU_PATTERN.matcher(order);

        while (orderMatcher.find()) {
            String amount = orderMatcher.group(QUANTITY.getValue());
            validateAmountRange(amount);
        }
    }

    private static void validateAmountRange(String number) {
        validateNumber(number);
        validateNumberSize(number);
        validateMenuRange(number);
    }

    private static void validateMenuRange(String number) {
        int amount = Integer.parseInt(number);
        if (amount < MINIMUM_QUANTITY.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateMenuDuplicate(String order) {
        Matcher orderMatcher = MENU_PATTERN.matcher(order);
        Set<String> menus = new HashSet<>();
        int menuCount = INIT.getValue();

        while (orderMatcher.find()) {
            String menu = orderMatcher.group(MENU.getValue());
            menus.add(menu);
            menuCount++;
        }
        if (menuCount != menus.size()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOnlyBeverage(String order) {
        Matcher orderMatcher = MENU_PATTERN.matcher(order);
        boolean onlyBeverage = true;

        while (orderMatcher.find()) {
            String orderMenu = orderMatcher.group(MENU.getValue());
            if (MenuItem.getCategoryByName(orderMenu) != BEVERAGE) {
                onlyBeverage = false;
            }
        }
        if (onlyBeverage) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateQuantity(String order) {
        Matcher orderMatcher = MENU_PATTERN.matcher(order);
        int sum = INIT.getValue();

        while (orderMatcher.find()) {
            String orderMenu = orderMatcher.group(QUANTITY.getValue());
            sum += Integer.parseInt(orderMenu);
        }
        if (sum > MAXIMUM_QUANTITY.getValue()) {
            throw new IllegalArgumentException();
        }
    }
}