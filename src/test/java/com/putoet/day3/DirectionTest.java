package com.putoet.day3;

import org.junit.jupiter.api.Test;

import static com.putoet.day3.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void valueOf() {
        assertEquals(NORTH, Direction.of('^'));
        assertEquals(WEST, Direction.of('>'));
        assertEquals(SOUTH, Direction.of('v'));
        assertEquals(EAST, Direction.of('<'));
    }
}