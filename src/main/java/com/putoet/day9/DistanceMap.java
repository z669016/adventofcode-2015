package com.putoet.day9;

import java.util.*;
import java.util.stream.Collectors;

class DistanceMap {
    final private List<Distance> distances = new ArrayList<>();

    private DistanceMap() {}

    public Optional<Integer> distance(String city1,String  city2) {
        final var distance = distances.stream().filter(d -> d.between(city1, city2)).findFirst();
        return distance.map(Distance::distance);
    }

    public static DistanceMap fromDescriptions(List<String> descriptions) {
        final DistanceMap distanceMap = new DistanceMap();
        descriptions.forEach(description -> distanceMap.distances.add(Distance.of(description)));

        return distanceMap;
    }

    public Set<String> cities() {
        return distances.stream()
                .map(d -> Set.of(d.from(), d.to()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public int routeDistance(List<String> route) {
        int routeDistance = 0;
        for (int idx = 0; idx < route.size() - 1; idx++) {
            final var city1 = route.get(idx);
            final var city2 = route.get(idx+1);

            final var distance = distance(city1, city2);
            if (distance.isEmpty())
                throw new IllegalArgumentException("No distance available from " + city1 + " to " + city2);

            routeDistance += distance.get();
        }

        return routeDistance;
    }
}
