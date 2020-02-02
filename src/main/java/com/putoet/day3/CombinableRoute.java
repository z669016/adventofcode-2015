package com.putoet.day3;

public interface CombinableRoute extends  Route {
    Route add(Route route);
}
