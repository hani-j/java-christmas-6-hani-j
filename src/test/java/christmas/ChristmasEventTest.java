package christmas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChristmasEventTest {

    @DisplayName("25 일 이하의 숫자가 들어오면 크리스마스 d-day 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 14, 25})
    public void isDDayDiscount(int day) {
        // given
        ChristmasEvent christmasEvent = new ChristmasEvent();

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
        ChristmasEvent christmasEvent = new ChristmasEvent();

        // when
        boolean isNotDDay = christmasEvent.isDDayDiscount(day);

        // then
        assertFalse(isNotDDay);
    }

    @DisplayName("평일 날짜가 들어오면 평일 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 11, 18, 26, 28})
    public void isWeekdayDiscount(int day) {
        // given
        ChristmasEvent christmasEvent = new ChristmasEvent();

        // when
        boolean isWeekday = christmasEvent.isWeekdayDiscount(day);

        // then
        assertTrue(isWeekday);
    }

    @DisplayName("평일 날짜가 들어오면 평일 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1,2,8,9,15,16, 22,23,29,30})
    public void isNotWeekdayDiscount(int day) {
        // given
        ChristmasEvent christmasEvent = new ChristmasEvent();

        // when
        boolean isNotWeekday = christmasEvent.isWeekdayDiscount(day);

        // then
        assertFalse(isNotWeekday);
    }
}
