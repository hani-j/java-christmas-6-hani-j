package christmas;

import christmas.controller.EventController;
import christmas.domain.OrderHistory;
import christmas.domain.menu.Menu;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventService eventService = new EventService(
                new Menu(),
                new OrderHistory()
        );

        EventController eventController = new EventController(
                new InputView(),
                new OutputView(),
                eventService
        );

        eventController.run();
    }
}
