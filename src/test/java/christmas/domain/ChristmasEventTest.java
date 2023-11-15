package christmas.domain;

import static christmas.domain.event.EventMessage.SANTA_BADGE;
import static christmas.domain.event.EventMessage.STAR_BADGE;
import static christmas.domain.event.EventMessage.TREE_BADGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.event.BadgeCalculator;
import christmas.domain.event.ChristmasEvent;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.DiscountDetails;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChristmasEventTest {

    private Menu menu = Menu.create();
    private OrderHistory orderHistory = OrderHistory.create();
    private ChristmasEvent christmasEvent = ChristmasEvent.of(
            DiscountDetails.create(), DiscountCalculator.create(), BadgeCalculator.create());

    @DisplayName("총 주문 금액이 10,000원 이상이면 이벤트 대상 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {10_000, 20_000, 100_000, 200_000})
    public void isEventTarget(int amount) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, 1);

        // when
        boolean isEventTarget = christmasEvent.isNotEventTarget(amount);

        // then
        assertFalse(isEventTarget);
    }

    @DisplayName("총 주문 금액이 10,000원 미만이면 이벤트 대상 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {9_999, 1000, 2000, 5000})
    public void isNotEventTarget(int amount) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, 1);

        // when
        boolean isNotEventTarget = christmasEvent.isNotEventTarget(amount);

        // then
        assertTrue(isNotEventTarget);
    }

    @DisplayName("25 일 이하의 숫자가 들어오면 크리스마스 d-day 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 14, 25})
    public void isDDayDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isDDay = christmasEvent.isDDayDiscount(day);

        // then
        assertTrue(isDDay);
    }

    @DisplayName("25 일 이후의 숫자가 들어오면 크리스마스 d-day 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    public void isNotDDayDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isNotDDay = christmasEvent.isDDayDiscount(day);

        // then
        assertFalse(isNotDDay);
    }

    @DisplayName("평일 날짜가 들어오면 평일 할인 여부가 true 이다.(특별일 제외)")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21, 26, 27, 28})
    public void isWeekdayDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isWeekday = christmasEvent.isWeekdayDiscount(day);

        // then
        assertTrue(isWeekday);
    }

    @DisplayName("평일이 아닌 날짜가 들어오면 평일 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void isNotWeekdayDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isNotWeekday = christmasEvent.isWeekdayDiscount(day);

        // then
        assertFalse(isNotWeekday);
    }

    @DisplayName("주말 날짜가 들어오면 주말 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void isWeekendDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isWeekend = christmasEvent.isWeekendDiscount(day);

        // then
        assertTrue(isWeekend);
    }

    @DisplayName("주말이 아닌 날짜가 들어오면 주말 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28})
    public void isNotWeekendDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isNotWeekend = christmasEvent.isWeekendDiscount(day);

        // then
        assertFalse(isNotWeekend);
    }

    @DisplayName("특별일 날짜가 들어오면 특별 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    public void isSpecialDayDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isWeekend = christmasEvent.isSpecialDayDiscount(day);

        // then
        assertTrue(isWeekend);
    }

    @DisplayName("특별일이 아닌 날짜가 들어오면 특별 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 8, 9, 11, 15, 16, 18, 22, 23, 26, 29, 30})
    public void isNotSpecialDayDiscount(int day) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, day);

        // when
        boolean isNotWeekend = christmasEvent.isSpecialDayDiscount(day);

        // then
        assertFalse(isNotWeekend);
    }

    @DisplayName("할인 전 총 금액이 12만원 이상이면 증정 이벤트 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 130_000, 140_000, 150_000})
    public void isGiveawayTarget(int amount) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, 1);

        // when
        boolean isGiveawayTarget = christmasEvent.isGiveawayTarget(amount);

        // then
        assertTrue(isGiveawayTarget);
    }

    @DisplayName("할인 전 총 금액이 12만원 미만이면 증정 이벤트 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {119_999, 1, 1000, 10_000})
    public void isNotGiveawayTarget(int amount) {
        // given
        christmasEvent.applyDiscount(menu, orderHistory, 1);

        // when
        boolean isNotGiveawayTarget = christmasEvent.isGiveawayTarget(amount);

        // then
        assertFalse(isNotGiveawayTarget);
    }

    @DisplayName("총 혜택 금액이 5천원 이상 1만원 미만이면 별을 반환한다.")
    @Test
    public void getStarEventBadge() {
        // given
        Menu menu = Menu.create();
        OrderHistory orderHistory = OrderHistory.create();
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 1);
        orderHistory.addOrder(menu, "초코케이크", 1);
        orderHistory.addOrder(menu, "제로콜라", 1);
        christmasEvent.applyDiscount(menu, orderHistory, 17);

        // when
        String eventBadge = christmasEvent.getEventBadge();

        // then
        assertEquals(STAR_BADGE.getMessage(), eventBadge);
    }

    @DisplayName("총 혜택 금액이 1만원 이상 2만원 미만이면 트리을 반환한다.")
    @Test
    public void getTreeEventBadge() {
        // given
        Menu menu = Menu.create();
        OrderHistory orderHistory = OrderHistory.create();
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 1);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 1);
        christmasEvent.applyDiscount(menu, orderHistory, 25);

        // when
        String eventBadge = christmasEvent.getEventBadge();

        // then
        assertEquals(TREE_BADGE.getMessage(), eventBadge);
    }

    @DisplayName("총 혜택 금액이 2만원 이상이면 산타를 반환한다.")
    @Test
    public void getSantaEventBadge() {
        // given
        Menu menu = Menu.create();
        OrderHistory orderHistory = OrderHistory.create();
        orderHistory.addOrder(menu, "양송이수프", 2);
        orderHistory.addOrder(menu, "티본스테이크", 3);
        orderHistory.addOrder(menu, "초코케이크", 10);
        orderHistory.addOrder(menu, "제로콜라", 2);
        christmasEvent.applyDiscount(menu, orderHistory, 17);

        // when
        String eventBadge = christmasEvent.getEventBadge();

        // then
        assertEquals(SANTA_BADGE.getMessage(), eventBadge);
    }
}
