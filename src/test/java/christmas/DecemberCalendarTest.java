package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DecemberCalendarTest {

    @DisplayName("4~7, 11~14, 18~21, 25~28 일을 입력하면 평일을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 11, 12, 13, 14, 18,19,20,21,25,26,27,28})
    public void getDayType(int day) {
        // given
        DecemberCalendar decemberCalendar = new DecemberCalendar();

        // when
        String day = decemberCalendar.getDayType(day);

        // then
        String expected = "평일";
        assertEquals(expected, day);
    }
}
