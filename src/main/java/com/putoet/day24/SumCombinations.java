package com.putoet.day24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class SumCombinations {
    public static List<List<Integer>> combinations(int making, List<Integer> list, int remainder) {
        assert making != 0;
        assert list != null;
        assert list.stream().noneMatch(element -> element > making);

        if (list.isEmpty()) {
            return List.of();
        }

        return combinationsOnOrderedList(making, orderedList(list), remainder);
    }

    private static List<List<Integer>> combinationsOnOrderedList(int making, List<Integer> orderedList, int remainder) {
        final var answer = new ArrayList<List<Integer>>();
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
            if (sum + value <= making) {
                final var copyOrderedList = new ArrayList<>(orderedList);
                final var copyGroup = new ArrayList<>(group);
                
                copyGroup.add(copyOrderedList.remove(offset));
                combinationsOnOrderedList(answer, copyGroup, offset, making, copyOrderedList, remainder);
            }

            offset++;
        }
    }


    public static List<Integer> orderedList(List<Integer> list) {
        final var copy = new ArrayList<>(list);
        copy.sort(Comparator.reverseOrder());
        return copy;
    }

    public static int sum(List<Integer> list) {
        if (list.isEmpty())
            return 0;

        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
