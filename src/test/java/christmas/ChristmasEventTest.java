package christmas;

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

    @DisplayName("평일 날짜가 들어오면 평일 할인 여부가 true 이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 14, 25})
    public void isWeekdayDiscount(int day) {
        // given
        ChristmasEvent christmasEvent = new ChristmasEvent();

        // when
        boolean isWeekday = christmasEvent.isWeekdayDiscount(day);

        // then
        assertTrue(isWeekday);
    }
}
