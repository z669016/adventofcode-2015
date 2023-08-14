package com.putoet.day19;

import java.util.List;
import java.util.Optional;

public class Molecule {
    public static Optional<String> formList(List<String> list) {
        assert list != null;

        return list.stream()
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .filter(text -> !text.contains(Transformations.SPLIT_ELEMENT))
                .findFirst();
    }
}
