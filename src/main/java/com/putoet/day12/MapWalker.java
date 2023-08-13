package com.putoet.day12;

import java.util.List;
import java.util.Map;

class MapWalker {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void walk(Object value, Visitor visitor, boolean ignoreRed) {
        if (value instanceof String s) visitor.accept(s);
        else if (value instanceof Integer i) visitor.accept(i);
        else if (value instanceof List l) walk(l, visitor, ignoreRed);
        else if (value instanceof Map m) {
            if (!ignoreRed || !containsRedValue(m))
                walk(m, visitor, ignoreRed);
        } else throw new IllegalStateException("Unknown object type: " + value.getClass());
    }

    private static void walk(Map<String, Object> map, Visitor visitor, boolean ignoreRed) {
        visitor.accept(map);
        map.forEach((key, value) -> walk(value, visitor, ignoreRed));
    }

    private static void walk(List<Object> list, Visitor visitor, boolean ignoreRead) {
        visitor.accept(list);
        list.forEach(o -> walk(o, visitor, ignoreRead));
    }

    private static boolean containsRedValue(Map<String, Object> map) {
        return map.values().stream()
                .filter(o -> o instanceof String)
                .map(o -> (String) o)
                .anyMatch(value -> value.equals("red"));
    }
}

