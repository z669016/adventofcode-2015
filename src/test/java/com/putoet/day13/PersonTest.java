package com.putoet.day13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person from;
    private Person left;
    private Person right;
    private Person unknown;

    @BeforeEach
    public void setup() {
        from = new Person("From");
        left = new Person("Left");
        right = new Person("Right");
        unknown = new Person("Unknown");
        from.addHappinessRelationShip(new PersonToPersonHappiness(from, left, 7));
        from.addHappinessRelationShip(new PersonToPersonHappiness(from, right, -4));
    }

    @Test
    void happiness() {
        assertEquals(3, from.happiness(left, right));
    }

    @Test
    void happinessUnknownLeft() {
        assertThrows(IllegalArgumentException.class, () -> from.happiness(unknown, right));
    }

    @Test
    void happinessUnknownRight() {
        assertThrows(IllegalArgumentException.class, () -> from.happiness(left, unknown));
    }

    @Test
    void addHappinessRelationShipInvalidFrom() {
        assertThrows(IllegalArgumentException.class, () -> from.addHappinessRelationShip(new PersonToPersonHappiness(unknown, left, 10)));
    }

    @Test
    void addHappinessRelationShipDouble() {
        assertThrows(IllegalArgumentException.class, () -> from.addHappinessRelationShip(new PersonToPersonHappiness(from, right, 10)));
    }
}