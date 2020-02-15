package com.putoet.day22;

import com.putoet.day21.Armament;
import com.putoet.day21.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmedPlayerTest {

    @Test
    void pick() {
        final Player player = new Player("One");
        final Armament sword = new Armament(Armament.Type.WEAPON, "Sword", 99, 1, 2);
        final Armament dagger = new Armament(Armament.Type.WEAPON, "Dagger", 2, 1, 2);

        player.pick(sword);
        assertTrue(player.armaments().contains(sword));
    }

    @Test
    void attackDefend() {
        final Player one = new Player("One");
        final Armament sword = new Armament(Armament.Type.WEAPON, "Sword", 99, 4, 2);
        one.pick(sword);

        final Player two = new Player("Two");
        final Armament dagger = new Armament(Armament.Type.WEAPON, "Dagger", 2, 1, 2);
        two.pick(dagger);

        one.attack(two);
        assertEquals(100, one.hitPoints());
        assertEquals(98, two.hitPoints());
        assertFalse(two.defeated());

        two.attack(one);
        assertEquals(99, one.hitPoints());
        assertEquals(98, two.hitPoints());
        assertFalse(one.defeated());

        for (int i = 0; i < 50; i++) {
            one.attack(two);
        }
        assertEquals(-2, two.hitPoints());
        assertTrue(two.defeated());
    }

    @Test
    void boss() {
        final List<String> descriptions = List.of(
                "Hit Points: 109",
                "Damage: 8",
                "Armor: 2");

        final Player boss = Player.boss();
        assertEquals(boss, Player.boss(List.of()));

        final Player newBoss = Player.boss(descriptions);
        assertNotEquals(boss, newBoss);
        assertEquals(109, newBoss.hitPoints());
        assertEquals(8, newBoss.damage());
        assertEquals(2, newBoss.defence());
    }
}