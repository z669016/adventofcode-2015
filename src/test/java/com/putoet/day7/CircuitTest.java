package com.putoet.day7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {
    private Circuit circuit = null;
    private final List<String> wirering = List.of(
            "123 -> x",
            "456 -> y",
            "x AND y -> d",
            "x OR y -> e",
            "x LSHIFT 2 -> f",
            "y RSHIFT 2 -> g",
            "NOT x -> h",
            "NOT y -> i");

    @BeforeEach
    public void setup() {
        circuit = Circuit.from(wirering);
    }

    @Test
    public void from() {
        assertEquals(8, circuit.encodingsCount());
        assertEquals("123", circuit.encoding("x").get());
        assertEquals("456", circuit.encoding("y").get());
        assertEquals("x AND y", circuit.encoding("d").get());
        assertEquals("x OR y", circuit.encoding("e").get());
        assertEquals("x LSHIFT 2", circuit.encoding("f").get());
        assertEquals("y RSHIFT 2", circuit.encoding("g").get());
        assertEquals("NOT x", circuit.encoding("h").get());
        assertEquals("NOT y", circuit.encoding("i").get());
        assertFalse(circuit.encoding("bla").isPresent());
    }

    @Test
    public void getUnknown() {
        assertThrows(IllegalStateException.class, () -> circuit.get("bla"));
    }

    @Test
    public void getXY() {
        assertEquals(123, circuit.get("x"));
        assertEquals(456, circuit.get("y"));
    }

    @Test
    public void getHI() {
        assertEquals(65412, circuit.get("h"));
        assertEquals(65079, circuit.get("i"));
    }

    @Test
    public void getF() {
        assertEquals(492, circuit.get("f"));
    }

    @Test
    public void getG() {
        assertEquals(114, circuit.get("g"));
    }

    @Test
    public void getD() {
        assertEquals(72, circuit.get("d"));
    }

    @Test
    public void getE() {
        assertEquals(507, circuit.get("e"));
    }
}