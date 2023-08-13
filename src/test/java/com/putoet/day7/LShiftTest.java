package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LShiftTest {
    @Test
    public void get() {
        final var a = new Wire("a", () -> 0b0011);
        final var b = new Wire("a", () -> 2);
        final var lshift = new LShift("a LSHIFT b", a, b);
        assertEquals(0b1100, lshift.get());
    }

    @Test
    public void getOverFlow() {
        final var a = new Wire("a", () -> 0b0011_0000_0000_0000);
        final var b = new Wire("a", () -> 3);
        final var lshift = new LShift("a LSHIFT b", a, b);
        assertEquals(0b1000_0000_0000_0000, lshift.get());
    }
}