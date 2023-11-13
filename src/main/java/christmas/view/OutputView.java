package christmas.view;

import static christmas.view.OutputMessage.AMOUNT;
import static christmas.view.OutputMessage.ORDER_MENU;
import static christmas.view.OutputMessage.PREVIEW;
import static christmas.view.OutputMessage.TOTAL_AMOUNT;

import java.util.Map;

public class OutputView {

    public void printPreview() {
        System.out.println(PREVIEW);
    }

    public void printOrderMenu(Map<String, Integer> orders) {
        System.out.println(ORDER_MENU);
        orders.entrySet().stream().forEach(order -> {
            System.out.printf(AMOUNT.getMessage(), order.getKey(), order.getValue());
        });
    }

    public void printTotalAmount(int amount) {
        System.out.println(TOTAL_AMOUNT);
        System.out.println(amount);
    }
}
