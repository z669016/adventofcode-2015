package com.putoet.day3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteInclusiveTest {
    private CombinableRoute route;

    @BeforeEach
    public void setup() {
        route = new RouteInclusive();
        route.add(Direction.NORTH);
        route.add(Direction.NORTH);
        route.add(Direction.SOUTH);
    }

    @Test
    void visitedAddresses() {
        assertEquals(3, route.visitedAddresses());
    }

    @Test
    public void addresses() {
        final var addresses = route.addresses();
        assertEquals(3, addresses.size());
        assertTrue(addresses.contains(Address.startAddress()));
        assertTrue(addresses.contains(Address.startAddress().move(Direction.NORTH)));
        assertTrue(addresses.contains(Address.startAddress().move(Direction.NORTH).move(Direction.NORTH)));
    }

    @Test
    public void add() {
        final var routeTwo = new RouteExclusive();
        routeTwo.add(Direction.WEST);
        routeTwo.add(Direction.WEST);
        routeTwo.add(Direction.EAST);

        final var combinedRoute = route.add(routeTwo);
        assertEquals(5, combinedRoute.visitedAddresses());
    }
}