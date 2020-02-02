package com.putoet.day3;

import org.junit.jupiter.api.Test;

import static com.putoet.day3.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void valueOf() {
        assertEquals(NORTH, Direction.valueOf('^'));
        assertEquals(WEST, Direction.valueOf('>'));
        assertEquals(SOUTH, Direction.valueOf('v'));
        assertEquals(EAST, Direction.valueOf('<'));
    }
}