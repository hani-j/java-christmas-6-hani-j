package christmas.domain;

import static christmas.domain.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.MenuItem.CHOCO_CAKE;
import static christmas.domain.menu.MenuItem.MUSHROOM_SOUP;
import static christmas.domain.menu.MenuItem.T_BONE_STAKE;
import static christmas.domain.menu.MenuItem.ZERO_COKE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderHistoryTest {

    private Menu menu = new Menu();
    private OrderHistory orderHistory;

    @BeforeEach
    void setTp() {
        orderHistory = new OrderHistory();
    }


    @DisplayName("메뉴가 있는지 확인하고 맞다면 주문내역에 추가한다.")
    @Test
    public void addOrder() {
        // given & when
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 2);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 4);

        // then
        int total = 10;
        assertEquals(total, orderHistory.getAllQuantity());
    }

    @DisplayName("없는 메뉴이면 주문내역에 추가하지 않고 throw 가 발생한다.")
    @Test
    public void addOrderNameFail() {
        // when & then
        assertThatThrownBy(() -> orderHistory.addOrder(menu, "양송이수푸", 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }

    @DisplayName("20개가 넘는 수량을 추가하면 throw 가 발생한다.")
    @Test
    public void addOrderQuantityFail() {
        // when & then
        assertThatThrownBy(() -> orderHistory.addOrder(menu, "양송이수프", 21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }

    @DisplayName("주문 내역의 총 주문 금액을 반환한다.")
    @Test
    public void getTotalAmount() {
        // given
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 2);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 4);

        // when
        int totalAmount = orderHistory.getTotalAmount(menu);

        // then
        int expected = (MUSHROOM_SOUP.getPrice() * 1)
                + (T_BONE_STAKE.getPrice() * 2)
                + (CHOCO_CAKE.getPrice() * 3)
                + (ZERO_COKE.getPrice() * 4);

        assertEquals(expected, totalAmount);
    }
}

