package cleancode.studycafe.practice.model.pass.locker;

import cleancode.studycafe.practice.exception.PassDataException;
import cleancode.studycafe.practice.model.pass.Pass;
import cleancode.studycafe.practice.model.pass.access.AccessPass;
import cleancode.studycafe.practice.model.pass.access.AccessPassType;

import java.util.Objects;

public class LockerPass implements Pass {

    private static final int UNAVAILABLE_PROPERTY = 0;

    private final AccessPassType accessPassType;
    private final int duration;
    private final int price;
    private final boolean isInUse;

    private LockerPass(AccessPassType accessPassType, int duration, int price, boolean isInUse) {
        this.accessPassType = accessPassType;
        this.duration = duration;
        this.price = price;
        this.isInUse = isInUse;
    }

    public static LockerPass of(String accessPassTypeName, int duration, int price) {
        AccessPassType accessPassType = AccessPassType.validateAndCreateAccessPassTypeFrom(accessPassTypeName);
        validate(duration, price);
        return new LockerPass(accessPassType, duration, price, false);
    }

    public static LockerPass createUnavailableLockerPassFrom(AccessPass pass) {
        AccessPassType accessPassType = pass.getAccessPassType();
        return new LockerPass(accessPassType, UNAVAILABLE_PROPERTY, UNAVAILABLE_PROPERTY, false);
    }

    public static LockerPass createLockerPassInUseFrom(LockerPass lockerPassOption) {
        return new LockerPass(lockerPassOption.getAccessPassType(), lockerPassOption.getDuration(), lockerPassOption.getPrice(), true);
    }

    @Override
    public AccessPassType getAccessPassType() {
        return accessPassType;
    }

    public boolean isAvailable() {
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
        return isInUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LockerPass that = (LockerPass) o;
        return duration == that.duration && price == that.price && isInUse == that.isInUse && accessPassType == that.accessPassType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessPassType, duration, price, isInUse);
    }

    private static void validate(int duration, int price) {
        if (duration <= 0) {
            throw new PassDataException("check file, duration must be greater than 0");
        }

        if (price <= 0) {
            throw new PassDataException("check file, price must be greater than 0");
        }
    }
}
