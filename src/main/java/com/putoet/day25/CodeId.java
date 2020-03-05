package com.putoet.day25;

public class CodeId {
    public static long START_VALUE = 20151125;
    public static long MULTIPLICATOR = 252533;
    public static long DIVIDER = 33554393;

    private final long start;
    private final long multiplicator;
    private final long divider;

    public CodeId() {
        this.start = START_VALUE;
        this.multiplicator = MULTIPLICATOR;
        this.divider = DIVIDER;
    }

    public CodeId(long start, long multiplicator, long divider) {
        this.start = start;
        this.multiplicator = multiplicator;
        this.divider = divider;
    }


    public long valueAtCell(long row, long column) {
        return valueAtIndex(cell(row, column));
    }

    public long valueAtIndex(long index) {
        long value = start;
        for (long idx = 1; idx < index; idx++) {
            value = (value * multiplicator) % divider;
        }

        return value;
    }

    public long start() { return start; }

    public static long cell(long row, long column) {
        assert row > 0;
        assert column > 0;

        long cell = rowStart(row);
        for (long idx = 0; idx < column - 1; idx++) {
            cell += idx + row + 1;
        }
        return cell;
    }

    protected static long rowStart(long row) {
        long rowStart = 1;
        for (long idx = 0; idx < row; idx++) {
            rowStart += idx;
        }
        return rowStart;
    }


}
