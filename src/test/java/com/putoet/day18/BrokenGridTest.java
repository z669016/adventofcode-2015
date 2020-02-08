package com.putoet.day18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrokenGridTest {
    private static final List<String> lines = List.of(
            "##.#.#",
            "...##.",
            "#....#",
            "..#...",
            "#.#..#",
            "####.#");
    private Grid grid;

    @BeforeEach
    void setup() {
        grid = BrokenGrid.fromLines(lines);
    }

    @Test
    void next() {
        final List<String> expected = List.of(
                "#.##.#",
                "####.#",
                "...##.",
                "......",
                "#...#.",
                "#.####");
        assertEquals(expected, grid.next().toList());
    }

    @Test
    void next5() {
        final List<String> expected = List.of(
                "##.###",
                ".##..#",
                ".##...",
                ".##...",
                "#.#...",
                "##...#");
        assertEquals(expected, grid.next().next().next().next().next().toList());
    }
}