package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RShiftTest {
    @Test
    public void get() {
        final var a = new Wire("a", () -> 0b1100);
        final var b = new Wire("a", () -> 3);
        final var rshift = new RShift("a RSHIFT b", a, b);
        assertEquals(0b0001, rshift.get());
    }
}