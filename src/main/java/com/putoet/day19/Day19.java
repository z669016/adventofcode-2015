package com.putoet.day19;

import utilities.ResourceLines;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class Day19 {
    private static final Molecule e = new Molecule("e");

    public static void main(String[] args) {
        final List<String> list = ResourceLines.list("/day19.txt");
        final Transformations transformations = Transformations.fromList(list);
        final Optional<Molecule> molecule = Molecule.formList(list);

        if (molecule.isEmpty()) {
            System.out.println("No molecule found in the provided list...");
            return;
        }

        final List<Molecule> transformed = transformations.apply(molecule.get());
        System.out.println("Number of possible molecules is " + transformed.size());

        final int level = transformations.transfomationsTo(molecule.get());
        System.out.println("Minimum number of revert back back to 'e' is " + level);
    }
}
