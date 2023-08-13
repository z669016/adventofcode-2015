package com.putoet.day18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LightGridTest {
    private static final List<String> lines = List.of(
            ".#.#.#",
            "...##.",
            "#....#",
            "..#...",
            "#.#..#",
            "####..");
    private LightGrid lightGrid;

    @BeforeEach
    void setup() {
        lightGrid = LightGrid.of(lines);
    }

    @Test
    void burningLights() {
        assertEquals(15, lightGrid.burningLights());
    }

    @Test
    void next() {
        final var nextLightGrid = lightGrid.next();
        assertEquals(11, nextLightGrid.burningLights());
    }

    @Test
    void next4() {
        final var nextLightGrid = lightGrid.next().next().next().next();
        assertEquals(4, nextLightGrid.burningLights());
    }
}