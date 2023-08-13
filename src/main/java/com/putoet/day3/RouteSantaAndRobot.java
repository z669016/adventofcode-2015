package com.putoet.day3;

import java.util.Optional;
import java.util.Set;

class RouteSantaAndRobot implements Route {
    private final CombinableRoute routeSanta = new RouteInclusive();
    private final Route routeRobot = new RouteExclusive();
    private Route activeRoute = routeSanta;

    @Override
    public void add(Direction direction) {
        activeRoute.add(direction);
        change();
    }

    private void change() {
        activeRoute = (activeRoute == routeSanta ? routeRobot : routeSanta);
    }

    @Override
    public void add(Address address, int visits) {
        activeRoute.add(address, visits);
        change();
    }

    @Override
    public int visitedAddresses() {
        return routeSanta.add(routeRobot).visitedAddresses();
    }

    @Override
    public Set<Address> addresses() {
        return routeSanta.add(routeRobot).addresses();
    }

    @Override
    public Optional<Integer> visits(Address address) {
        return routeSanta.add(routeRobot).visits(address);
    }
}
