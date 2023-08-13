package com.putoet.day12;

import java.util.List;
import java.util.Map;

interface Visitor {
    default void accept(Map<String, Object> ignoredMap) {}

    default void accept(List<Object> ignoredList) {}

    default void accept(String ignoredString) {}

    default void accept(Integer ignoredInteger) {}
}
