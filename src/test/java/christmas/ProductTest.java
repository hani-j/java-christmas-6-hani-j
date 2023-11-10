package christmas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product soup = new Product("양송이수프", 6000);

    @DisplayName("입력된 이름이 메뉴의 이름과 같으면 true 를 반환한다.")
    @Test
    public void isSameName() {
        // given
        Product soup = new Product("양송이수프", 6000);
        String name = "양송이수프";

        // when
        boolean isSameName = soup.isSameName(name);

        // then
        assertTrue(isSameName);
    }

    @DisplayName("입력된 이름이 메뉴의 이름과 다르면 false 를 반환한다.")
    @Test
    public void isNotSameName() {
        // given
        Product soup = new Product("양송이수프", 6000);
        String name = "양송이수푸";

        // when
        boolean isNotSameName = soup.isSameName(name);

        // then
        assertFalse(isNotSameName);
    }
}
