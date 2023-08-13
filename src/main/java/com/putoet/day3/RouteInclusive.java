package com.putoet.day3;

class RouteInclusive extends RouteExclusive implements CombinableRoute {
    RouteInclusive() {
        add(Address.startAddress(), 1);
    }

    @Override
    public Route add(Route other) {
        final Route newRoute = new RouteExclusive();

        addresses().forEach(address -> newRoute.add(address, visits(address).orElseThrow()));
        other.addresses().forEach(address -> newRoute.add(address, other.visits(address).orElseThrow()));

        return newRoute;
    }
}
