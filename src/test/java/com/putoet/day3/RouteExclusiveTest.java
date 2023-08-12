package com.putoet.day3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteExclusiveTest {
    private Route route;

    @BeforeEach
    public void setup() {
        route = new RouteExclusive();
        route.add(Direction.NORTH);
        route.add(Direction.NORTH);
        route.add(Direction.SOUTH);
    }

    @Test
    void visitedAddresses() {
        assertEquals(2, route.visitedAddresses());
    }

    @Test
    void visites() {
        final var visitesOne = route.visits(Address.startAddress().move(Direction.NORTH));
        final var visitesTwo = route.visits(Address.startAddress().move(Direction.NORTH).move(Direction.NORTH));

        assertTrue(visitesOne.isPresent());
        assertEquals(2, visitesOne.get());

        assertTrue(visitesTwo.isPresent());
        assertEquals(1, visitesTwo.get());
    }

    @Test
    public void addresses() {
        final var addresses = route.addresses();
        assertEquals(2, addresses.size());
        assertTrue(addresses.contains(Address.startAddress().move(Direction.NORTH)));
        assertTrue(addresses.contains(Address.startAddress().move(Direction.NORTH).move(Direction.NORTH)));
    }
}