package com.putoet.day19;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransformationsTest {
    private List<String> list = List.of(
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

        final Molecule e = new Molecule("e");
        final Molecule nal = new Molecule("NAl");
        final Molecule omg = new Molecule("OMg");

        assertEquals(e, t1.from());
        assertEquals(e, t2.from());

        assertTrue((omg.equals(t1.to()) && nal.equals(t2.to()))
                || (omg.equals(t2.to()) && nal.equals(t1.to())));
    }

    @Test
    void apply() {
        final Transformations transformations = Transformations.fromList(list);
        final Molecule molecule = new Molecule("HOH");

        final List<Molecule> moleculeTransformations = transformations.apply(molecule);
        assertEquals(4, moleculeTransformations.size());
        assertTrue(moleculeTransformations.contains(new Molecule(("HOOH"))));
        assertTrue(moleculeTransformations.contains(new Molecule(("HOHO"))));
        assertTrue(moleculeTransformations.contains(new Molecule(("OHOH"))));
        assertTrue(moleculeTransformations.contains(new Molecule(("HHHH"))));
    }

    @Test
    void transformationsTo() {
        final List<String> list = List.of(
                "e => H",
                "e => O",
                "H => HO",
                "H => OH",
                "O => HH");
        final Transformations transformations = Transformations.fromList(list);
        assertEquals(3, transformations.transfomationsTo(new Molecule("HOH")));
        assertEquals(6, transformations.transfomationsTo(new Molecule("HOHOHO")));
    }
}