package christmas.controller;

import christmas.service.EventService;
import christmas.util.EventValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;
    private final EventService eventService;

    private EventController(final InputView inputView, final OutputView outputView, final EventService eventService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventService = eventService;
    }

    public static EventController of(final InputView inputView, final OutputView outputView,
                                     final EventService eventService) {
        return new EventController(inputView, outputView, eventService);
    }

    public void run() {
        setEvent();
        showBenefitPreview();
    }

    public void setEvent() {
        outputView.printWelcomeMessage();

        final int day = getDay();
        eventService.setOrder(day, getOrder());

        outputView.printPreview(day);
    }

    public void showBenefitPreview() {
        printOrderHistory();
        printEventDetails();
    }

    public void printOrderHistory() {
        outputView.printOrderMenu(eventService.getOrders());
        outputView.printTotalAmount(eventService.getTotalAmount());
    }

    public void printEventDetails() {
        outputView.printGiveawayMenu(eventService.getGiveaway());
        outputView.printBenefitDetails(eventService.getDiscountDetails());
        outputView.printTotalBenefitAmount(eventService.getTotalBenefitAmount());
        outputView.printTotalDiscountedAmount(eventService.getTotalDisCountedAmount());
        outputView.printEventBadge(eventService.getEventBadge());
    }

    public final int getDay() {
        boolean isRepeat = true;
        String day;

        do {
            day = inputView.inputDay();
            try {
                EventValidator.validateDay(day);
                isRepeat = false;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        } while (isRepeat);
        return Integer.parseInt(day);
    }

    public final String getOrder() {
        boolean isRepeat = true;
        String order;

        do {
            order = inputView.inputOrder();
            try {
                EventValidator.validateOrder(order);
                isRepeat = false;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        } while (isRepeat);
        return order;
    }
}
