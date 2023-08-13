package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotTest {
    @Test
    public void get() {
        final var a = new Wire("a", () -> 1);
        final var not = new Not("NOT a", a);
        assertEquals(65534, not.get());
    }
}