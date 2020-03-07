package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WizardTest {

    @Test
    void defend() {
        final Wizard wizard = new Wizard(1, 500, List.of());
        assertFalse(wizard.defeated());
        wizard.defend(1);
        assertTrue(wizard.defeated());
    }

    @Test
    void heal() {
        final Wizard wizard = new Wizard(0, 500, List.of());
        assertTrue(wizard.defeated());
        wizard.heal(1);
        assertFalse(wizard.defeated());
        wizard.heal(-1);
        assertTrue(wizard.defeated());
    }

    @Test
    void recharge() {
        final Wizard wizard = new Wizard(10, 0, List.of());
        assertTrue(wizard.defeated());
        wizard.recharge(1);
        assertFalse(wizard.defeated());
        wizard.charge(1);
        assertTrue(wizard.defeated());
    }

    @Test
    void armor() {
        final Wizard wizard = new Wizard(2, 500, List.of());
        wizard.armor(1);
        assertFalse(wizard.defeated());
        wizard.defend(2);
        assertFalse(wizard.defeated());
        wizard.defend(1);
        assertTrue(wizard.defeated());
    }

    @Test
    void attack() {
        final Combat combat = mock(Combat.class);
        final Combattant combattant = mock(Combattant.class);

        final int[] counter = {0};
        final List<Consumer<Combat>> list = List.of(
                c -> counter[0]++,
                c -> counter[0]++,
                c -> counter[0]++
        );
        final Wizard wizard = new Wizard(1, 1, list);
        for (int idx = 0; idx < 10; idx++)
            wizard.attack(combat, combattant);

        assertEquals(3, counter[0]);
    }
}