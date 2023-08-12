package com.putoet.day3;

public record Address(int x, int y) {
    public Address() {
        this(0, 0);
    }

    public Address move(Direction direction) {
        return switch (direction) {
            case NORTH -> new Address(x, y + 1);
            case WEST -> new Address(x + 1, y);
            case SOUTH -> new Address(x, y - 1);
            case EAST -> new Address(x - 1, y);
        };
    }

    public static Address startAddress() {
        return new Address();
    }
}
