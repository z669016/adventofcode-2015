package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrainTest {

    @Test
    void cast() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Drain.costs());

        final Effect effect = Drain.cast(wizard, boss);
        effect.apply(wizard, boss);

        verify(boss, times(1)).damage(Drain.damage());
        verify(wizard,times(1)).damage(-Drain.healing());
        verify(wizard, times(1)).mana();
        verify(wizard, times(1)).charge(Drain.costs());
    }

    @Test
    void duplicate() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Drain.costs());

        final Effect copy = Drain.cast(wizard, boss).duplicate();
        assertFalse(copy.active());
        assertTrue(copy.ended());
    }

    @Test
    void castFail() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Drain.costs() - 1);

        assertThrows(IllegalStateException.class, () -> Drain.cast(wizard,boss));
    }
}