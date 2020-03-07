package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MagicMissileTest {

    @Test
    void cast() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(MagicMissile.COST)).thenReturn(true);
        when(combat.boss()).thenReturn(boss);

        MagicMissile.cast(combat);

        verify(boss).defend(MagicMissile.DAMAGE);
    }

    @Test
    void castFail() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        final Combat combat = mock(Combat.class);

        when(combat.wizard()).thenReturn(wizard);
        when(wizard.charge(MagicMissile.COST)).thenReturn(false);
        when(combat.boss()).thenReturn(boss);

        MagicMissile.cast(combat);

        verify(boss, never()).defend(MagicMissile.DAMAGE);
    }
}