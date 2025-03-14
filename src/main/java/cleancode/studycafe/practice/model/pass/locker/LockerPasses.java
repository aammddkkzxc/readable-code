package cleancode.studycafe.practice.model.pass.locker;

import cleancode.studycafe.practice.model.pass.access.AccessPass;

import java.util.List;

public class LockerPasses {

    private final List<LockerPass> lockerPasses;

    public LockerPasses(List<LockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static LockerPasses of(List<LockerPass> lockerPasses) {
        return new LockerPasses(lockerPasses);
    }

    public LockerPass findLockerPassBy(AccessPass accessPass) {
        return lockerPasses.stream()
                .filter(accessPass::matchWithLockerPass)
                .findFirst()
                .orElse(LockerPass.createUnavailableLockerPassFrom(accessPass));
    }
}
