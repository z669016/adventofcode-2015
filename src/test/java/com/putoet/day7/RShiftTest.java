package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RShiftTest {
    @Test
    public void get() {
        final Wire a = new Wire("a", () -> 0b1100);
        final Wire b = new Wire("a", () -> 3);
        final RShift rshift = new RShift("a RSHIFT b", a, b);
        assertEquals(0b0001, rshift.get());
    }
}