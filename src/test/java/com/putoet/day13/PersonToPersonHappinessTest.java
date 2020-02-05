package com.putoet.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonToPersonHappinessTest {

    @Test
    void happynessImpact() {
        final Person from = new Person("a");
        final Person to = new Person("b");
        final PersonToPersonHappiness happiness = new PersonToPersonHappiness(from, to, -10);
        assertEquals("a", happiness.fromPerson().name());
        assertEquals("b", happiness.toPerson().name());
        assertEquals(-10, happiness.happynessImpact());
    }
}