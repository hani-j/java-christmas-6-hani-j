package christmas.view;

import static christmas.view.OutputMessage.AMOUNT;
import static christmas.view.OutputMessage.ORDER_MENU;
import static christmas.view.OutputMessage.PREVIEW;

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
}
