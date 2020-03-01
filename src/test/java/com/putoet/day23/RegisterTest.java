package com.putoet.day23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    private Register reg;

    @BeforeEach
    void setup() {
        reg = new Register("R");
    }

    @Test
    void empty() {
        assertEquals(0, reg.get());
    }

    @Test
    void acceptNegative() {
        reg.accept(-7);
        assertEquals(7, reg.get());
    }

    @Test
    void acceptPositive() {
        reg.accept(3);
        assertEquals(3, reg.get());
    }
}