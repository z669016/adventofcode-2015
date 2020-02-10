package com.putoet.day19;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Molecule {
    private final String element;

    public Molecule(String element) {
        assert element != null;
        assert element.trim().length() > 0;

        this.element = element.trim();
    }

    public String element() {
        return element;
    }

    public static Optional<Molecule> formList(List<String> list) {
        assert list != null;

        return list.stream()
                .filter(text -> text.trim().length() > 0)
                .filter(text -> !text.contains(Transformations.SPLIT_ELEMENT))
                .map(Molecule::new)
                .findFirst();
    }

    public int length() {
        return element.length();
    }

    @Override
    public String toString() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Molecule)) return false;
        Molecule molecule = (Molecule) o;
        return element.equals(molecule.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}
