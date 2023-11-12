package christmas.domain;

import christmas.domain.menu.Category;

public record Product(Category category, int price) {
}
