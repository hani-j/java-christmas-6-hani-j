package christmas;

import static christmas.Category.APPETIZER;
import static christmas.Category.BEVERAGE;
import static christmas.Category.DESSERT;
import static christmas.Category.MAIN;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {

    Menu menu = new Menu();

    @DisplayName("메뉴에 있는 이름이면 true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"})
    public void isNameInMenu(String name) {
        // when & then
        assertDoesNotThrow(() -> menu.validateNameInMenu(name));
    }

    @DisplayName("메뉴에 없는 이름이면 에러메세지를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수", "티본스네이크", "쵸코케이크", "그냥콜라"})
    public void isNotNameInMenu(String name) {
        // when & then
        assertThatThrownBy(() -> menu.validateNameInMenu(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("에피타이저 메뉴를 입력하면 APPETIZER Enum 을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
    public void getAppetizerCategory(String name) {
        // when
        Category category = menu.getCategory(name);

        // then
        assertEquals(APPETIZER, category);
    }

    @DisplayName("메인 메뉴를 입력하면 MAIN Enum 을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    public void getMainCategory(String name) {
        // when
        Category category = menu.getCategory(name);

        // then
        assertEquals(MAIN, category);
    }

    @DisplayName("디저트 메뉴를 입력하면 DESSERT Enum 을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크", "초코케이크"})
    public void getDessertCategory(String name) {
        // when
        Category category = menu.getCategory(name);

        // then
        assertEquals(DESSERT, category);
    }

    @DisplayName("음료 메뉴를 입력하면 BEVERAGE Enum 을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    public void getBeverageCategory(String name) {
        // when
        Category category = menu.getCategory(name);

        // then
        assertEquals(BEVERAGE, category);
    }
}
