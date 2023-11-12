package christmas;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NUMBER = Pattern.compile("[0-9]+");

    public boolean validateNumber(String number) {
        return NUMBER.matcher(number).matches();
    }
}