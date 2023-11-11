package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderHistoryTest {

    @DisplayName("메뉴가 있는지 확인하고 맞다면 주문내역에 추가한다.")
    @Test
    public void addOrder() {
        // given
        OrderHistory orderHistory = new OrderHistory();
        Menu menu = new Menu();

        // when
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 2);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 4);

        // then
        assertEquals(10, orderHistory.getAllQuantity());
    }

    @DisplayName("없는 메뉴이면 주문내역에 추가하지 않는다.")
    @Test
    public void addOrderNameFail() {
        // given
        OrderHistory orderHistory = new OrderHistory();
        Menu menu = new Menu();

        // when & then
        assertThatThrownBy(() -> orderHistory.addOrder(menu, "양송이수푸", 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        assertEquals(0, orderHistory.getAllQuantity());
    }
}
