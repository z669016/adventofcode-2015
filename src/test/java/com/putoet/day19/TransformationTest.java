package com.putoet.day19;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransformationTest {
    private static final Molecule hoh = new Molecule("HOH");
    private static final Molecule hooh = new Molecule("HOOH");
    private static final Molecule hoho = new Molecule("HOHO");
    private static final Transformation transformation = new Transformation(new Molecule("H"), new Molecule("HO"));

    @Test
    void applyNotFound() {
        final Molecule bla = new Molecule("BLA");
        final List<Molecule> transformedMolecules = transformation.apply(bla);
        assertEquals(0, transformedMolecules.size());
    }

    @Test
    void apply() {
        final List<Molecule> transformedMolecules = transformation.apply(hoh);
        assertEquals(2, transformedMolecules.size());
        System.out.println(transformedMolecules);

        assertTrue((hooh.equals(transformedMolecules.get(0)) && hoho.equals(transformedMolecules.get(1)))
            || (hooh.equals(transformedMolecules.get(1)) && hoho.equals(transformedMolecules.get(0))));
    }
}