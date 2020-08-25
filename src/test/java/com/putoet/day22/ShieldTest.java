package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShieldTest {

    @Test
    void cast() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Shield.costs());

        final Effect effect = Shield.cast(wizard, boss);

        verify(wizard,times(1)).armor(Shield.armor());
        verify(wizard, times(1)).mana();
        verify(wizard, times(1)).charge(Shield.costs());

        for (int idx = 0; idx < 5; idx++) {
            effect.apply(wizard, boss);
            verify(wizard,times(1)).armor(Shield.armor());
        }

        effect.apply(wizard, boss);
        verify(wizard,times(1)).armor(Shield.armor());
        verify(wizard,times(1)).armor(-Shield.armor());

        effect.apply(wizard, boss);
        verify(wizard,times(1)).armor(Shield.armor());
        verify(wizard,times(1)).armor(-Shield.armor());
    }


    @Test
    void duplicate() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Shield.costs());

        final Effect copy = Shield.cast(wizard, boss).duplicate();
        assertEquals(6, copy.timer());
    }

    @Test
    void castFail() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);

        when(wizard.mana()).thenReturn(Drain.costs() - 1);

        assertThrows(IllegalStateException.class, () -> Shield.cast(wizard, boss));
    }

}