package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    
    @DisplayName("1-31 의 숫자인 경우 throw 가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "30", "31"})
    void validateDay(String day) {
        // given
        Validator validator = new Validator();

        // when & then
        assertDoesNotThrow(() -> validator.validateDay(day));
    }

    @DisplayName("1-31 의 숫자가 아닌 경우 throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1.", ".2", "ㄱ", "one", "32", "44"})
    void validateNotDay(String day) {
        // given
        Validator validator = new Validator();

        // when & then
        assertThatThrownBy(() -> validator.validateDay(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
