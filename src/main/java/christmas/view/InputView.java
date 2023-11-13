package christmas.view;

import static christmas.view.OutputMessage.REQUEST_DAY;
import static christmas.view.OutputMessage.REQUEST_ORDER;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputDay() {
        System.out.println(REQUEST_DAY.getMessage());
        return Console.readLine();
    }

    public String inputOrder() {
        System.out.println(REQUEST_ORDER.getMessage());
        return Console.readLine();
    }
}
