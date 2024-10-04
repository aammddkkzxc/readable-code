package cleancode.minesweeper.tobe.position;

import java.util.Objects;

public class CellPosition {

    private final int rowIndex;
    private final int columnIndex;

    public CellPosition(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || columnIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다");
        }

        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public static CellPosition of(int rowIndex, int columnIndex) {
        return new CellPosition(rowIndex, columnIndex);
    }

    public CellPosition calculatePositionBy(RelativePosition relativePosition) {
        if (canCalculatePositionBy(relativePosition)) {
            return CellPosition.of(
                    this.rowIndex + relativePosition.getDeltaRow(),
                    this.columnIndex + relativePosition.getDeltaCol()
            );
        }

        throw new IllegalArgumentException("움직일수 있는 좌표가 아닙니다");
    }

    public boolean canCalculatePositionBy(RelativePosition relativePosition) {
        return rowIndex + relativePosition.getDeltaRow() >= 0
                && columnIndex + relativePosition.getDeltaCol() >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return rowIndex == that.rowIndex && columnIndex == that.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }

    public boolean isRowIndexMoreThanEqual(int rowSize) {
        return rowIndex >= rowSize;
    }

    public boolean isColIndexMoreThanEqual(int colSize) {
        return columnIndex >= colSize;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return columnIndex;
    }

    public boolean isRowIndexLessThan(int rowIndex) {
        return this.rowIndex < rowIndex;
    }

    public boolean isColIndexLessThan(int colIndex) {
        return this.columnIndex < colIndex;
    }
}
