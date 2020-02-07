package com.putoet.day17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationCalculator {
    public static List<List<Integer>> combinations(int sum, List<Integer> numbers) {
        assert sum > 0;
        assert numbers != null && numbers.size() > 0;

        numbers = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        final List<List<Integer>> solutions = new ArrayList<>();
        while (sum(numbers) >= sum) {
            final List<Integer> solution = new ArrayList<>();
            combinations(solutions, solution, sum, numbers);
            numbers = tail(numbers);
        }
        return solutions;
    }

    private static void combinations(List<List<Integer>> solutions, List<Integer> solution, int sum, List<Integer> numbers) {
        if (numbers.size() == 0)
            return;

        final int head = head(numbers);
        List<Integer> tail = tail(numbers);
        if (sum(solution) + head <= sum) {
            solution.add(head);

            if (sum(solution) == sum) {
                solutions.add(solution);
                return;
            }

            while (sum(solution) + sum(tail) >= sum) {
                final List<Integer> subSolution = new ArrayList<>(solution);
                combinations(solutions, subSolution, sum, tail);
                tail = tail(tail);
            }
        }
    }

    private static Integer head(List<Integer> list) {
        return list.get(0);
    }

    private static List<Integer> tail(List<Integer> list) {
        return list.stream().skip(1).collect(Collectors.toList());
    }

    private static int sum(List<Integer> list) {
        return list.stream().mapToInt(i -> i).sum();
    }
}
