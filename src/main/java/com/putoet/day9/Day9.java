package com.putoet.day9;

import utilities.Permutator;
import utilities.ResourceLines;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class Day9 {
    public static void main(String[] args) {
        final List<String> descriptions = ResourceLines.list("/day9.txt");
        final DistanceMap map = DistanceMap.fromDescriptions(descriptions);
        final List<String> cities = new ArrayList<>(map.cities());
        final Permutator<String> permutator = new Permutator<>();
        final List<List<String>> routes = permutator.permute(cities);

        final OptionalInt minDistance = routes.stream()
                .map(map::routeDistance)
                .mapToInt(i -> i)
                .min();
        if (minDistance.isPresent())
            System.out.println("The shortest route is: " + minDistance.getAsInt());
        else
            System.out.println("Hmmm ... I fucked up somewhere!");

        final OptionalInt maxDistance = routes.stream()
                .map(map::routeDistance)
                .mapToInt(i -> i)
                .max();
        if (maxDistance.isPresent())
            System.out.println("The longest route is: " + maxDistance.getAsInt());
        else
            System.out.println("Hmmm ... I fucked up somewhere else!");
    }
}
