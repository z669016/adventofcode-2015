package com.putoet.day3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteSantaAndRobotTest {
    private Route route;

    @BeforeEach
    public void setup() {
        route = new RouteSantaAndRobot();
        route.add(Direction.NORTH);
        route.add(Direction.WEST);
        route.add(Direction.NORTH);
        route.add(Direction.WEST);
        route.add(Direction.SOUTH);
        route.add(Direction.EAST);
    }

    @Test
    public void visited() {
        System.out.println(route.addresses());
        assertEquals(5, route.visitedAddresses());
    }

}