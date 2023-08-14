package com.putoet.day24;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeightBalancerTest {
    private final List<Integer> WEIGHTS = new ArrayList<>(List.of(1, 2, 3, 4, 5, 7, 8, 9, 10, 11));

    @Test
    void construct() {
        assertThrows(AssertionError.class, () -> new WeightBalancer(1, WEIGHTS));
        assertThrows(AssertionError.class, () -> new WeightBalancer(3, List.of(0, 1)));
        assertThrows(IllegalArgumentException.class, () -> new WeightBalancer(3, List.of(1, 2, 4)));
    }

    @Test
    void averageWeight() {
        final var wb = new WeightBalancer(3, WEIGHTS);
        assertEquals(3, wb.containers());
        assertEquals(20, wb.averageWeight());
        assertEquals(WEIGHTS, wb.packages());

        // The next assertion only works if the WEIGHTS is an ArrayList (mutable) list. When you change the type
        // e.g. WEIGHTS = List.of(...)) instead of WEIGHTS = new ArrayList<>(List.of(... )), then all of a sudden
        // the copyOf inside the constructor won;t make a copy but will return a reference to the same unmodifiable
        // list ... which will cause this assertion to FAIL
        assertNotSame(WEIGHTS, wb.packages());
    }

    @Test
    void loadBalancing() {
        final var wb = new WeightBalancer(3, WEIGHTS);
        final var cargoList = wb.loadBalancing();

        System.out.println(cargoList.size());
        cargoList.forEach(cargo -> System.out.println("(QE=" + WeightBalancer.quantumEntanglement(cargo) + ") " + cargo));

        assertEquals(12, cargoList.size());
    }

    @Test
    void quantumEntanglement() {
        assertEquals(99, WeightBalancer.quantumEntanglement(List.of(11, 9)));
        assertEquals(90, WeightBalancer.quantumEntanglement(List.of(10, 9, 1)));
        assertEquals(210, WeightBalancer.quantumEntanglement(List.of(10, 7, 3)));
    }
}