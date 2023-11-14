package christmas.domain;

import static christmas.domain.event.DayType.SPECIAL_DAY;
import static christmas.domain.event.DayType.WEEKDAY;
import static christmas.domain.event.EventValue.D_DAY_ADD_AMOUNT;
import static christmas.domain.event.EventValue.D_DAY_DIFFERENCE;
import static christmas.domain.event.EventValue.D_DAY_DISCOUNT_AMOUNT;
import static christmas.domain.event.EventValue.GIVEAWAY_AMOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.BadgeCalculator;
import christmas.domain.event.ChristmasEvent;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.DiscountDetails;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasEventTotalTest {

    private Menu menu;
    private OrderHistory orderHistory;

    private ChristmasEvent christmasEvent;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        orderHistory = new OrderHistory();
        christmasEvent = new ChristmasEvent(
                new DiscountDetails(),
                new DiscountCalculator(),
                new BadgeCalculator());
    }

    @DisplayName("평일 날짜가 들어왔을 때 총 혜택 금액을 반환한다.")
    @Test
    public void getTotalDisCountAmount() {
        // given
        int day = 4;
        int dessertQuantity = 3;
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 2);
        orderHistory.addOrder(menu, "초코케이크", dessertQuantity);
        orderHistory.addOrder(menu, "제로콜라", 4);
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        int totalDisCountAmount = christmasEvent.getTotalBenefitAmount();

        // then
        int expected =
                getDDayDiscountAmount(day)
                        + dessertQuantity * WEEKDAY.getDiscountPrice()
                        + GIVEAWAY_AMOUNT.getValue();
        assertEquals(expected, totalDisCountAmount);
    }

    public int getDDayDiscountAmount(int day) {
        return D_DAY_DISCOUNT_AMOUNT.getValue() + (day - D_DAY_DIFFERENCE.getValue()) * D_DAY_ADD_AMOUNT.getValue();
    }

    @DisplayName("주말 날짜가 들어왔을 때 총 혜택 금액을 반환한다.")
    @Test
    public void getWeekendTotalDisCountAmount() {
        // given
        int day = 15;
        int mainQuantity = 2;
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", mainQuantity);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 4);
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        int totalDisCountAmount = christmasEvent.getTotalBenefitAmount();

        // then
        int expected =
                getDDayDiscountAmount(day)
                        + mainQuantity * WEEKDAY.getDiscountPrice()
                        + GIVEAWAY_AMOUNT.getValue();
        assertEquals(expected, totalDisCountAmount);
    }

    @DisplayName("특별일 날짜가 들어왔을 때 총 혜택 금액을 반환한다.")
    @Test
    public void getSpecialDayTotalDisCountAmount() {
        // given
        int day = 3;
        int dessertQuantity = 2;
        orderHistory.addOrder(menu, "티본스테이크", 1);
        orderHistory.addOrder(menu, "바비큐립", 1);
        orderHistory.addOrder(menu, "초코케이크", dessertQuantity);
        orderHistory.addOrder(menu, "제로콜라", 1);
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        int totalDisCountAmount = christmasEvent.getTotalBenefitAmount();

        // then
        int expected =
                getDDayDiscountAmount(day)
                        + dessertQuantity * WEEKDAY.getDiscountPrice()
                        + GIVEAWAY_AMOUNT.getValue()
                        + SPECIAL_DAY.getDiscountPrice();
        assertEquals(expected, totalDisCountAmount);
    }

    @DisplayName("할인 후 예상 결제 금액을 반환한다.")
    @Test
    public void getTotalDisCountedAmount() {
        // given
        int day = 3;
        int dessertQuantity = 2;
        orderHistory.addOrder(menu, "티본스테이크", 1);
        orderHistory.addOrder(menu, "바비큐립", 1);
        orderHistory.addOrder(menu, "초코케이크", dessertQuantity);
        orderHistory.addOrder(menu, "제로콜라", 1);
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        int totalDisCountedAmount = christmasEvent.getTotalDisCountedAmount(menu, orderHistory);

        // then
        int expected = orderHistory.getTotalAmount(menu)
                - getDDayDiscountAmount(day)
                - dessertQuantity * WEEKDAY.getDiscountPrice()
                - SPECIAL_DAY.getDiscountPrice();
        assertEquals(expected, totalDisCountedAmount);
    }
}
