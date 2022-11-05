package com.putoet.day19;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransformationsTest {
    private final List<String> list = List.of(
            "H => HO",
            "H => OH",
            "O => HH",
            "",
            "HOH");

    @Test
    void fromList() {
        final List<String> list = List.of(
                "e => NAl",
                "e => OMg",
                "",
                "CRnCaSiRnBSiRnF");

        final Transformations transformations = Transformations.fromList(list);
        assertEquals(2, transformations.size());

        final Iterator<Transformation> iter = transformations.iterator();
        assertTrue(iter.hasNext());
        final Transformation t1 = iter.next();
        assertTrue(iter.hasNext());
        final Transformation t2 = iter.next();
        assertFalse(iter.hasNext());

        final String e = "e";
        final String nal = "NAl";
        final String omg = "OMg";

        assertEquals(e, t1.from());
        assertEquals(e, t2.from());

        assertTrue((omg.equals(t1.to()) && nal.equals(t2.to()))
                || (omg.equals(t2.to()) && nal.equals(t1.to())));
    }

    @Test
    void apply() {
        final Transformations transformations = Transformations.fromList(list);
        final String molecule = "HOH";

        final List<String> moleculeTransformations = transformations.apply(molecule);
        assertEquals(4, moleculeTransformations.size());
        assertTrue(moleculeTransformations.contains("HOOH"));
        assertTrue(moleculeTransformations.contains("HOHO"));
        assertTrue(moleculeTransformations.contains("OHOH"));
        assertTrue(moleculeTransformations.contains("HHHH"));
    }
}