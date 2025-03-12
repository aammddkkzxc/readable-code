package cleancode.studycafe.practiceday7mission.model.pass.locker;

public enum LockerPassType {

    AVAILABLE("발권 가능"),
    UNAVAILABLE("발권 불가");

    private final String description;

    LockerPassType(String description) {
        this.description = description;
    }

    public boolean isUnavailable() {
        return this == UNAVAILABLE;
    }
}
