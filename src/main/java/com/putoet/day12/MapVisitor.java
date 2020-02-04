package com.putoet.day12;

import java.util.List;
import java.util.Map;

public class MapVisitor {
    interface Visitor {
        void accept(Map<String,Object> map);
        void accept(List<Object> list);
        void accept(String string);
        void accept(Integer integer);
    }

    public static void visit(Map<String,Object> map, Visitor visitor, boolean ignoreRed) {
        visitor.accept(map);

        if (!ignoreRed || !map.containsKey("red"))
            map.forEach((key, value) -> visit(value, visitor, ignoreRed));
    }

    private static void visit(List<Object> list, Visitor visitor, boolean ignoreRead) {
        visitor.accept(list);
        list.forEach(o -> visit(o, visitor, ignoreRead));
    }

    public static void visit(Object value, Visitor visitor, boolean ignoreRed) {
        if (value instanceof String) visitor.accept((String) value);
        else if (value instanceof Integer) visitor.accept((Integer) value);
        else if(value instanceof List) visit((List<Object>) value, visitor, ignoreRed);
        else if(value instanceof Map) {
            final Map<String,Object> map = (Map<String,Object>) value;
            if (!ignoreRed || !containsRedValue(map))
                visit(map, visitor, ignoreRed);
        }
        else throw new IllegalStateException("Unknown object type: " + value.getClass());
    }

    private static boolean containsRedValue(Map<String,Object> map) {
        return map.values().stream()
                .filter(o -> o instanceof String)
                .map(o -> (String) o)
                .anyMatch(value -> value.equals("red"));
    }
}

