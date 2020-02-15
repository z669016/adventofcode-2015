package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpellTest {

    @Test
    void castSpell() {
        final Spell everLastingSpell = new Spell("non lasting spell", 97, 3, 1,5);
        final WizardPlayer player = mock(WizardPlayer.class);

        assertEquals(97, everLastingSpell.cost());
        assertEquals(3, everLastingSpell.damage());
        assertEquals(1, everLastingSpell.armor());

        everLastingSpell.casted(player);

        assertEquals(97, everLastingSpell.cost());
        assertEquals(3, everLastingSpell.damage());
        assertEquals(1, everLastingSpell.armor());

        verify(player).heal(5);
    }

    @Test
    void castEffectpell() {
        final Spell everLastingSpell = new EffectSpell("non lasting spell", 97, 3, 1, 0, 0,2);
        final WizardPlayer player = mock(WizardPlayer.class);

        assertEquals(97, everLastingSpell.cost());
        assertEquals(3, everLastingSpell.damage());
        assertEquals(1, everLastingSpell.armor());

        everLastingSpell.casted(player);

        assertEquals(97, everLastingSpell.cost());
        assertEquals(3, everLastingSpell.damage());
        assertEquals(1, everLastingSpell.armor());

        everLastingSpell.casted(player);

        assertEquals(97, everLastingSpell.cost());
        assertEquals(0, everLastingSpell.damage());
        assertEquals(0, everLastingSpell.armor());

        verify(player, times(2)).heal(0);
        verify(player, times(2)).recharge(0);
    }
}