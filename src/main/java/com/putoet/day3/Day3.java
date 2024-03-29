package com.putoet.day3;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day3 {
    public static void main(String[] args) {
        var directions = ResourceLines.list("/day3.txt").stream()
                .flatMapToInt(String::chars)
                .mapToObj(Direction::of)
                .toList();

        Timer.run(() -> {
            final var route = new RouteInclusive();
            directions.forEach(route::add);
            System.out.println("Visited addresses is " + route.visitedAddresses());
        });

        Timer.run(() -> {
            final var route = new RouteSantaAndRobot();
            directions.forEach(route::add);
            System.out.println("Visited addresses by Santa and robot is " + route.visitedAddresses());
        });
    }
}
