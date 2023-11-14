package christmas.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {

    private EventValidator validator = new EventValidator();

    @DisplayName("1-31 의 숫자인 경우 throw 가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "30", "31"})
    void validateDay(String day) {
        // when & then
        assertDoesNotThrow(() -> validator.validateDay(day));
    }

    @DisplayName("1-31 의 숫자가 아닌 경우 throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1.", ".2", "ㄱ", "one", "32", "44", "2147483648"})
    void validateNotDay(String day) {
        // when & then
        assertThatThrownBy(() -> validator.validateDay(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴 형식에 맞는 경우 Throw 가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1", "해산물파스타-2,레드와인-1,초코케이크-1"})
    void validateOrderFormat(String order) {
        // when & then
        assertDoesNotThrow(() -> validator.validateOrderFormat(order));
    }

    @DisplayName("메뉴 형식에 맞지 않는 경우 Throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2, 레드와인-1,초코케이크-1", "해산물파스타2, 레드와인1,초코케이크1"})
    void validateNotOrderFormat(String order) {
        // when & then
        assertThatThrownBy(() -> validator.validateOrderFormat(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 개수가 1이상인 경우 Throw 가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크-1", "해산물파스타-2,레드와인-7,초코케이크-11"})
    void validateMenuAmount(String order) {
        // when & then
        assertDoesNotThrow(() -> validator.validateMenuAmount(order));
    }

    @DisplayName("메뉴의 개수가 1이상이 아닌 경우 Throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타--2,레드와인-1,초코케이크-1", "해산물파스타-0,레드와인-7,초코케이크-11"})
    void validateInvalidMenuAmount(String order) {
        // when & then
        assertThatThrownBy(() -> validator.validateMenuAmount(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 중복된 경우 Throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,해산물파스타-1,초코케이크-1", "초코케이크-0,레드와인-7,초코케이크-11"})
    void validateMenuDuplicate(String order) {
        // when & then
        assertThatThrownBy(() -> validator.validateMenuDuplicate(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문한 경우 Throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1", "제로콜라-2,레드와인-1,샴페인-1"})
    void validateOnlyBeverage(String order) {
        // given
        Menu menu = new Menu();

        // when & then
        assertThatThrownBy(() -> validator.validateOnlyBeverage(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 수량이 20개가 넘는 경우 Throw 가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-5,제로콜라-5,레드와인-5,샴페인-6"})
    void validateQuantity(String order) {
        // when & then
        assertThatThrownBy(() -> validator.validateQuantity(order))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
