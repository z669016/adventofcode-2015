package com.putoet.day24;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SumCombinationsTest {
    @Test
    void error() {
        assertThrows(AssertionError.class, () -> SumCombinations.combinations(0, List.of(), 0));
        assertThrows(AssertionError.class, () -> SumCombinations.combinations(1, null, 0));
        assertThrows(AssertionError.class, () -> SumCombinations.combinations(3, List.of(5, 1, 2, 3), 0));
    }

    @Test
    void orderedList() {
        final List<Integer> list = List.of(2, 1, 5, 4, 3, 7, 10, 11, 9, 8);
        final List<Integer> ordered = SumCombinations.orderedList(list);
        assertEquals(List.of(11, 10, 9, 8, 7, 5, 4, 3, 2, 1), ordered);
        assert list != ordered;
    }

    @Test
    void combinations() {
        assertEquals(List.of(), SumCombinations.combinations(3, List.of(), 0));

        assertEquals(List.of(), SumCombinations.combinations(4, List.of(1, 2), 0));

        assertEquals(List.of(List.of(2, 1)), SumCombinations.combinations(3, List.of(1, 2), 0));

        assertEquals(List.of(List.of(3), List.of(2, 1)), SumCombinations.combinations(3, List.of(1, 2, 3), 0));

        //assertEquals(List.of(List.of(3, 1), List.of(2, 2)), SumCombinations.combinations(4, List.of(1, 2, 3, 2), 0));

        //assertEquals(List.of(List.of(3, 1), List.of(3, 1), List.of(2, 2), List.of(2, 1, 1), List.of(2, 1, 1)), SumCombinations.combinations(4, List.of(1, 2, 1, 3, 2), 0));

        final List<List<Integer>> combinations = SumCombinations.combinations(20, List.of(1, 2, 3, 4, 5, 7, 8, 9, 10, 11), 0);
        //assertEquals(25, combinations.size());
        // combinations.forEach(System.out::println);
    }

    @Test
    void combinationsWithRemainder() {
        assertEquals(List.of(List.of(3), List.of(2, 1)), SumCombinations.combinations(3, List.of(1, 2, 3), 3));

        assertEquals(List.of(List.of(2, 1), List.of(2, 1), List.of(2, 1), List.of(2, 1)), SumCombinations.combinations(3, List.of(1, 2, 2, 1), 3));

        final List<List<Integer>> combinations = SumCombinations.combinations(20, List.of(1, 2, 3, 4, 5, 7, 8, 10), 20);
        // combinations.forEach(System.out::println);
        assertEquals(12, combinations.size());
    }
}