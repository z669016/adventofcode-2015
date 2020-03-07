package com.putoet.day22;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RechargeTest {

    @Test
    void cast() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);
        final ArgumentCaptor<Effect> argument = ArgumentCaptor.forClass(Effect.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(Recharge.COST)).thenReturn(true);
        when(combat.boss()).thenReturn(boss);

        Recharge.cast(combat);

        verify(combat).addEffect(argument.capture());
        final Effect effect = argument.getValue();

        assertEquals(Recharge.NAME, effect.name());
        assertFalse(effect.ended());

        for (int idx = 0; idx < 5; idx++) {
            effect.apply(wizard, boss);
            effect.unapply(wizard, boss);

            if (idx < 4)
                assertFalse(effect.ended());
            else
                assertTrue(effect.ended());
        }
        effect.apply(wizard, boss);
        effect.unapply(wizard, boss);

        verify(wizard, times(5)).recharge(Recharge.MANA);
    }
}