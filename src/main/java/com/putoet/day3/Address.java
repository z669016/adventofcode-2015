package com.putoet.day3;

import java.util.Objects;

public class Address {
    private final int x;
    private final int y;

    private Address() {
        this.x = 0;
        this.y = 0;
    }

    private Address(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Address move(Direction direction) {
        return switch (direction) {
            case NORTH -> new Address(x, y + 1);
            case WEST -> new Address(x + 1, y);
            case SOUTH -> new Address(x, y - 1);
            case EAST -> new Address(x - 1, y);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return x == address.x &&
                y == address.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(x=%d,y=%d)", x, y);
    }

    public static Address startAddress() {
        return new Address();
    }
}
