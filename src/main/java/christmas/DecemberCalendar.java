//package christmas;
//
//import static christmas.DayType.SPECIAL_DAY;
//import static christmas.DayType.WEEKDAY;
//import static christmas.DayType.WEEKEND;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DecemberCalendar {
//    private Map<Integer, DayType> calendar;
//
//    public DecemberCalendar() {
//        calendar = new HashMap<>();
//        initDay();
//    }
//
//    public void initDay() {
//        initWeekend();
//        initSpecialDay();
//        initWeekday(4, 7);
//        initWeekday(11, 14);
//        initWeekday(18, 21);
//        initWeekday(26, 28);
//    }
//
//    private void initWeekend() {
//        for (int day = 1; day <= 31; day += 7) {
//            calendar.put(day, WEEKEND);
//            calendar.put(day + 1, WEEKEND);
//        }
//    }
//
//    private void initSpecialDay() {
//        for (int day = 3; day <= 31; day += 7) {
//            calendar.put(day, SPECIAL_DAY);
//        }
//        calendar.put(25, SPECIAL_DAY);
//    }
//
//    private void initWeekday(int startDay, int endDay) {
//        for (int day = startDay; day <= endDay; day++) {
//            calendar.put(day, WEEKDAY);
//        }
//    }
//
//    public DayType getDayType(int day) {
//        return calendar.get(day);
//    }
//}
