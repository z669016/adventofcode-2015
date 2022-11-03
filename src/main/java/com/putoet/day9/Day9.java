package com.putoet.day9;

import com.putoet.resources.ResourceLines;
import com.putoet.statistics.Permutator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 * Using a brute force approach ... load all distances in a map object, then get all cities on the map (in a Set),
 * create a permutation of all cities (which provides basically a list of all possible routes), and then find the
 * min and max distance for each route.
 */
public class Day9 {
    public static void main(String[] args) {
        final List<String> descriptions = ResourceLines.list("/day9.txt");
        final DistanceMap map = DistanceMap.fromDescriptions(descriptions);
        final List<String> cities = new ArrayList<>(map.cities());
        final Permutator<String> permutator = new Permutator<>();
        final List<List<String>> routes = permutator.permute(cities);
        final int[] distances = routes.stream()
                .map(map::routeDistance)
                .mapToInt(i -> i)
                .toArray();

        final OptionalInt minDistance = Arrays.stream(distances).min();
        if (minDistance.isPresent())
            System.out.println("The shortest route is: " + minDistance.getAsInt());
        else
            System.out.println("Hmmm ... I fucked up somewhere!");

        final OptionalInt maxDistance = Arrays.stream(distances).max();
        if (maxDistance.isPresent())
            System.out.println("The longest route is: " + maxDistance.getAsInt());
        else
            System.out.println("Hmmm ... I fucked up somewhere else!");
    }
}
