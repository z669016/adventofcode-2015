package com.putoet.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transformation {
    private final Molecule from;
    private final Molecule to;

    public Transformation(Molecule from, Molecule to) {
        assert from != null;
        assert to != null;

        this.from = from;
        this.to = to;
    }

    public Molecule from() {
        return from;
    }

    public Molecule to() {
        return to;
    }

    public List<Molecule> apply(Molecule molecule) {
        final StringBuilder start = new StringBuilder();
        String end = molecule.element();

        final String element = from.element();
        if (!end.contains(element)) {
            return List.of();
        }

        final List<Molecule> transformedMolecules = new ArrayList<>();
        while (end.contains(element)) {
            final int pos = end.indexOf(element);
            final String before = start + end.substring(0, pos);
            start.append(end.substring(0, pos + element.length()));
            end = end.substring(pos + element.length());
            transformedMolecules.add(new Molecule((before + to().element() + end)));
        }

        return transformedMolecules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transformation)) return false;
        Transformation that = (Transformation) o;
        return from.equals(that.from) &&
                to.equals(that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Transformation{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
