package cleancode.studycafe.practice.model.pass.access;

import cleancode.studycafe.practice.exception.PassDataException;
import cleancode.studycafe.practice.model.pass.Pass;
import cleancode.studycafe.practice.model.pass.locker.LockerPass;

import java.util.Objects;

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

    public static AccessPass of(String typeName, int duration, int price, double discountRate) {
        AccessPassType accessPassType = AccessPassType.validateAndCreateAccessPassTypeFrom(typeName);
        validate(duration, price, discountRate);
        return new AccessPass(accessPassType, duration, price, discountRate);
    }

    public boolean isSameTypeWith(AccessPassType passType) {
        return this.type == passType;
    }

    public boolean matchWithLockerPass(LockerPass lockerPass) {
        return this.type == lockerPass.getAccessPassType()
                && this.duration == lockerPass.getDuration();
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

    public int getDiscountPrice() {
        return (int) (price * discountRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessPass that = (AccessPass) o;
        return duration == that.duration && price == that.price && Double.compare(discountRate, that.discountRate) == 0 && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, duration, price, discountRate);
    }

    private static void validate(int duration, int price, double discountRate) {
        checkDuration(duration);
        checkPrice(price);
        checkDiscountRate(discountRate);
    }

    private static void checkDuration(int duration) {
        if (duration <= 0) {
            throw new PassDataException("check data, duration must be greater than 0");
        }
    }

    private static void checkPrice(int price) {
        if (price <= 0) {
            throw new PassDataException("check data, price must be greater than 0");
        }
    }

    private static void checkDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            throw new PassDataException("check data, discountRate must be between 0 and 1");
        }
    }
}
