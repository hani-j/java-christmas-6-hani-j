package christmas;

import static christmas.DayType.SPECIAL_DAY;
import static christmas.DayType.WEEKDAY;
import static christmas.DayType.WEEKEND;

import java.util.HashMap;
import java.util.Map;

public class DecemberCalendar {
    private Map<Integer, DayType> calendar;

    public DecemberCalendar() {
        calendar = new HashMap<>();
        initDay();
    }

    public void initDay() {
        initWeekend();
        initSpecialDay();
        initWeekday(4, 7);
        initWeekday(11, 14);
        initWeekday(18, 21);
        initWeekday(26, 28);
    }

    private void initWeekend() {
        calendar.put(1, WEEKEND);
        calendar.put(2, WEEKEND);
        calendar.put(8, WEEKEND);
        calendar.put(9, WEEKEND);
        calendar.put(15, WEEKEND);
        calendar.put(16, WEEKEND);
        calendar.put(22, WEEKEND);
        calendar.put(23, WEEKEND);
        calendar.put(29, WEEKEND);
        calendar.put(30, WEEKEND);
    }

    private void initSpecialDay() {
        calendar.put(3, SPECIAL_DAY);
        calendar.put(10, SPECIAL_DAY);
        calendar.put(17, SPECIAL_DAY);
        calendar.put(24, SPECIAL_DAY);
        calendar.put(25, SPECIAL_DAY);
        calendar.put(31, SPECIAL_DAY);
    }

    private void initWeekday(int startDay, int endDay) {
        for (int day = startDay; day <= endDay; day++) {
            calendar.put(day, WEEKDAY);
        }
    }

    public DayType getDayType(int day) {
        return calendar.get(day);
    }
}
