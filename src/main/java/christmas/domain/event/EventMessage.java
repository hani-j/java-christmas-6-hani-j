package christmas.domain.event;

public enum EventMessage {
    D_DAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIVEAWAY_EVENT("증정 이벤트"),
    CHAMPAGNE("샴페인 1개"),
    STAR_BADGE("별"),
    TREE_BADGE("트리"),
    SANTA_BADGE("산타"),
    NOTHING("없음");

    private final String message;

    EventMessage(final String message) {
        this.message = message;
    }

    public final String getMessage() {
        return message;
    }
}
