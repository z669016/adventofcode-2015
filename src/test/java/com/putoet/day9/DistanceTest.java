package com.putoet.day9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {
    private Distance distance = null;

    @BeforeEach
    public void setup() {
        distance = new Distance("Faerun", "Tristram", 65);
    }

    @Test
    void fromDescription() {
        final var other = Distance.of("Faerun to Tristram = 65");
        assertEquals(distance, other);
    }

    @Test
    void fromInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> Distance.of("Faerun to Tristram 65"));
    }

    @Test
    void between() {
        assertTrue(distance.between("Tristram", "Faerun"));
        assertTrue(distance.between("Faerun", "Tristram"));
        assertFalse(distance.between("Tristram", "Amsterdam"));
        assertFalse(distance.between("Amsterdam", "Faerun"));
    }
}