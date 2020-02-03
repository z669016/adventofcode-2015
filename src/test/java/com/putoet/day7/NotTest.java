package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotTest {
    @Test
    public void get() {
        final Wire a = new Wire("a", () -> 1);
        final Not not = new Not("NOT a", a);
        assertEquals(65534, not.get());
    }
}