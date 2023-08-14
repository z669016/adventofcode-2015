package com.putoet.day19;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public record Transformations(List<Transformation> transformations) implements Iterable<Transformation> {
    public static final String SPLIT_ELEMENT = " => ";

    public static Transformations of(List<String> list) {
        final List<Transformation> transformations = list.stream()
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .filter(text -> text.contains(SPLIT_ELEMENT))
                .map(text -> text.split(SPLIT_ELEMENT))
                .map(e -> new Transformation(e[0],e[1]))
                .toList();

        return new Transformations(transformations);
    }

    public int size() {
        return transformations.size();
    }

    public List<String> apply(String molecule) {
        return transformations.stream()
                .map(t -> t.apply(molecule))
                .flatMap(Collection::stream)
                .distinct()
                .toList();
    }

    @Override
    public Iterator<Transformation> iterator() {
        return transformations.iterator();
    }
}
