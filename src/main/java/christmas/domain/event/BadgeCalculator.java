package christmas.domain.event;

import static christmas.domain.event.EventMessage.NOTHING;
import static christmas.domain.event.EventMessage.SANTA_BADGE;
import static christmas.domain.event.EventMessage.STAR_BADGE;
import static christmas.domain.event.EventMessage.TREE_BADGE;
import static christmas.domain.event.EventValue.SANTA_BADGE_MINIMUM;
import static christmas.domain.event.EventValue.STAR_BADGE_MINIMUM;
import static christmas.domain.event.EventValue.TREE_BADGE_MINIMUM;

public class BadgeCalculator {

    private BadgeCalculator() {

    }

    public static BadgeCalculator create() {
        return new BadgeCalculator();
    }

    public final String calculateEventBadge(final int discountAmount) {
        if (discountAmount >= STAR_BADGE_MINIMUM.getValue() && discountAmount < TREE_BADGE_MINIMUM.getValue()) {
            return STAR_BADGE.getMessage();
        }
        if (discountAmount >= TREE_BADGE_MINIMUM.getValue() && discountAmount < SANTA_BADGE_MINIMUM.getValue()) {
            return TREE_BADGE.getMessage();
        }
        if (discountAmount >= SANTA_BADGE_MINIMUM.getValue()) {
            return SANTA_BADGE.getMessage();
        }
        return NOTHING.getMessage();
    }
}
