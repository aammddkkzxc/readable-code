package cleancode.studycafe.practice.model.pass;

import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.locker.LockerPass;

public class Charge {

    private final AccessPass accessPass;
    private final LockerPass lockerPass;

    public Charge(AccessPass accessPass, LockerPass lockerPass) {
        this.accessPass = accessPass;
        this.lockerPass = lockerPass;
    }

    public static Charge of(AccessPass accessPass, LockerPass lockerPass) {
        return new Charge(accessPass, lockerPass);
    }

    public boolean isDiscountApplied() {
        return getDiscountAmount() > 0;
    }

    public int getCharge() {
        int lockerPassCharge = lockerPass.getPrice();
        int totalCharge = accessPass.getPrice() + lockerPassCharge;

        return totalCharge - getDiscountAmount();
    }

    public int getDiscountAmount() {
        return accessPass.getDiscountPrice();
    }

}
