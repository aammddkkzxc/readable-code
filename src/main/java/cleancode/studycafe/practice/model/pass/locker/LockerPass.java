package cleancode.studycafe.practice.model.pass.locker;

import cleancode.studycafe.practice.model.pass.Pass;
import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.access.AccessPassType;

public class LockerPass implements Pass {

    private static final int UNAVAILABLE_PROPERTY = 0;

    private final AccessPassType accessPassType;
    private final int duration;
    private final int price;

    private boolean inUse = false;

    private LockerPass(AccessPassType accessPassType, int duration, int price) {
        this.accessPassType = accessPassType;
        this.duration = duration;
        this.price = price;
    }

    public static LockerPass of(AccessPassType passType, int duration, int price) {
        return new LockerPass(passType, duration, price);
    }

    public static LockerPass disableLockerPass(AccessPass pass) {
        return new LockerPass(pass.getAccessPassType(), UNAVAILABLE_PROPERTY, UNAVAILABLE_PROPERTY);
    }

    public void startUsing() {
        inUse = true;
    }

    @Override
    public AccessPassType getAccessPassType() {
        return accessPassType;
    }

    public boolean exist() {
        return !accessPassType.canNotUseLocker();
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public boolean isInUse() {
        return inUse;
    }

}
