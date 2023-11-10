package christmas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChristmasEventTest {

    ChristmasEvent christmasEvent = new ChristmasEvent();


    @DisplayName("25 일 이하의 숫자가 들어오면 크리스마스 d-day 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 14, 25})
    public void isDDayDiscount(int day) {
        // given & when
        boolean isDDay = christmasEvent.isDDayDiscount(day);

        // then
        assertTrue(isDDay);
    }

    @DisplayName("25 일 이후의 숫자가 들어오면 크리스마스 d-day 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    public void isNotDDayDiscount(int day) {
        // given & when
        boolean isNotDDay = christmasEvent.isDDayDiscount(day);

        // then
        assertFalse(isNotDDay);
    }

    @DisplayName("평일 날짜가 들어오면 평일 할인 여부가 true 이다.(특별일 제외)")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21, 26, 27, 28})
    public void isWeekdayDiscount(int day) {
        // given & when
        boolean isWeekday = christmasEvent.isWeekdayDiscount(day);

        // then
        assertTrue(isWeekday);
    }

    @DisplayName("평일이 아닌 날짜가 들어오면 평일 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void isNotWeekdayDiscount(int day) {
        // given & when
        boolean isNotWeekday = christmasEvent.isWeekdayDiscount(day);

        // then
        assertFalse(isNotWeekday);
    }

    @DisplayName("주말 날짜가 들어오면 주말 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void isWeekendDiscount(int day) {
        // given & when
        boolean isWeekend = christmasEvent.isWeekendDiscount(day);

        // then
        assertTrue(isWeekend);
    }

    @DisplayName("주말이 아닌 날짜가 들어오면 주말 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28})
    public void isNotWeekendDiscount(int day) {
        // given & when
        boolean isNotWeekend = christmasEvent.isWeekendDiscount(day);

        // then
        assertFalse(isNotWeekend);
    }

    @DisplayName("특별일 날짜가 들어오면 특별 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    public void isSpecialDayDiscount(int day) {
        // given & when
        boolean isWeekend = christmasEvent.isSpecialDayDiscount(day);

        // then
        assertTrue(isWeekend);
    }

    @DisplayName("특별일이 아닌 날짜가 들어오면 특별 할인 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 8, 9, 11, 15, 16, 18, 22, 23, 26, 29, 30})
    public void isNotSpecialDayDiscount(int day) {
        // given & when
        boolean isNotWeekend = christmasEvent.isSpecialDayDiscount(day);

        // then
        assertFalse(isNotWeekend);
    }

    @DisplayName("할인 전 총 금액이 12만원 이상이면 증정 이벤트 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 130_000, 140_000, 150_000})
    public void isGiveawayTarget(int amount) {
        // given & when
        boolean isGiveawayTarget = christmasEvent.isGiveawayTarget(amount);

        // then
        assertTrue(isGiveawayTarget);
    }

    @DisplayName("할인 전 총 금액이 12만원 미만이면 증정 이벤트 여부가 false 이다.")
    @ParameterizedTest
    @ValueSource(ints = {119_999, 1, 1000, 10_000})
    public void isNotGiveawayTarget(int amount) {
        // given & when
        boolean isNotGiveawayTarget = christmasEvent.isGiveawayTarget(amount);

        // then
        assertFalse(isNotGiveawayTarget);
    }
}
