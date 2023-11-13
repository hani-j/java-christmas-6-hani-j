package christmas.view;

import static christmas.view.OutputMessage.AMOUNT;
import static christmas.view.OutputMessage.BENEFIT_DETAILS;
import static christmas.view.OutputMessage.CHAMPAGNE;
import static christmas.view.OutputMessage.DETAILS;
import static christmas.view.OutputMessage.GIVEAWAY_MENU;
import static christmas.view.OutputMessage.ORDER_MENU;
import static christmas.view.OutputMessage.PREVIEW;
import static christmas.view.OutputMessage.TOTAL_AMOUNT;

import java.util.Map;

public class OutputView {

    public void printPreview() {
        System.out.println(PREVIEW);
    }

    public void printOrderMenu(Map<String, Integer> orders) {
        System.out.println(ORDER_MENU.getMessage());
        orders.entrySet().stream().forEach(order -> {
            System.out.printf(AMOUNT.getMessage(), order.getKey(), order.getValue());
        });
    }

    public void printTotalAmount(int amount) {
        System.out.println(TOTAL_AMOUNT.getMessage());
        System.out.println(amount);
    }

    public void printGiveawayMenu(String menu) {
        System.out.println(GIVEAWAY_MENU.getMessage());
        System.out.println(CHAMPAGNE);
    }

    public void printBenefitDetails(Map<String, Integer> discountDetails) {
        System.out.println(BENEFIT_DETAILS.getMessage());
        discountDetails.entrySet().stream()
                .forEach(detail -> System.out.printf(DETAILS.getMessage(), detail.getKey(), detail.getValue()));
    }
}
