package com.putoet.day3;

import com.putoet.resources.ResourceLines;

public class Day3 {

    public static final String PUZZLE_INPUT = "/day3.txt";

    public static void main(String[] args)  {
        Route route = new RouteInclusive();
        ResourceLines.list(PUZZLE_INPUT).stream()
                .flatMapToInt(String::chars)
                .mapToObj(Direction::valueOf)
                .forEach(route::add);
        System.out.println("Visited addresses is " + route.visitedAddresses());

        route = new RouteSantaAndRobot();
        ResourceLines.list(PUZZLE_INPUT).stream()
                .flatMapToInt(String::chars)
                .mapToObj(Direction::valueOf)
                .forEach(route::add);
        System.out.println("Visited addresses by Santa and robot is " + route.visitedAddresses());
    }
}
