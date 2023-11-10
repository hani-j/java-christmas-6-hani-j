package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {

    @DisplayName("메뉴에 있는 이름이면 true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"})
    public void isNameInMenu(String name) {
        // given
        Menu menu = new Menu();

        // when & then
        assertDoesNotThrow(() -> menu.validateNameInMenu(name));
    }

    @DisplayName("메뉴에 없는 이름이면 에러메세지를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수푸", "티본스네이크", "쵸코케이크", "그냥콜라"})
    public void isNotNameInMenu(String name) {
        // given
        Menu menu = new Menu();

        // when & then
        assertThatThrownBy(() -> menu.validateNameInMenu(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
