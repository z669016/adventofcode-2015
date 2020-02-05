package com.putoet.day13;

import utilities.Permutator;
import utilities.ResourceLines;

import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        final List<String> descriptions = ResourceLines.list("/day13.txt");
        final HappinessMap map = HappinessMap.fromList(descriptions);

        final List<String> persons = List.of("Carol", "David", "Alice", "Bob");
        System.out.println("Actual happiness based on provided config is " + map.happiness(persons));

        final Permutator<String> permutator = new Permutator<>();
        final List<List<String>> permutations = permutator.permute(persons);
        int optimized =  permutations.stream().map(map::happiness)
                .mapToInt(i -> i)
                .max().getAsInt();
        System.out.println("Optimal happiness based on config is " + optimized);
        System.out.println("Change of happiness based on provided config is " + (330 - optimized));
    }
}
