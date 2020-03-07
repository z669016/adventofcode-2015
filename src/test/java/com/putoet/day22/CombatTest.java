package com.putoet.day22;

import org.junit.jupiter.api.Test;
import utilities.ListSupplier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CombatTest {

    @Test
    void start() {
        final Wizard wizard = new Wizard(10, 1, new ListSupplier<>(List.of()));
        final Boss boss = new Boss(10, 5);
        final Combat combat = new Combat(wizard, boss);

        final Combattant winner = combat.start();
        assertEquals(boss, winner);
    }

    @Test
    void isEffective() {
        final String NAME = "SPELL";
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = new Combat(wizard, boss);

        assertFalse(combat.isEffective(NAME));
        combat.addEffect(new Effect() {
            @Override
            public String name() {
                return NAME;
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
            }

            @Override
            public void unapply(Wizard wizard, Boss boss) {
            }

            @Override
            public boolean active() {
                return false;
            }

            @Override
            public boolean ended() {
                return false;
            }
        });

        assertTrue(combat.isEffective(NAME));
    }

    @Test
    void sample1() {
        final Wizard wizard = new Wizard(10, 250, new ListSupplier<>(List.of(
                Poison::cast,
                MagicMissile::cast
        )));
        final Boss boss = new Boss(13, 8);
        final Combat combat = new Combat(wizard, boss);

        final Combattant winner = combat.start();

        assertEquals(wizard.name(), winner.name());
        assertEquals(Poison.COST + MagicMissile.COST, wizard.charged());
    }

    @Test
    void sample2() {
        final Wizard wizard = new Wizard(10, 250, new ListSupplier<>(List.of(
                Recharge::cast,
                Shield::cast,
                Drain::cast,
                Poison::cast,
                MagicMissile::cast
        )));
        final Boss boss = new Boss(14, 8);
        final Combat combat = new Combat(wizard, boss);

        final Combattant winner = combat.start();
        System.out.println(wizard);
        System.out.println(boss);

        assertEquals(wizard.name(), winner.name());
        assertEquals(Recharge.COST + Shield.COST + Drain.COST + Poison.COST + MagicMissile.COST, wizard.charged());
    }
}