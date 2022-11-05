package com.putoet.day17;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerCombinationsTest {
    private static final List<Integer> dataSet = List.of(5, 5, 10, 15, 20);
    private static final List<Integer> s1 = List.of(20, 5);
    private static final List<Integer> s2 = List.of(20, 5);
    private static final List<Integer> s3 = List.of(15, 10);
    private static final List<Integer> s4 = List.of(15, 5, 5);
    private static final List<List<Integer>> solutions = List.of(s1, s2, s3, s4);


    @Test
    void combinations() {
        assertEquals(solutions, ContainerCombinations.combinations(25, dataSet));
    }
}