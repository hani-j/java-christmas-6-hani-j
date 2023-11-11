package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

public class OrderHistoryTest {

    @DisplayName("메뉴가 있는지 확인하고 맞다면 주문내역에 추가한다.")
    @ParameterizedTest
    public void addOrder() {
        // given
        OrderHistory orderHistory = new OrderHistory();

        // when
        orderHistory.addOrder("양송이수프");
        orderHistory.addOrder("티본스테이크");
        orderHistory.addOrder("초코케이크");
        orderHistory.addOrder("제로콜라");

        // then
        assertEquals(4, orderHistory.getSize());
    }
}
