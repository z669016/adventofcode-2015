package com.putoet.day19;

import java.util.List;
import java.util.Optional;

public class Molecule {
    public static Optional<String> formList(List<String> list) {
        assert list != null;

        return list.stream()
                .filter(text -> text.trim().length() > 0)
                .filter(text -> !text.contains(Transformations.SPLIT_ELEMENT))
                .map(String::trim)
                .findFirst();
    }
}
