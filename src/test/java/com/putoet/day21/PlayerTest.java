package com.putoet.day21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void pick() {
        final var player = new Player("One");
        final var sword = new Armament(Armament.Type.WEAPON, "Sword", 99, 1, 2);

        player.pick(sword);
        assertTrue(player.armaments().contains(sword));
    }

    @Test
    void attackDefend() {
        final var one = new Player("One");
        final var sword = new Armament(Armament.Type.WEAPON, "Sword", 99, 4, 2);
        one.pick(sword);

        final var two = new Player("Two");
        final var dagger = new Armament(Armament.Type.WEAPON, "Dagger", 2, 1, 2);
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
        final var descriptions = List.of(
                "Hit Points: 109",
                "Damage: 8",
                "Armor: 2");

        var boss = Player.boss();
        assertEquals(100, boss.hitPoints());
        assertEquals(6, boss.damage());
        assertEquals(1, boss.defence());
        assertTrue(boss.armaments().isEmpty());

        boss = Player.boss(descriptions);
        assertEquals(109, boss.hitPoints());
        assertEquals(8, boss.damage());
        assertEquals(2, boss.defence());
        assertTrue(boss.armaments().isEmpty());
    }
}