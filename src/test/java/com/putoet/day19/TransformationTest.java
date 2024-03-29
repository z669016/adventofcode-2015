package com.putoet.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformationTest {
    private static final String hoh = "HOH";
    private static final String hooh = "HOOH";
    private static final String hoho = "HOHO";
    private static final Transformation transformation = new Transformation("H", "HO");

    @Test
    void applyNotFound() {
        final var transformedMolecules = transformation.apply("BLA");
        assertEquals(0, transformedMolecules.size());
    }

    @Test
    void apply() {
        final var transformedMolecules = transformation.apply(hoh);
        assertEquals(2, transformedMolecules.size());
        assertTrue(transformedMolecules.contains(hooh));
        assertTrue(transformedMolecules.contains(hoho));
    }

    @Test
    void reverse() {
        final var t = new Transformation("from", "to");
        assertEquals(new Transformation("to", "from"), t.reverse());
    }
}