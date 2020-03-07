package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DrainTest {

    @Test
    void cast() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(Drain.COST)).thenReturn(true);
        when(combat.boss()).thenReturn(boss);

        Drain.cast(combat);

        verify(wizard).heal(Drain.HIT_POINTS);
        verify(boss).defend(Drain.DAMAGE);
    }

    @Test
    void castFail() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(Drain.COST)).thenReturn(false);
        when(combat.boss()).thenReturn(boss);

        Drain.cast(combat);

        verify(wizard, never()).heal(Drain.HIT_POINTS);
        verify(boss, never()).defend(Drain.DAMAGE);
    }
}