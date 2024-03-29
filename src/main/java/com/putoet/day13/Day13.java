package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;
import org.paukov.combinatorics3.Generator;

import java.util.Arrays;

public class Day13 {
    public static void main(String[] args) {
        final var input = ResourceLines.list("/day13.txt");

        Timer.run(() -> optimize(HappinessMap.of(input)));
        Timer.run(() -> optimize(HappinessMap.ofIncludingMyself(input)));
    }

    private static void optimize(HappinessMap map) {
        final var persons = map.persons().toArray(String[]::new);
        final var optimized =  Generator.permutation(persons).simple().stream()
                .map(map::happiness)
                .mapToInt(i -> i)
                .max()
                .orElseThrow();

        System.out.println("Optimal happiness for " + Arrays.toString(persons) + " is " + optimized);
    }
}
