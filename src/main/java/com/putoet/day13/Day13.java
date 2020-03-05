package com.putoet.day13;

import utilities.Permutator;
import utilities.ResourceLines;

import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        final HappinessMap map = HappinessMap.fromList(ResourceLines.list("/day13.txt"));
        optimize(map);

        final HappinessMap mapIncludingMyself = HappinessMap.fromListIncludingMyself(ResourceLines.list("/day13.txt"));
        optimize(mapIncludingMyself);
    }

    private static void optimize(HappinessMap map) {
        final List<String> persons = List.copyOf(map.persons());

        final Permutator<String> permutator = new Permutator<>();
        final List<List<String>> permutations = permutator.permute(persons);
        int optimized =  permutations.stream().map(map::happiness)
                .mapToInt(i -> i)
                .max().getAsInt();
        System.out.println("Optimal happiness for " + persons + " is " + optimized);
    }
}
