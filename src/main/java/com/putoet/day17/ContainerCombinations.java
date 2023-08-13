package com.putoet.day17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ContainerCombinations {
    public static List<List<Integer>> combinations(int sum, List<Integer> numbers) {
        assert sum > 0;
        assert numbers != null && !numbers.isEmpty();

        numbers = numbers.stream().sorted(Comparator.reverseOrder()).toList();

        final var solutions = new ArrayList<List<Integer>>();
        while (sum(numbers) >= sum) {
            final var solution = new ArrayList<Integer>();
            combinations(solutions, solution, sum, numbers);
            numbers = tail(numbers);
        }
        return solutions;
    }

    private static void combinations(List<List<Integer>> solutions, List<Integer> solution, int sum, List<Integer> numbers) {
        if (numbers.isEmpty())
            return;

        final var head = head(numbers);
        var tail = tail(numbers);
        if (sum(solution) + head <= sum) {
            solution.add(head);

            if (sum(solution) == sum) {
                solutions.add(solution);
                return;
            }

            while (sum(solution) + sum(tail) >= sum) {
                final var subSolution = new ArrayList<>(solution);
                combinations(solutions, subSolution, sum, tail);
                tail = tail(tail);
            }
        }
    }

    private static Integer head(List<Integer> list) {
        return list.get(0);
    }

    private static List<Integer> tail(List<Integer> list) {
        return list.stream().skip(1).toList();
    }

    private static int sum(List<Integer> list) {
        return list.stream().mapToInt(i -> i).sum();
    }
}
