package com.putoet.day3;

import com.putoet.day2.Day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day3 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final URL url = Day2.class.getResource("/day3.txt");
        final Path path = Paths.get(url.toURI());

        Route route = new RouteInclusive();
        Files.lines(path).flatMapToInt(String::chars).mapToObj(Direction::valueOf).forEach(route::add);
        System.out.println("Visited addresses is " + route.visitedAddresses());

        route = new RouteSantaAndRobot();
        Files.lines(path).flatMapToInt(String::chars).mapToObj(Direction::valueOf).forEach(route::add);
        System.out.println("Visited addresses by Santa and robot is " + route.visitedAddresses());
    }
}
