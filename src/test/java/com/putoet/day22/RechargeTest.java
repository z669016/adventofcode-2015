package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RechargeTest {

    @Test
    void cast() {
        final var wizard = mock(Wizard.class);
        final var boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Recharge.costs());

        final var effect = Recharge.cast(wizard, boss);

        verify(wizard, times(1)).mana();
        verify(wizard, times(1)).charge(Recharge.costs());

        for (int idx = 0; idx < 10; idx++) {
            effect.apply(wizard, boss);
        }
        verify(wizard, times(5)).charge(-Recharge.mana());
    }

    @Test
    void duplicate() {
        final var wizard = mock(Wizard.class);
        final var boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(Recharge.costs());

        final var effect = Recharge.cast(wizard, boss);
        effect.apply(wizard, boss);

        final var copy = effect.duplicate();
        assertEquals(4, copy.timer());
    }

    @Test
    void castFail() {
        final var wizard = mock(Wizard.class);
        final var boss = mock(Boss.class);

        when(wizard.mana()).thenReturn(Recharge.costs() - 1);

        assertThrows(IllegalStateException.class, () -> Recharge.cast(wizard, boss));
    }
}