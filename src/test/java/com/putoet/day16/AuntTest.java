package com.putoet.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuntTest {
    @Test
    void fromDescription() {
        final Aunt aunt = Aunt.fromDescription("Sue 24: goldfish: 7, pomeranians: 9, akitas: 4");
        assertEquals("Sue", aunt.name());
        assertEquals(24, aunt.number());
        assertEquals(7, aunt.goldfish());
        assertEquals(9, aunt.pomeranians());
        assertEquals(4, aunt.akitas());
        assertFalse(aunt.childrenKnown());
        assertFalse(aunt.catsKnown());
        assertFalse(aunt.samoyedsKnown());
        assertFalse(aunt.vizslasKnown());
        assertFalse(aunt.treesKnown());
        assertFalse(aunt.catsKnown());
        assertFalse(aunt.perfumesKnown());

        assertThrows(AssertionError.class, () -> Aunt.fromDescription(null));
        assertThrows(AssertionError.class, () -> Aunt.fromDescription(""));
        assertThrows(AssertionError.class, () -> Aunt.fromDescription("Sue 24, goldfish 7, pomeranians 9, akitas 4"));
    }

    @Test
    void setProperty() {
        final List<String> properties = List.of(
                "children: 1",
                "cats: 2",
                "samoyeds: 3",
                "pomeranians: 4",
                "akitas: 5",
                "vizslas: 6",
                "goldfish: 7",
                "trees: 8",
                "cars: 9",
                "perfumes: 10");

        final Aunt aunt = new Aunt("Bla", 1);
        properties.forEach(aunt::setProperty);

        assertEquals(1, aunt.children());
        assertEquals(2, aunt.cats());
        assertEquals(3, aunt.samoyeds());
        assertEquals(4, aunt.pomeranians());
        assertEquals(5, aunt.akitas());
        assertEquals(6, aunt.vizslas());
        assertEquals(7, aunt.goldfish());
        assertEquals(8, aunt.trees());
        assertEquals(9, aunt.cars());
        assertEquals(10, aunt.perfumes());

        assertTrue(aunt.childrenKnown());
        assertTrue(aunt.catsKnown());
        assertTrue(aunt.samoyedsKnown());
        assertTrue(aunt.pomeraniansKnown());
        assertTrue(aunt.akitasKnown());
        assertTrue(aunt.vizslasKnown());
        assertTrue(aunt.goldfishKnown());
        assertTrue(aunt.treesKnown());
        assertTrue(aunt.carsKnown());
        assertTrue(aunt.perfumesKnown());

        assertThrows(AssertionError.class, () -> aunt.setProperty(null));
        assertThrows(NumberFormatException.class, () -> aunt.setProperty("bla: a"));
        assertThrows(IllegalArgumentException.class, () -> aunt.setProperty("horses: 11"));
        assertThrows(IllegalArgumentException.class, () -> aunt.setProperty(""));
        assertThrows(IllegalArgumentException.class, () -> aunt.setProperty("cats 1"));
    }

    @Test
    void couldMatch() {
        final Aunt aunt = Aunt.fromDescription("Sue 24: goldfish: 7, pomeranians: 9, akitas: 4");
        final List<String> properties = List.of(
                "children: 1",
                "cats: 1",
                "samoyeds: 1",
                "pomeranians: 9",
                "akitas: 4",
                "vizslas: 1",
                "goldfish: 7",
                "trees: 1",
                "cars: 1",
                "perfumes: 1");

        final Aunt unknownAunt = new Aunt("", 0);
        properties.forEach(unknownAunt::setProperty);

        assertTrue(aunt.couldMatch(unknownAunt));
    }
}