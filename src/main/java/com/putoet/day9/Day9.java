package com.putoet.day9;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;
import org.paukov.combinatorics3.Generator;

import java.util.Arrays;

public class Day9 {
    public static void main(String[] args) {
        final var distances = Timer.run(() -> {
            final var descriptions = ResourceLines.list("/day9.txt");
            final var map = DistanceMap.fromDescriptions(descriptions);
            final var cities = map.cities().toArray(String[]::new);
            return Generator.permutation(cities).simple().stream()
                    .map(map::routeDistance)
                    .mapToInt(i -> i)
                    .toArray();
        });

        final var minDistance = Arrays.stream(distances).min();
        if (minDistance.isPresent())
            System.out.println("The shortest route is: " + minDistance.getAsInt());
        else
            System.out.println("Hmmm ... I fucked up somewhere!");

        final var maxDistance = Arrays.stream(distances).max();
        if (maxDistance.isPresent())
            System.out.println("The longest route is: " + maxDistance.getAsInt());
        else
            System.out.println("Hmmm ... I fucked up somewhere else!");
    }
}
