package com.putoet.day19;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MoleculeTest {
    private static final String NAME = "Bla";
    private static final List<String> list = List.of(
            "H => HO",
            "H => OH",
            "O => HH",
            "",
            "HOH");

    @Test
    void element() {
        final Molecule m = new Molecule(NAME);
        assertEquals(NAME, m.element());
        assertEquals(NAME, m.toString());
    }

    @Test
    void fromList() {
        assertThrows(AssertionError.class, () -> Molecule.formList(null));

        assertFalse(Molecule.formList(List.of()).isPresent());

        final Optional<Molecule> molecule = Molecule.formList(list);
        assertTrue(molecule.isPresent());
        assertTrue(new Molecule("HOH").equals(molecule.get()));
    }
}