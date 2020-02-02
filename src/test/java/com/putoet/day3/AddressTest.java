package com.putoet.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void testEquals() {
        assertEquals(Address.startAddress(), Address.startAddress()
                .move(Direction.NORTH)
                .move(Direction.WEST)
                .move(Direction.SOUTH)
                .move(Direction.EAST));
    }
}