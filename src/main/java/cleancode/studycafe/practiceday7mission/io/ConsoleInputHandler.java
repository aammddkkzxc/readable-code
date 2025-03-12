package cleancode.studycafe.practiceday7mission.io;

import cleancode.studycafe.practiceday7mission.exception.AppException;
import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPass;
import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPassType;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public AccessPassType selectAccessPassType() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return AccessPassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return AccessPassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return AccessPassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    @Override
    public AccessPass selectAccessPass(List<AccessPass> passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    @Override
    public boolean decideToUseLockerPass() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
