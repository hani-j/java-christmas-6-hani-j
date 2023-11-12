package christmas.domain;

import static christmas.domain.ErrorMessage.INVALID_DAY;
import static christmas.domain.event.DayType.getDayType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.DayType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DayTypeTest {

    @DisplayName("4~7, 11~14, 18~21, 25~28 일을 입력하면 평일을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21, 26, 27, 28})
    public void getWeekdayDayType(int day) {
        // given & when
        DayType dayType = getDayType(day);

        // then
        DayType expected = DayType.WEEKDAY;
        assertEquals(expected, dayType);
    }

    @DisplayName("1~2, 8~9, 15~16, 22~23, 29~30 일을 입력하면 평일을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void getWeekendDayType(int day) {
        // given & when
        DayType dayType = getDayType(day);

        // then
        DayType expected = DayType.WEEKEND;
        assertEquals(expected, dayType);
    }

    @DisplayName("3, 10, 17, 24, 25, 31 일을 입력하면 평일을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    public void getSpecialDayType(int day) {
        // given & when
        DayType dayType = getDayType(day);

        // then
        DayType expected = DayType.SPECIAL_DAY;
        assertEquals(expected, dayType);
    }

    @DisplayName("1~31 이 아닌 날짜를 입력하면 throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32, 35, 40, 100})
    public void getSpecialDayTypeFail(int day) {
        // when & then
        assertThatThrownBy(() -> getDayType(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DAY.getMessage());
    }
}
