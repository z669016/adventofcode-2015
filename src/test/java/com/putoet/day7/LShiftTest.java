package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LShiftTest {
    @Test
    public void get() {
        final Wire a = new Wire("a", () -> 0b0011);
        final Wire b = new Wire("a", () -> 2);
        final LShift lshift = new LShift("a LSHIFT b", a, b);
        assertEquals(0b1100, lshift.get());
    }

    @Test
    public void getOverFlow() {
        final Wire a = new Wire("a", () -> 0b0011_0000_0000_0000);
        final Wire b = new Wire("a", () -> 3);
        final LShift lshift = new LShift("a LSHIFT b", a, b);
        assertEquals(0b1000_0000_0000_0000, lshift.get());
    }
}