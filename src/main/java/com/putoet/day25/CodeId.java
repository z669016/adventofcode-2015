package com.putoet.day25;

class CodeId {
    public static long START_VALUE = 20151125;
    public static long MULTIPLIER = 252533;
    public static long DIVIDER = 33554393;

    private CodeId() {
    }

    public static long valueAtCell(long row, long column) {
        return valueAtIndex(cell(row, column));
    }

    public static long valueAtIndex(long index) {
        long value = START_VALUE;
        for (long idx = 1; idx < index; idx++) {
            value = (value * MULTIPLIER) % DIVIDER;
        }

        return value;
    }

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
