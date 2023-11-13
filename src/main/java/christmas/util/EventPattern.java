package christmas.util;

import java.util.regex.Pattern;

public class EventPattern {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    public static final Pattern ORDER_PATTERN = Pattern.compile("^([가-힣]+-\\d,)*[가-힣]+-\\d$");
    public static final Pattern MENU_PATTERN = Pattern.compile("([가-힣]+)-(-?\\d+)");
}
