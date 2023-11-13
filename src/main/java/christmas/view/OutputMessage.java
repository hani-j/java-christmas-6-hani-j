package christmas.view;

public enum OutputMessage {
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    REQUEST_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    AMOUNT("%s %d개\n"),
    TOTAL_AMOUNT("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    CHAMPAGNE("샴페인 1개"),
    BENEFIT_DETAILS("<혜택 내역>"),
    DETAILS("%s: -%d원\n"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    WON_FORMAT("%원,d"),
    TOTAL_DISCOUNTED_AMOUNT("<할인 후 예상 결제 금액>"),
    NOTHING("없음");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public final String getMessage() {
        return message;
    }
}
