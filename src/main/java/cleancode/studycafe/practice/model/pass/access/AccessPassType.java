package cleancode.studycafe.practice.model.pass.access;

import cleancode.studycafe.practice.exception.PassDataException;
import cleancode.studycafe.practice.model.pass.locker.LockerPassType;

import java.util.Arrays;

public enum AccessPassType {

    HOURLY("시간 단위 이용권", LockerPassType.UNAVAILABLE),
    WEEKLY("주 단위 이용권", LockerPassType.UNAVAILABLE),
    FIXED("1인 고정석", LockerPassType.AVAILABLE);

    private final String description;
    private final LockerPassType lockerPassType;

    AccessPassType(String description, LockerPassType lockerPassType) {
        this.description = description;
        this.lockerPassType = lockerPassType;
    }

    public static AccessPassType validateAndCreateAccessPassTypeFrom(String name) {
        return Arrays
                .stream(values())
                .filter(type -> type.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new PassDataException("No AccessPassType found with the given name: " + name));
    }

    public boolean canNotUseLocker() {
        return lockerPassType.isUnavailable();
    }

    public boolean isHourly() {
        return this == HOURLY;
    }

    public boolean isWeekly() {
        return this == WEEKLY;
    }

    public boolean isFixed() {
        return this == FIXED;
    }
}

