package com.putoet.day17;

import utilities.ResourceLines;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Day17 {
    public static void main(String[] args) {
        final List<Integer> numbers = ResourceLines.list("/day17.txt").stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<List<Integer>> solutions = CombinationCalculator.combinations(150, numbers);
        System.out.println("Number of combinations found is " + solutions.size());

        final OptionalInt minimumSize = solutions.stream()
                .mapToInt(List::size)
                .min();
        if (minimumSize.isPresent()) {
            solutions = solutions.stream()
            .filter(list -> list.size() == minimumSize.getAsInt())
            .collect(Collectors.toList());

            System.out.println("Minimum size is " + minimumSize.getAsInt() + " which can be filled in " + solutions.size() + " different ways.");
        }
    }
}