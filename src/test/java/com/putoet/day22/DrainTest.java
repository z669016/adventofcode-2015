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
        when(wizard.charge(Drain.costs())).thenReturn(true);
        when(combat.boss()).thenReturn(boss);

        Drain.cast(combat);

        verify(wizard).heal(Drain.healing());
        verify(boss).defend(Drain.damage());
    }

    @Test
    void castFail() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(Drain.costs())).thenReturn(false);
        when(combat.boss()).thenReturn(boss);

        Drain.cast(combat);

        verify(wizard, never()).heal(Drain.healing());
        verify(boss, never()).defend(Drain.damage());
    }
}