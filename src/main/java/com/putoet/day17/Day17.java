package com.putoet.day17;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.OptionalInt;

public class Day17 {
    public static void main(String[] args) {
        final List<Integer> numbers = ResourceLines.stream("/day17.txt")
                .map(Integer::valueOf)
                .toList();

        final List<List<Integer>> solutions = ContainerCombinations.combinations(150, numbers);
        System.out.println("Number of combinations found is " + solutions.size());

        final OptionalInt minimumSize = solutions.stream()
                .mapToInt(List::size)
                .min();
        if (minimumSize.isPresent()) {
            final long count = solutions.stream()
                    .filter(list -> list.size() == minimumSize.getAsInt())
                    .count();

            System.out.println("Minimum size is " + minimumSize.getAsInt() + " which can be filled in " + count + " different ways.");
        }
    }
}