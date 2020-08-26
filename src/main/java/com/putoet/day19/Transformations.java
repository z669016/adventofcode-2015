package com.putoet.day19;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Transformations implements Iterable<Transformation> {
    public static final String SPLIT_ELEMENT = " => ";

    private final List<Transformation> transformations;

    private Transformations(List<Transformation> list) {
        this.transformations = list;
    }

    public static Transformations fromList(List<String> list) {
        final List<Transformation> transformations = list.stream()
                .filter(text -> text.trim().length() > 0)
                .filter(text -> text.contains(SPLIT_ELEMENT))
                .map(text -> text.split(SPLIT_ELEMENT))
                .map(e -> new Transformation(new Molecule(e[0]), new Molecule(e[1])))
                .collect(Collectors.toList());

        return new Transformations(transformations);
    }

    public int size() {
        return transformations.size();
    }

    public List<Molecule> apply(Molecule molecule) {
        return transformations.stream()
                .map(t -> t.apply(molecule))
                .flatMap(Collection::stream)
                .distinct().collect(Collectors.toList());
    }

    @Override
    public Iterator<Transformation> iterator() {
        return transformations.iterator();
    }

    @Override
    public String toString() {
        return transformations.toString();
    }
}
