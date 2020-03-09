package com.putoet.day22;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PoisonTest {

    @Test
    void cast() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);
        final ArgumentCaptor<Effect> argument = ArgumentCaptor.forClass(Effect.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(Poison.costs())).thenReturn(true);
        when(combat.boss()).thenReturn(boss);

        Poison.cast(combat);

        verify(combat).addEffect(argument.capture());
        final Effect effect = argument.getValue();

        assertEquals(Poison.name(), effect.name());

    }

    @Test
    void effect() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);

        final Effect effect = Poison.effect();

        assertEquals(Poison.name(), effect.name());
        assertFalse(effect.ended());

        for (int idx = 0; idx < Poison.timer(); idx++) {
            effect.apply(wizard, boss);
            effect.unapply(wizard, boss);

            if (idx < Poison.timer() - 1)
                assertFalse(effect.ended());
            else
                assertTrue(effect.ended());
        }
        effect.apply(wizard, boss);
        effect.unapply(wizard, boss);

        verify(boss, times(6)).defend(Poison.damage());
    }
}