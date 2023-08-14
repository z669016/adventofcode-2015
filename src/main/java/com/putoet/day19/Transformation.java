package com.putoet.day19;

import java.util.ArrayList;
import java.util.List;

public record Transformation(String from, String to) {
    public Transformation {
        assert from != null;
        assert to != null;
    }

    public List<String> apply(String molecule) {
        final var start = new StringBuilder();
        var end = molecule;

        final var element = from;
        if (!end.contains(element)) {
            return List.of();
        }

        final var transformedMolecules = new ArrayList<String>();
        while (end.contains(element)) {
            final var pos = end.indexOf(element);
            final var before = start + end.substring(0, pos);

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
