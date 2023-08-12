package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import org.paukov.combinatorics3.Generator;

import java.util.Arrays;
import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        final List<String> input = ResourceLines.list("/day13.txt");
        final HappinessMap map = HappinessMap.fromList(input);
        optimize(map);

        final HappinessMap mapIncludingMyself = HappinessMap.fromListIncludingMyself(input);
        optimize(mapIncludingMyself);
    }

    private static void optimize(HappinessMap map) {
        final String[] persons = map.persons().toArray(String[]::new);

        int optimized =  Generator.permutation(persons).simple().stream()
                .map(map::happiness)
                .mapToInt(i -> i)
                .max()
                .orElseThrow();
        System.out.println("Optimal happiness for " + Arrays.toString(persons) + " is " + optimized);
    }
}
