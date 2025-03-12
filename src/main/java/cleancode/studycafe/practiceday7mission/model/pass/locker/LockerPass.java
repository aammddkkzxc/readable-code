package cleancode.studycafe.practiceday7mission.model.pass.locker;

import cleancode.studycafe.practiceday7mission.exception.PassDataException;
import cleancode.studycafe.practiceday7mission.model.pass.Pass;
import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPass;
import cleancode.studycafe.practiceday7mission.model.pass.access.AccessPassType;

import java.util.Objects;

public class LockerPass implements Pass {

    private static final int UNAVAILABLE_PROPERTY = 0;

    private final AccessPassType accessPassType;
    private final int duration;
    private final int price;

    private boolean inUse = false;

    private LockerPass(String accessPassType, int duration, int price) {
        validate(accessPassType, duration, price);
        this.accessPassType = AccessPassType.valueOf(accessPassType);
        this.duration = duration;
        this.price = price;
    }

    private LockerPass(AccessPass accessPass) {
        this.accessPassType = accessPass.getAccessPassType();
        this.duration = UNAVAILABLE_PROPERTY;
        this.price = UNAVAILABLE_PROPERTY;
    }

    public static LockerPass of(String passType, int duration, int price) {
        return new LockerPass(passType, duration, price);
    }

    public static LockerPass ofUnavailableLockerPass(AccessPass pass) {
        return new LockerPass(pass);
    }

    public void startUsing() {
        inUse = true;
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
        return inUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LockerPass that = (LockerPass) o;
        return duration == that.duration && price == that.price && inUse == that.inUse && accessPassType == that.accessPassType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessPassType, duration, price, inUse);
    }

    private void validate(String accessPassType, int duration, int price) {
        try {
            AccessPassType.valueOf(accessPassType);
        } catch (IllegalArgumentException e) {
            throw new PassDataException("check file, access pass type not found");
        }

        if (duration <= 0) {
            throw new PassDataException("check file, duration must be greater than 0");
        }

        if (price <= 0) {
            throw new PassDataException("check file, price must be greater than 0");
        }
    }
}
