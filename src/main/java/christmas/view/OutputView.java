package christmas.view;

import static christmas.view.OutputMessage.AMOUNT;
import static christmas.view.OutputMessage.BENEFIT_DETAILS;
import static christmas.view.OutputMessage.CHAMPAGNE;
import static christmas.view.OutputMessage.DETAILS;
import static christmas.view.OutputMessage.EVENT_BADGE;
import static christmas.view.OutputMessage.GIVEAWAY_MENU;
import static christmas.view.OutputMessage.ORDER_MENU;
import static christmas.view.OutputMessage.PREVIEW;
import static christmas.view.OutputMessage.TOTAL_AMOUNT;
import static christmas.view.OutputMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.OutputMessage.TOTAL_DISCOUNTED_AMOUNT;
import static christmas.view.OutputMessage.WELCOME_MESSAGE;
import static christmas.view.OutputMessage.WON_FORMAT;

import java.util.Map;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE.getMessage());
    }

    public void printPreview() {
        System.out.println(PREVIEW.getMessage());
        System.out.println();
    }

    public void printOrderMenu(Map<String, Integer> orders) {
        System.out.println(ORDER_MENU.getMessage());
        orders.entrySet().stream().forEach(order -> {
            System.out.printf(AMOUNT.getMessage(), order.getKey(), order.getValue());
        });
        System.out.println();
    }

    public void printTotalAmount(int amount) {
        System.out.println(TOTAL_AMOUNT.getMessage());
        System.out.println(amount);
        System.out.println();
    }

    public void printGiveawayMenu(String menu) {
        System.out.println(GIVEAWAY_MENU.getMessage());
        System.out.println(CHAMPAGNE.getMessage());
        System.out.println();
    }

    public void printBenefitDetails(Map<String, Integer> discountDetails) {
        System.out.println(BENEFIT_DETAILS.getMessage());
        discountDetails.entrySet().stream()
                .forEach(detail -> System.out.printf(DETAILS.getMessage(), detail.getKey(), detail.getValue()));
        System.out.println();
    }

    public void printTotalDiscountAmount(int amount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.printf(String.format(WON_FORMAT.getMessage(), amount));
        System.out.println();
    }

    public void printTotalDiscountedAmount(int amount) {
        System.out.println(TOTAL_DISCOUNTED_AMOUNT.getMessage());
        System.out.println(String.format(WON_FORMAT.getMessage(), amount));
        System.out.println();
    }

    public void printEventBadge(String badge) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(badge);
    }
}
