package com.putoet.day19;

import java.util.ArrayList;
import java.util.List;

public record Transformation(String from, String to) {
    public Transformation {
        assert from != null;
        assert to != null;
    }

    public List<String> apply(String molecule) {
        final StringBuilder start = new StringBuilder();
        String end = molecule;

        final String element = from;
        if (!end.contains(element)) {
            return List.of();
        }

        final List<String> transformedMolecules = new ArrayList<>();
        while (end.contains(element)) {
            final int pos = end.indexOf(element);
            final String before = start + end.substring(0, pos);
            start.append(end, 0, pos + element.length());
            end = end.substring(pos + element.length());
            transformedMolecules.add(before + to() + end);
        }

        return transformedMolecules;
    }

    public Transformation reverse() {
        return new Transformation(to, from);
    }
}
