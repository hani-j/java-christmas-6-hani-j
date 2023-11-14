package christmas;

import christmas.controller.EventController;
import christmas.domain.OrderHistory;
import christmas.domain.event.BadgeCalculator;
import christmas.domain.event.ChristmasEvent;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.DiscountDetails;
import christmas.domain.menu.Menu;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final ChristmasEvent christmasEvent = new ChristmasEvent(
                new DiscountDetails(),
                new DiscountCalculator(),
                new BadgeCalculator()
        );

        final EventService eventService = new EventService(
                new Menu(),
                new OrderHistory(),
                christmasEvent
        );

        final EventController eventController = new EventController(
                new InputView(),
                new OutputView(),
                eventService
        );

        eventController.run();
    }
}
