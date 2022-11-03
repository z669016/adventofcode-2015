package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import com.putoet.statistics.Permutator;

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
        final List<String> persons = List.copyOf(map.persons());

        final Permutator<String> permutator = new Permutator<>();
        final List<List<String>> permutations = permutator.permute(persons);
        int optimized =  permutations.stream().map(map::happiness)
                .mapToInt(i -> i)
                .max()
                .orElseThrow();
        System.out.println("Optimal happiness for " + persons + " is " + optimized);
    }
}
