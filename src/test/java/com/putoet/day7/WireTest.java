package com.putoet.day7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WireTest {
    private static final String NAMED_WIRE = "NAMED_WIRE";
    private Wire wire = null;

    @BeforeEach
    public void setup () {
        wire = new Wire(NAMED_WIRE, () -> 12);
    }

    @Test
    public void name() {
        assertEquals(NAMED_WIRE.toLowerCase(), wire.name());
    }

    @Test
    public void get() {
        assertEquals(12, wire.get());
    }
}