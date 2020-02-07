package com.putoet.day17;

import utilities.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day17 {
    public static void main(String[] args) {
        final List<Integer> numbers = ResourceLines.list("/day17.txt").stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        final List<List<Integer>> solutions = CombinationCalculator.combinations(150, numbers);
        System.out.println("Number of combinations found is " + solutions.size());
    }
}