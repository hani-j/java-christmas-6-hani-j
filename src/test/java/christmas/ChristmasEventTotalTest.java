package christmas;

import static christmas.DayType.WEEKEND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasEventTotalTest {

    @DisplayName("평일 날짜가 들어왔을 때 총 혜택 금액을 반환한다.")
    @Test
    public void getTotalDisCountAmount() {
        // given
        int day = 4;
        Menu menu = new Menu();
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.addOrder(menu, "양송이수프", 1);
        orderHistory.addOrder(menu, "티본스테이크", 2);
        orderHistory.addOrder(menu, "초코케이크", 3);
        orderHistory.addOrder(menu, "제로콜라", 4);
        ChristmasEvent christmasEvent1 = new ChristmasEvent(menu, orderHistory, day);

        // when
        int totalDisCountAmount = christmasEvent1.getTotalDisCountAmount();

        // then
        int expected = 1300 + 3 * WEEKEND.getDiscountPrice() + 25000;
        assertEquals(expected, totalDisCountAmount);
    }
}
