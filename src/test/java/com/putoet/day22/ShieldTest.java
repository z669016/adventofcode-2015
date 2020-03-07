package com.putoet.day22;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.logging.SocketHandler;

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
        when(wizard.charge(Shield.COST)).thenReturn(true);
        when(combat.boss()).thenReturn(boss);

        Shield.cast(combat);

        verify(combat).addEffect(argument.capture());
        final Effect effect = argument.getValue();

        assertEquals(Shield.NAME, effect.name());
        assertFalse(effect.ended());

        for (int idx = 0; idx < 7; idx++) {
            effect.apply(wizard, boss);
            effect.unapply(wizard, boss);

            if (idx < 6)
                assertFalse(effect.ended());
            else
                assertTrue(effect.ended());
        }
        effect.apply(wizard, boss);
        effect.unapply(wizard, boss);

        verify(wizard, times(7)).armor(Shield.ARMOR);
        verify(wizard, times(7)).armor(-1 *Shield.ARMOR);
    }
}