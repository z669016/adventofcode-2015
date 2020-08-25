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
        when(wizard.mana()).thenReturn(Poison.costs());

        final Effect effect = Poison.cast(wizard, boss);

        verify(boss,times(0)).damage(Poison.damage());
        verify(wizard, times(1)).mana();
        verify(wizard, times(1)).charge(Poison.costs());

        for (int idx = 0; idx < 10; idx++) {
            effect.apply(wizard, boss);
        }
        verify(boss,times(6)).damage(Poison.damage());
    }

    @Test
    void duplicate() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Poison.costs());

        final Effect effect = Poison.cast(wizard, boss);
        effect.apply(wizard, boss);

        final Effect copy = effect.duplicate();
        assertEquals(5, copy.timer());
    }

    @Test
    void castFail() {
        final Wizard wizard = mock(Wizard.class);
        final Boss boss = mock(Boss.class);

        when(wizard.mana()).thenReturn(Poison.costs() - 1);

        assertThrows(IllegalStateException.class, () -> Poison.cast(wizard, boss));
    }

}