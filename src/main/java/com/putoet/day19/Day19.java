package com.putoet.day19;

import utilities.ResourceLines;

import java.util.List;
import java.util.Optional;

public class Day19 {
    public static void main(String[] args) {
        final List<String> list = ResourceLines.list("/day19.txt");
        final Transformations transformations = Transformations.fromList(list);
        final Optional<Molecule> molecule = Molecule.formList(list);

        if (molecule.isPresent()) {
            final List<Molecule> transformed = transformations.apply(molecule.get());
            System.out.println("Number of possible molecules is " + transformed.size());
        }
    }
}
