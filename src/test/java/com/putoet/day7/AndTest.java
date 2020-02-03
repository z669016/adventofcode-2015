package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AndTest {
    @Test
    public void get() {
        final Wire a = new Wire("a", () -> 0b0101);
        final Wire b = new Wire("a", () -> 0b0100);
        final And and = new And("a AND b", a, b);
        assertEquals(0b0100, and.get());
    }
}