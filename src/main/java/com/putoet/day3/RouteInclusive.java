package com.putoet.day3;

public class RouteInclusive extends RouteExclusive implements CombinableRoute {
    public RouteInclusive() {
        add(Address.startAddress(), 1);
    }

    @Override
    public Route add(Route other) {
        final Route newRoute = new RouteExclusive();

        addresses().forEach(address -> newRoute.add(address, visits(address).get()));
        other.addresses().forEach(address -> newRoute.add(address, other.visits(address).get()));

        return newRoute;
    }
}
