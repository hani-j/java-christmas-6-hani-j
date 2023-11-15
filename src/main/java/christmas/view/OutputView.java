package christmas.view;

import static christmas.view.OutputMessage.AMOUNT;
import static christmas.view.OutputMessage.BENEFIT_DETAILS;
import static christmas.view.OutputMessage.DETAILS;
import static christmas.view.OutputMessage.EVENT_BADGE;
import static christmas.view.OutputMessage.GIVEAWAY_MENU;
import static christmas.view.OutputMessage.MINUS;
import static christmas.view.OutputMessage.NOTHING;
import static christmas.view.OutputMessage.ORDER_MENU;
import static christmas.view.OutputMessage.PREVIEW;
import static christmas.view.OutputMessage.TOTAL_AMOUNT;
import static christmas.view.OutputMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.OutputMessage.TOTAL_DISCOUNTED_AMOUNT;
import static christmas.view.OutputMessage.WELCOME_MESSAGE;
import static christmas.view.OutputMessage.WON;
import static christmas.view.OutputMessage.WON_FORMAT;

import java.util.Map;

public class OutputView {

    private OutputView() {
        
    }

    public static OutputView create() {
        return new OutputView();
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE.getMessage());
    }

    public void printPreview(final int day) {
        System.out.printf(PREVIEW.getMessage(), day);
        System.out.println();
    }

    public void printOrderMenu(final Map<String, Integer> orders) {
        System.out.println(ORDER_MENU.getMessage());
        orders.entrySet().stream().forEach(order -> {
            System.out.printf(AMOUNT.getMessage(), order.getKey(), order.getValue());
        });
        System.out.println();
    }

    public void printTotalAmount(final int amount) {
        System.out.println(TOTAL_AMOUNT.getMessage());
        System.out.printf(WON.getMessage(), getWon(amount));
        System.out.println();
    }

    private String getWon(final int amount) {
        return String.format(WON_FORMAT.getMessage(), amount);
    }

    public void printGiveawayMenu(final String giveaway) {
        System.out.println(GIVEAWAY_MENU.getMessage());
        System.out.println(giveaway);
        System.out.println();
    }

    public void printBenefitDetails(final Map<String, Integer> discountDetails) {
        System.out.println(BENEFIT_DETAILS.getMessage());
        if (discountDetails.size() == 0) {
            System.out.println(NOTHING.getMessage());
        }
        discountDetails.entrySet().stream()
                .forEach(detail -> System.out.printf(DETAILS.getMessage(), detail.getKey(), getWon(detail.getValue())));
        System.out.println();
    }

    public void printTotalBenefitAmount(final int amount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT.getMessage());
        if (amount != 0) {
            System.out.printf(MINUS.getMessage());
        }
        System.out.printf(WON.getMessage(), getWon(amount));
        System.out.println();
    }

    public void printTotalDiscountedAmount(final int amount) {
        System.out.println(TOTAL_DISCOUNTED_AMOUNT.getMessage());
        System.out.printf(WON.getMessage(), getWon(amount));
        System.out.println();
    }

    public void printEventBadge(final String badge) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(badge);
    }
}
