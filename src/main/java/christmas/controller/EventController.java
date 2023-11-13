package christmas.controller;

import christmas.service.EventService;
import christmas.util.EventValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;
    private final EventService eventService;

    public EventController(InputView inputView, OutputView outputView, EventService eventService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventService = eventService;
    }

    public void run() {
        outputView.printWelcomeMessage();

        int day = getDay();
        String order = getOrder();
        eventService.setOrder(day, order);

        printOrderHistory(day);
        printEventDetails(day);
    }

    public void printOrderHistory(int day) {
        outputView.printPreview(day);
        outputView.printOrderMenu(eventService.getOrders());
        outputView.printTotalAmount(eventService.getTotalAmount());
    }

    public void printEventDetails(int day) {
        outputView.printGiveawayMenu(eventService.getGiveaway());
        outputView.printBenefitDetails(eventService.getDiscountDetails());
        outputView.printTotalBenefitAmount(eventService.getTotalBenefitAmount());
        outputView.printTotalDiscountedAmount(eventService.getTotalDisCountedAmount());
        outputView.printEventBadge(eventService.getEventBadge());
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

    public String getOrder() {
        try {
            String order = inputView.inputOrder();
            EventValidator.validateOrder(order);
            return order;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getOrder();
        }
    }


}
