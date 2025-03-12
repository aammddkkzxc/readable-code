package cleancode.studycafe.practiceday7mission.model.pass.access;

import cleancode.studycafe.practiceday7mission.exception.PassDataException;
import cleancode.studycafe.practiceday7mission.model.pass.locker.LockerPassType;

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

    public static AccessPassType of(String name) {
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new PassDataException("check data, AccessPassType not found");
        }
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

