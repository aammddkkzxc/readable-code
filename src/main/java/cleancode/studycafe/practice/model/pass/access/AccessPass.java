package cleancode.studycafe.practice.model.pass.access;

import cleancode.studycafe.practice.model.pass.locker.LockerPass;
import cleancode.studycafe.practice.model.pass.Pass;

public class AccessPass implements Pass {

    private final AccessPassType type;
    private final int duration;
    private final int price;
    private final double discountRate;

    private AccessPass(AccessPassType type, int duration, int price, double discountRate) {
        this.type = type;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static AccessPass of(AccessPassType passType, int duration, int price, double discountRate) {
        return new AccessPass(passType, duration, price, discountRate);
    }

    public boolean isSameTypeWith(AccessPassType passType) {
        return this.type == passType;
    }

    public boolean matchWithLockerPass(LockerPass lockerPass) {
        return this.type == lockerPass.getAccessPassType()
                && this.duration == lockerPass.getDuration();
    }

    public boolean canNotUseLockerPass() {
        return this.type.canNotUseLocker();
    }

    @Override
    public AccessPassType getAccessPassType() {
        return type;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}
