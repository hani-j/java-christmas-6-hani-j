package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {

    @DisplayName("숫자인 경우 exception 을 반환하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "40", "100"})
    void validateDay(int day) {
        // given
        Validator validator = new Validator();

        // when & then
        assertDoesNotThrow(validator.validateDay(day));
    }

    @DisplayName("숫자가 아닌 경우 exception 을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1.", ".2", "-3", "400", "2147483648"})
    void validateNotDay(int day) {
        // given
        Validator validator = new Validator();

        // when & then
        assertThatThrownBy(() -> validator.validateDay(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
