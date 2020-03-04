package com.putoet.day24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class to get combinations of integers from a list that sum up to a specific value
 */
public class SumCombinations {
    /**
     * Method to find all possible combinations of integers from the provided list, that sum up to a specific
     * value (making). If an empty is provided, an empty list will be returned. The provided list can be immutable.
     *
     * The list may not be null
     * No value in the list may be bigger than the 'making' value
     * The 'making' value may not be 0
     *
     * @param making int The sum value we're looking for
     * @param list List<Integer> The list of Integer values
     * @param  remainder int the sum of the remaing elements in the list must be equal to this value
     * @return a List of List of Integer
     */
    public static List<List<Integer>> combinations(int making, List<Integer> list, int remainder) {
        assert making != 0;
        assert list != null;
        assert list.stream().noneMatch(element -> element > making);

        if (list.size() == 0) {
            return List.of();
        }

        return combinationsOnOrderedList(making, orderedList(list), remainder);
    }

    private static List<List<Integer>> combinationsOnOrderedList(int making, List<Integer> orderedList, int remainder) {
        final List<List<Integer>> answer = new ArrayList<>();

        combinationsOnOrderedList(answer, new ArrayList<>(), 0, making, orderedList, remainder);
        return answer;
    }

    private static void combinationsOnOrderedList(List<List<Integer>> answer, List<Integer> group, int offset, int making, List<Integer> orderedList, int remainder) {
        final int sum = sum(group);

        if (sum == making) {
            final int remaining = sum(orderedList);
            if (remaining == remainder)
                answer.add(group);

            return;
        }

        while (offset < orderedList.size()) {
            final int value = orderedList.get(offset);
            if (sum+ value <= making) {
                final List<Integer> copyOrderedList = new ArrayList<>(orderedList);
                final List<Integer> copyGroup = new ArrayList<>(group);
                
                copyGroup.add(copyOrderedList.remove(offset));
                combinationsOnOrderedList(answer, copyGroup, offset, making, copyOrderedList, remainder);
            }

            offset++;
        }
    }

    /**
     * Create a decending mutable copy of the provided list  of integer values
     * @param list List<Integer>
     * @return List<Integer>
     */
    public static List<Integer> orderedList(List<Integer> list) {
        final List<Integer> copy = new ArrayList<>(list);
        copy.sort(Comparator.reverseOrder());
        return copy;
    }

    /**
     * Calculate the sum of the Integer values of the list
     * @param list List<Integer>
     * @return int
     */
    public static int sum(List<Integer> list) {
        if (list.size() == 0)
            return 0;

        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
