package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MagicMissileTest {

    @Test
    void cast() {
        final var wizard = mock(Wizard.class);
        final var boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(MagicMissile.costs());

        final var effect = MagicMissile.cast(wizard, boss);
        effect.apply(wizard, boss);

        verify(boss, times(1)).damage(MagicMissile.damage());
        verify(wizard, times(1)).mana();
        verify(wizard, times(1)).charge(MagicMissile.costs());

        assertFalse(effect.active());
        assertTrue(effect.ended());
    }

    @Test
    void duplicate() {
        final var wizard = mock(Wizard.class);
        final var boss = mock(Boss.class);
        when(wizard.mana()).thenReturn(MagicMissile.costs());

        final var copy = MagicMissile.cast(wizard, boss).duplicate();
        assertFalse(copy.active());
        assertTrue(copy.ended());
    }

    @Test
    void castFail() {
        final var wizard = mock(Wizard.class);
        final var boss = mock(Boss.class);

        when(wizard.mana()).thenReturn(MagicMissile.costs() - 1);

        assertThrows(IllegalStateException.class, () -> MagicMissile.cast(wizard, boss));
    }
}