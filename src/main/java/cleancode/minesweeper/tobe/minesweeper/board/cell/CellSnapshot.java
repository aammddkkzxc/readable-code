package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Objects;

public class CellSnapshot {

    private final CellSnapshotStatus status;
    private final int nearbyLandMineCount;

    public CellSnapshot(CellSnapshotStatus status, int nearbyLandMineCount) {
        this.status = status;
        this.nearbyLandMineCount = nearbyLandMineCount;
    }

    public static CellSnapshot of(CellSnapshotStatus status, int nearbyLandMineCount) {
        return new CellSnapshot(status, nearbyLandMineCount);
    }

    public static CellSnapshot ofEmpty() {
        return CellSnapshot.of(CellSnapshotStatus.EMPTY, 0);
    }

    public static CellSnapshot ofFlag() {
        return CellSnapshot.of(CellSnapshotStatus.FLAG, 0);
    }

    public static CellSnapshot ofLandMine() {
        return CellSnapshot.of(CellSnapshotStatus.LAND_MINE, 0);
    }

    public static CellSnapshot ofNumber(int nearbyLandMineCount) {
        return CellSnapshot.of(CellSnapshotStatus.NUMBER, nearbyLandMineCount);
    }

    public static CellSnapshot ofUnchecked() {
        return CellSnapshot.of(CellSnapshotStatus.UNCHECKED, 0);
    }

    public boolean isSameStatus(CellSnapshotStatus currentStatus) {
        return status == currentStatus;
    }

    public CellSnapshotStatus getStatus() {
        return status;
    }

    public int getNearbyLandMineCount() {
        return nearbyLandMineCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellSnapshot snapshot = (CellSnapshot) o;
        return nearbyLandMineCount == snapshot.nearbyLandMineCount && status == snapshot.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nearbyLandMineCount);
    }
}
