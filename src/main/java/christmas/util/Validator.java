package christmas.util;

import static christmas.domain.ErrorMessage.INVALID_DAY;
import static christmas.domain.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.Category.BEVERAGE;

import christmas.domain.menu.Menu;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NUMBER = Pattern.compile("[0-9]+");
    private static final Pattern ORDER = Pattern.compile("^([가-힣]+-\\d,)*[가-힣]+-\\d$");
    private static final Pattern MENU = Pattern.compile("([가-힣]+)-(-?\\d+)");

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
        if (!NUMBER.matcher(number).matches()) {
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

        if (day < 1 || day > 31) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOrder(Menu menu, String order) {
        try {
            validateOrderFormat(order);
            validateMenuAmount(order);
            validateMenuDuplicate(order);
            validateOnlyBeverage(menu, order);
        } catch (Exception exception) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validateOrderFormat(String order) {
        if (!ORDER.matcher(order).matches()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateMenuAmount(String order) {
        Matcher orderMatcher = MENU.matcher(order);

        while (orderMatcher.find()) {
            String amount = orderMatcher.group(2);
            validateAmountRange(amount);
        }
    }

    private static void validateAmountRange(String number) {
        validateNumber(number);
        validateNumberSize(number);
        validateRange(number);
    }

    private static void validateRange(String number) {
        int amount = Integer.parseInt(number);
        if (amount < 1) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateMenuDuplicate(String order) {
        Matcher orderMatcher = MENU.matcher(order);
        Set<String> menus = new HashSet<>();
        int menuCount = 0;

        while (orderMatcher.find()) {
            String menu = orderMatcher.group(1);
            menus.add(menu);
            menuCount++;
        }
        if (menuCount != menus.size()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOnlyBeverage(Menu menu, String order) {
        Matcher orderMatcher = MENU.matcher(order);

        while (orderMatcher.find()) {
            String orderMenu = orderMatcher.group(1);
            if (menu.getCategory(orderMenu) != BEVERAGE) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}