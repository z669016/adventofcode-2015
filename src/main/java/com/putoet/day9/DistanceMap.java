package com.putoet.day9;

import java.util.*;
import java.util.stream.Collectors;

public class DistanceMap {
    final private List<Distance> distances = new ArrayList<>();

    private DistanceMap() {}

    public Optional<Integer> distance(String city1,String  city2) {
        final Optional<Distance> distance = distances.stream().filter(d -> d.between(city1, city2)).findFirst();
        return distance.map(Distance::distance);
    }

    /**
     * Create a DistanceMap from a list of distance descriptions (see Distance for the correct format)
     * @param descriptions List of Strings whith each string containing a valid formed distance description
     * @return DistanceMap
     */
    public static DistanceMap fromDescriptions(List<String> descriptions) {
        final DistanceMap distanceMap = new DistanceMap();
        descriptions.forEach(description -> distanceMap.distances.add(Distance.fromDescription(description)));

        return distanceMap;
    }

    /**
     * Create a Set of all the cities on the map
     * @return Set of cities (String)
     */
    public Set<String> cities() {
        return distances.stream()
                .map(d -> Set.of(d.from(), d.to()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Calculate the distance from starting point (first element in the list) until the end (which could be the
     * beginning but doesn't have to). If the map doesnt contain info on the distance between two consecutive points
     * in the list, this methos will throw an IllegalArgumentException. City names must match the names of the city
     * in the map (so correct upper/lower case usage).
     *
     * @param route List of city names (Strings)
     * @return int The sum of the individual distances
     */
    public int routeDistance(List<String> route) {
        int routeDistance = 0;
        for (int idx = 0; idx < route.size() - 1; idx++) {
            final String city1 = route.get(idx);
            final String city2 = route.get(idx+1);

            final Optional<Integer> distance = distance(city1, city2);
            if (distance.isEmpty())
                throw new IllegalArgumentException("No distance available from " + city1 + " to " + city2);

            routeDistance += distance.get();
        }

        return routeDistance;
    }
}
