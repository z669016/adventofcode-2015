package com.putoet.day24;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cargo {
    public final List<List<Integer>> containers;

    public Cargo(List<Integer> ... containers) {
        assert containers != null;
        assert Arrays.stream(containers).noneMatch(Objects::isNull);
        assert containers.length > 0;

        this.containers = Arrays.asList(containers);
    }

    @Override
    public String toString() {
        return containers.stream().map(Object::toString).collect(Collectors.joining("[",",", "]"));
    }

    public List<Integer> passengerCompartment() {
        return containers.get(0);
    }
}
