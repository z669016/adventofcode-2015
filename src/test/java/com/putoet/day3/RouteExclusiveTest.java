package com.putoet.day3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        final Optional<Integer> visitesOne = route.visits(Address.startAddress().move(Direction.NORTH));
        final Optional<Integer> visitesTwo = route.visits(Address.startAddress().move(Direction.NORTH).move(Direction.NORTH));

        assertTrue(visitesOne.isPresent());
        assertEquals(2, visitesOne.get());

        assertTrue(visitesTwo.isPresent());
        assertEquals(1, visitesTwo.get());
    }

    @Test
    public void addresses() {
        final Set<Address> addresses = route.addresses();
        assertEquals(2, addresses.size());
        assertTrue(addresses.contains(Address.startAddress().move(Direction.NORTH)));
        assertTrue(addresses.contains(Address.startAddress().move(Direction.NORTH).move(Direction.NORTH)));

        System.out.println(addresses);
    }
}