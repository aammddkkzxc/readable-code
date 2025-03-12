package cleancode.studycafe.practice;

import cleancode.studycafe.practice.io.FileHandler;
import cleancode.studycafe.practice.exception.AppException;
import cleancode.studycafe.practice.io.InputHandler;
import cleancode.studycafe.practice.io.OutputHandler;
import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.access.AccessPassType;
import cleancode.studycafe.practice.model.pass.access.AccessPasses;
import cleancode.studycafe.practice.model.pass.locker.LockerPass;
import cleancode.studycafe.practice.model.pass.locker.LockerPasses;

import java.util.List;

public class PassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final FileHandler fileHandler = new FileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            AccessPass accessPass = proceedAccessPassSelection();

            LockerPass lockerPass = proceedLockerPassSelection(accessPass);

            outputHandler.showPassOrderSummary(accessPass, lockerPass);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private AccessPass proceedAccessPassSelection() {
        outputHandler.askAccessPassTypeSelection();
        AccessPassType selectedAccessPassType = inputHandler.selectAccessPassType();

        List<AccessPass> passOptions = findAccessPassOptionsBy(selectedAccessPassType);

        outputHandler.showAccessPassListForSelection(passOptions);
        return inputHandler.selectAccessPass(passOptions);
    }

    private List<AccessPass> findAccessPassOptionsBy(AccessPassType selectedAccessPassType) {
        AccessPasses allAccessPasses = fileHandler.readAllAccessPasses();
        return allAccessPasses.findPassesBy(selectedAccessPassType);
    }

    private LockerPass proceedLockerPassSelection(AccessPass selectedAccessPass) {
        LockerPass lockerPassOption = findLockerPassOptionBy(selectedAccessPass);

        if (lockerPassOption.exist()) {
            outputHandler.askLockerPass(lockerPassOption);
            boolean useLockerPass = inputHandler.decideToUseLockerPass();

            if (useLockerPass) {
                lockerPassOption.startUsing();
            }

        }

        return lockerPassOption;
    }

    private LockerPass findLockerPassOptionBy(AccessPass accessPass) {
        LockerPasses allLockerPasses = fileHandler.readLockerPasses();

        return allLockerPasses.findLockerPassBy(accessPass);
    }

}
