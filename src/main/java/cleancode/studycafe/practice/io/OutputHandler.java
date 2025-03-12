package cleancode.studycafe.practice.io;

import cleancode.studycafe.practice.model.pass.Charge;
import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.locker.LockerPass;

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
