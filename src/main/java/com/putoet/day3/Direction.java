package com.putoet.day3;

public enum Direction {
    NORTH, WEST, SOUTH, EAST;

    public static Direction valueOf(int symbol) {
        switch (symbol) {
            case '^': return NORTH;
            case '>': return WEST;
            case 'v': return SOUTH;
            case '<': return EAST;
            default:
                throw new IllegalArgumentException("Invalid direction '" + symbol + "'");
        }
    }
}
