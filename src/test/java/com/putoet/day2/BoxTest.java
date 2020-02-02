package com.putoet.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void wrapSize() {
        assertEquals(52, Box.of("2x3x4").wrapSize());
        assertEquals(42, Box.of("1x1x10").wrapSize());
    }

    @Test
    void additionalWrap() {
        assertEquals(6, Box.of("2x3x4").additionalWrap());
        assertEquals(1, Box.of("1x1x10").additionalWrap());
    }

    @Test
    void totalWrap() {
        assertEquals(58, Box.of("2x3x4").totalWrap());
        assertEquals(43, Box.of("1x1x10").totalWrap());
    }

    @Test
    void ribbonLength() {
        assertEquals(10, Box.of("2x3x4").ribbonLength());
        assertEquals(4, Box.of("1x1x10").ribbonLength());
    }

    @Test
    void ribbonBowLength() {
        assertEquals(24, Box.of("2x3x4").ribbonBowLength());
        assertEquals(10, Box.of("1x1x10").ribbonBowLength());
    }

    @Test
    void totalRibbon() {
        assertEquals(34, Box.of("2x3x4").totalRibbon());
        assertEquals(14, Box.of("1x1x10").totalRibbon());
    }
}

