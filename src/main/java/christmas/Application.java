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
        final ChristmasEvent christmasEvent = ChristmasEvent.of(
                DiscountDetails.create(), DiscountCalculator.create(), BadgeCalculator.create());

        final EventService eventService = EventService.of(
                Menu.create(), OrderHistory.create(), christmasEvent);

        final EventController eventController = EventController.of(
                InputView.create(), OutputView.create(), eventService);

        eventController.run();
    }


}
