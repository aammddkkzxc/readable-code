package cleancode.studycafe.practiceday7mission.io;

import cleancode.studycafe.practiceday7mission.model.pass.Charge;
import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPass;
import cleancode.studycafe.practiceday7mission.model.pass.locker.LockerPass;

import java.util.List;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askAccessPassTypeSelection();

    void showAccessPassListForSelection(List<AccessPass> passes);

    void askLockerPass(LockerPass lockerPass);

    void showPassResult(AccessPass accessPass, LockerPass lockerPass);

    void showChargeResult(Charge charge);

    void showSimpleMessage(String message);

}
