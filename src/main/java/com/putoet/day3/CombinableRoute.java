package com.putoet.day3;

interface CombinableRoute extends  Route {
    Route add(Route route);
}
