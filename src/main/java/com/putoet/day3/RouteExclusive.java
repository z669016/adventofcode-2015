package com.putoet.day3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class RouteExclusive implements Route {
    private final Map<Address, Integer> route = new HashMap<>();
    private Address lastAddress;

    public RouteExclusive() {
        lastAddress = Address.startAddress();
    }

    @Override
    public void add(Direction direction) {
        add(lastAddress.move(direction));
    }

    private void add(Address address) {
        add(address, 1);
    }

    @Override
    public void add(Address address, int visits) {
        lastAddress = address;
        if (route.containsKey(address))
            route.put(address, route.get(address) + visits);
        else
            route.put(address, visits);
    }

    @Override
    public int visitedAddresses() {
        return route.size();
    }

    @Override
    public Set<Address> addresses() {
        return route.keySet();
    }

    @Override
    public Optional<Integer> visits(Address address) {
        return route.containsKey(address) ? Optional.of(route.get(address)) : Optional.empty();
    }
}
