package cleancode.studycafe.tobe.model.pass.seat;

import java.util.Set;

public enum StudyCafeSeatPassType {

    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석");

    private static final Set<StudyCafeSeatPassType> LOCKER_TYPES = Set.of(FIXED);

    private final String description;

    StudyCafeSeatPassType(String description) {
        this.description = description;
    }

    public boolean isLockerType() {
        return LOCKER_TYPES.contains(this);
    }

    public boolean isNotLockerType() {
        return !isLockerType();
    }

}
