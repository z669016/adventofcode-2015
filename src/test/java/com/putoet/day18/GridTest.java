package com.putoet.day18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private static final List<String> lines = List.of(
            ".#.#.#",
            "...##.",
            "#....#",
            "..#...",
            "#.#..#",
            "####..");
    private Grid grid;

    @BeforeEach
    void setup() {
        grid = Grid.fromLines(lines);
    }

    @Test
    void fromLines() {
        assertEquals(lines, grid.toList());
    }

    @Test
    void burningLights() {
        assertEquals(15, grid.burningLights());
    }

    @Test
    void next() {
        final List<String> expected = List.of(
                "..##..",
                "..##.#",
                "...##.",
                "......",
                "#.....",
                "#.##..");

        final Grid nextGrid = grid.next();
        assertEquals(11, nextGrid.burningLights());
        assertEquals(expected, nextGrid.toList());
    }

    @Test
    void next4() {
        final List<String> expected = List.of(
                "......",
                "......",
                "..##..",
                "..##..",
                "......",
                "......");

        final Grid nextGrid = grid.next().next().next().next();
        assertEquals(4, nextGrid.burningLights());
        assertEquals(expected, nextGrid.toList());
    }
}