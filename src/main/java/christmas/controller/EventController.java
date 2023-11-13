package christmas.controller;

import christmas.domain.OrderHistory;
import christmas.domain.event.ChristmasEvent;
import christmas.domain.menu.Menu;
import christmas.util.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventController {
    private static final Pattern MENU = Pattern.compile("([가-힣]+)-(-?\\d+)");

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Menu menu = new Menu();
    OrderHistory orderHistory = new OrderHistory();

    public void run() {
        outputView.printWelcomeMessage();

        int day = getDay();
        setOrder();

        outputView.printPreview(day);
        outputView.printOrderMenu(orderHistory.getOrders());
        outputView.printTotalAmount(orderHistory.getTotalAmount(menu));

        ChristmasEvent christmasEvent = new ChristmasEvent(menu, orderHistory, day);
        outputView.printGiveawayMenu(christmasEvent.getGiveaway(menu, orderHistory));
        outputView.printBenefitDetails(christmasEvent.getDiscountDetails());
        outputView.printTotalBenefitAmount(christmasEvent.getTotalBenefitAmount());
        outputView.printTotalDiscountedAmount(christmasEvent.getTotalDisCountedAmount(menu, orderHistory));
        outputView.printEventBadge(christmasEvent.getEventBadge());
    }

    public int getDay() {
        try {
            String day = inputView.inputDay();
            Validator.validateDay(day);
            return Integer.parseInt(day);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getDay();
        }
    }

    public void setOrder() {
        try {
            String order = inputView.inputOrder();
            Validator.validateOrder(menu, order);
            orderParser(order);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            setOrder();
        }
    }

    public void orderParser(String order) {
        Matcher orderMatcher = MENU.matcher(order);

        while (orderMatcher.find()) {
            String name = orderMatcher.group(1);
            String quantity = orderMatcher.group(2);
            orderHistory.addOrder(menu, name, Integer.parseInt(quantity));
        }
    }
}
