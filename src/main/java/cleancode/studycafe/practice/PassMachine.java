package cleancode.studycafe.practice;

import cleancode.studycafe.practice.config.MachineConfig;
import cleancode.studycafe.practice.exception.AppException;
import cleancode.studycafe.practice.io.InputHandler;
import cleancode.studycafe.practice.io.OutputHandler;
import cleancode.studycafe.practice.io.PassDataReader;
import cleancode.studycafe.practice.model.pass.Charge;
import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.access.AccessPassType;
import cleancode.studycafe.practice.model.pass.access.AccessPasses;
import cleancode.studycafe.practice.model.pass.locker.LockerPass;
import cleancode.studycafe.practice.model.pass.locker.LockerPasses;

import java.util.List;

public class PassMachine {

    private final InputHandler consoleInputHandler;
    private final OutputHandler consoleOutputHandler;

    private AccessPasses allAccessPasses;
    private LockerPasses allLockerPasses;

    public PassMachine(MachineConfig machineConfig) {
        PassDataReader passDataReader = machineConfig.getPassDataReader();
        consoleInputHandler = machineConfig.getInputHandler();
        consoleOutputHandler = machineConfig.getOutputHandler();

        allAccessPasses = passDataReader.readAllAccessPasses();
        allLockerPasses = passDataReader.readLockerPasses();
    }

    public void run() {
        try {
            consoleOutputHandler.showWelcomeMessage();
            consoleOutputHandler.showAnnouncement();

            AccessPass selectedAccessPass = proceedAccessPassSelection();
            LockerPass selectedLockerPass = proceedLockerPassSelection(selectedAccessPass);
            Charge charge = new Charge(selectedAccessPass, selectedLockerPass);

            consoleOutputHandler.showPassResult(selectedAccessPass, selectedLockerPass);
            consoleOutputHandler.showChargeResult(charge);
        } catch (AppException e) {
            consoleOutputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            consoleOutputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private AccessPass proceedAccessPassSelection() {
        consoleOutputHandler.askAccessPassTypeSelection();

        AccessPassType selectedAccessPassType = consoleInputHandler.selectAccessPassType();
        List<AccessPass> accessPassOptions = allAccessPasses.findPassesBy(selectedAccessPassType);
        consoleOutputHandler.showAccessPassListForSelection(accessPassOptions);

        return consoleInputHandler.selectAccessPass(accessPassOptions);
    }

    private LockerPass proceedLockerPassSelection(AccessPass selectedAccessPass) {
        LockerPass lockerPassOption = allLockerPasses.findLockerPassBy(selectedAccessPass);

        return checkAndSetUsage(lockerPassOption);
    }

    private LockerPass checkAndSetUsage(LockerPass lockerPassOption) {
        if (lockerPassOption.isAvailable()) {
            consoleOutputHandler.askLockerPass(lockerPassOption);
            boolean useLockerPass = consoleInputHandler.decideToUseLockerPass();

            if (useLockerPass) {
                return LockerPass.createLockerPassInUseFrom(lockerPassOption);
            }
        }

        return lockerPassOption;
    }

}
