package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrTest {
    @Test
    public void get() {
        final var a = new Wire("a", () -> 0b0101);
        final var b = new Wire("a", () -> 0b0110);
        final var or = new Or("a OR b", a, b);
        assertEquals(0b0111, or.get());
    }
}