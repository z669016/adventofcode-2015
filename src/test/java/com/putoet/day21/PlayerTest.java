package com.putoet.day21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void pay() {
        final Player player = new Player("One", 100);
        final Armament sword = new Armament(Armament.Type.WEAPON, "Sword", 99, 1, 2);
        final Armament dagger = new Armament(Armament.Type.WEAPON, "Dagger", 2, 1, 2);

        player.pay(sword);
        assertEquals(1, player.gold());
        assertTrue(player.armaments().contains(sword));

        assertThrows(IllegalArgumentException.class, () -> player.pay(dagger));
        assertEquals(1, player.gold());
        assertTrue(player.armaments().contains(sword));
    }

    @Test
    void attackDefend() {
        final Player one = new Player("One", 100);
        final Armament sword = new Armament(Armament.Type.WEAPON, "Sword", 99, 4, 2);
        one.pay(sword);

        final Player two = new Player("Two", 100);
        final Armament dagger = new Armament(Armament.Type.WEAPON, "Dagger", 2, 1, 2);
        two.pay(dagger);

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