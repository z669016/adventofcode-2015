package com.putoet.day24;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SumCombinationsTest {
    @Test
    void error() {
        assertThrows(AssertionError.class, () -> SumCombinations.combinations(0, List.of(), 0));
        assertThrows(AssertionError.class, () -> SumCombinations.combinations(3, List.of(5, 1, 2, 3), 0));
    }

    @Test
    void orderedList() {
        final var list = List.of(2, 1, 5, 4, 3, 7, 10, 11, 9, 8);
        final var ordered = SumCombinations.orderedList(list);
        assertEquals(List.of(11, 10, 9, 8, 7, 5, 4, 3, 2, 1), ordered);
        assert list != ordered;
    }

    @Test
    void combinations() {
        assertEquals(List.of(), SumCombinations.combinations(3, List.of(), 0));
        assertEquals(List.of(), SumCombinations.combinations(4, List.of(1, 2), 0));
        assertEquals(List.of(List.of(2, 1)), SumCombinations.combinations(3, List.of(1, 2), 0));
    }

    @Test
    void combinationsWithRemainder() {
        assertEquals(List.of(List.of(3), List.of(2, 1)), SumCombinations.combinations(3, List.of(1, 2, 3), 3));
        assertEquals(List.of(List.of(2, 1), List.of(2, 1), List.of(2, 1), List.of(2, 1)), SumCombinations.combinations(3, List.of(1, 2, 2, 1), 3));

        final var combinations = SumCombinations.combinations(20, List.of(1, 2, 3, 4, 5, 7, 8, 10), 20);
        assertEquals(12, combinations.size());
    }
}