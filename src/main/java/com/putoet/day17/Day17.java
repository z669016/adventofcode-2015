package com.putoet.day17;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day17 {
    public static void main(String[] args) {
        final var numbers = ResourceLines.stream("/day17.txt")
                .map(Integer::valueOf)
                .toList();

        final var solutions = Timer.run(() -> {
            final var combinations = ContainerCombinations.combinations(150, numbers);
            System.out.println("Number of combinations found is " + combinations.size());
            return combinations;
        });

        Timer.run(() -> {
            final var minimumSize = solutions.stream()
                    .mapToInt(List::size)
                    .min()
                    .orElseThrow();
            final var count = solutions.stream()
                    .filter(list -> list.size() == minimumSize)
                    .count();

            System.out.println("Minimum size is " + minimumSize + " which can be filled in " + count + " different ways.");
            return null;
        });
    }
}