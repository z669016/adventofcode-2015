package com.putoet.day3;

import java.util.Optional;
import java.util.Set;

public interface Route {
    void add(Direction direction);

    void add(Address address, int visits);

    int visitedAddresses();

    Set<Address> addresses();

    Optional<Integer> visits(Address address);
}
