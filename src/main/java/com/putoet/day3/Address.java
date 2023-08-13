package com.putoet.day3;

record Address(int x, int y) {
    Address() {
        this(0, 0);
    }

    Address move(Direction direction) {
        return switch (direction) {
            case NORTH -> new Address(x, y + 1);
            case WEST -> new Address(x + 1, y);
            case SOUTH -> new Address(x, y - 1);
            case EAST -> new Address(x - 1, y);
        };
    }

    static Address startAddress() {
        return new Address();
    }
}
