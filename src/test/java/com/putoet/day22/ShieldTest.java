package com.putoet.day22;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShieldTest {

    @Test
    void cast() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);
        final ArgumentCaptor<Effect> argument = ArgumentCaptor.forClass(Effect.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(Shield.costs())).thenReturn(true);
        when(combat.boss()).thenReturn(boss);

        Shield.cast(combat);

        verify(combat).addEffect(argument.capture());
        final Effect effect = argument.getValue();

        assertEquals(Shield.name(), effect.name());
    }

    @Test
    void effect() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Effect effect = Shield.effect();

        assertEquals(Shield.name(), effect.name());
        assertFalse(effect.ended());

        for (int idx = 0; idx < Shield.timer(); idx++) {
            effect.apply(wizard, boss);
            effect.unapply(wizard, boss);

            if (idx < Shield.timer() - 1)
                assertFalse(effect.ended());
            else
                assertTrue(effect.ended());
        }
        effect.apply(wizard, boss);
        effect.unapply(wizard, boss);

        verify(wizard, times(1)).armor(Shield.armor());
        verify(wizard, times(1)).armor(-1 * Shield.armor());
    }
}