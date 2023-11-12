package christmas.domain;

import static christmas.domain.event.DayType.WEEKDAY;
import static christmas.domain.event.DayType.WEEKEND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.ChristmasEvent;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasEventTotalTest {

    private Menu menu;
    private OrderHistory orderHistory;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        orderHistory = new OrderHistory();
    }

    @DisplayName("평일 날짜가 들어왔을 때 총 혜택 금액을 반환한다.")
    @Test
    public void getTotalDisCountAmount() {
        // given
        int day = 4;
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 2);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 4);
        ChristmasEvent christmasEvent = new ChristmasEvent(menu, orderHistory, day);

        // when
        int totalDisCountAmount = christmasEvent.getTotalBenefitAmount();

        // then
        int expected = 1300 + 3 * WEEKDAY.getDiscountPrice() + 25000;
        assertEquals(expected, totalDisCountAmount);
    }

    @DisplayName("주말 날짜가 들어왔을 때 총 혜택 금액을 반환한다.")
    @Test
    public void getWeekendTotalDisCountAmount() {
        // given
        int day = 15;
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 2);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 4);
        ChristmasEvent christmasEvent = new ChristmasEvent(menu, orderHistory, day);

        // when
        int totalDisCountAmount = christmasEvent.getTotalBenefitAmount();

        // then
        int expected = 2400 + 2 * WEEKEND.getDiscountPrice() + 25000;
        assertEquals(expected, totalDisCountAmount);
    }

    @DisplayName("특별일 날짜가 들어왔을 때 총 혜택 금액을 반환한다.")
    @Test
    public void getSpecialDayTotalDisCountAmount() {
        // given
        int day = 3;
        orderHistory.addOrder(menu, "티본스테이크", 1);
        orderHistory.addOrder(menu, "바비큐립", 1);
        orderHistory.addOrder(menu, "초코케이크", 2);
        orderHistory.addOrder(menu, "제로콜라", 1);
        ChristmasEvent christmasEvent = new ChristmasEvent(menu, orderHistory, day);

        // when
        int totalDisCountAmount = christmasEvent.getTotalBenefitAmount();

        // then
        int expected = 31_246;
        assertEquals(expected, totalDisCountAmount);
    }

    @DisplayName("할인 후 예상 결제 금액을 반환한다.")
    @Test
    public void getTotalDisCountedAmount() {
        // given
        int day = 3;
        orderHistory.addOrder(menu, "티본스테이크", 1);
        orderHistory.addOrder(menu, "바비큐립", 1);
        orderHistory.addOrder(menu, "초코케이크", 2);
        orderHistory.addOrder(menu, "제로콜라", 1);
        ChristmasEvent christmasEvent = new ChristmasEvent(menu, orderHistory, day);

        // when
        int totalDisCountedAmount = christmasEvent.getTotalDisCountedAmount(menu, orderHistory);

        // then
        int expected = 135_754;
        assertEquals(expected, totalDisCountedAmount);
    }
}
