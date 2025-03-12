package cleancode.studycafe.practice.model.pass.access;

import cleancode.studycafe.practice.model.pass.locker.LockerPassType;

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

    public boolean canNotUseLocker() {
        return lockerPassType.isUnavailable();
    }
}

