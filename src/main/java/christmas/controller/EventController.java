package christmas.controller;

import static christmas.util.EventPattern.MENU_PATTERN;
import static christmas.util.EventRule.MENU;
import static christmas.util.EventRule.QUANTITY;

import christmas.domain.OrderHistory;
import christmas.domain.event.ChristmasEvent;
import christmas.domain.menu.Menu;
import christmas.util.EventValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.regex.Matcher;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;
    Menu menu = new Menu();
    OrderHistory orderHistory = new OrderHistory();

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();

        int day = getDay();
        setOrder();

        outputView.printPreview(day);
        printOrderHistory(day);
        printEventDetails(day);
    }

    public void printOrderHistory(int day) {
        outputView.printOrderMenu(orderHistory.getOrders());
        outputView.printTotalAmount(orderHistory.getTotalAmount(menu));
    }

    public void printEventDetails(int day) {
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
            EventValidator.validateDay(day);
            return Integer.parseInt(day);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getDay();
        }
    }

    public void setOrder() {
        try {
            String order = inputView.inputOrder();
            EventValidator.validateOrder(menu, order);
            orderParser(order);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            setOrder();
        }
    }

    public void orderParser(String order) {
        Matcher orderMatcher = MENU_PATTERN.matcher(order);

        while (orderMatcher.find()) {
            String name = orderMatcher.group(MENU.getValue());
            String quantity = orderMatcher.group(QUANTITY.getValue());
            orderHistory.addOrder(menu, name, Integer.parseInt(quantity));
        }
    }
}
