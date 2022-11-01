package com.putoet.day3;

public enum Direction {
    NORTH, WEST, SOUTH, EAST;

    public static Direction valueOf(int symbol) {
        return switch (symbol) {
            case '^' -> NORTH;
            case '>' -> WEST;
            case 'v' -> SOUTH;
            case '<' -> EAST;
            default -> throw new IllegalArgumentException("Invalid direction '" + symbol + "'");
        };
    }
}
