package christmas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NUMBER = Pattern.compile("[0-9]+");
    private static final Pattern ORDER = Pattern.compile("^([가-힣]+-\\d,)*[가-힣]+-\\d$");
    private static final Pattern MENU = Pattern.compile("([가-힣]+)-(-?\\d+)");

    public void validateDay(String number) {
        try {
            validateNumber(number);
            validateNumberSize(number);
            validateInRange(number);
        } catch (Exception exception) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNumber(String number) {
        if (!NUMBER.matcher(number).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNumberSize(final String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    private void validateInRange(String number) {
        int day = Integer.parseInt(number);

        if (day < 1 || day > 31) {
            throw new IllegalArgumentException();
        }
    }

    public void validateOrderFormat(String order) {
        if (!ORDER.matcher(order).matches()) {
            throw new IllegalArgumentException();
        }
    }

    public void validateMenuAmount(String order) {
        Matcher orderMatcher = MENU.matcher(order);

        while (orderMatcher.find()) {
            String amount = orderMatcher.group(2);
            validateAmountRange(amount);
        }
    }

    private void validateAmountRange(String number) {
        validateNumber(number);
        validateNumberSize(number);
        validateRange(number);
    }

    public void validateRange(String number) {
        int amount = Integer.parseInt(number);
        if (amount < 1) {
            throw new IllegalArgumentException();
        }
    }
}