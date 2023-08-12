package com.putoet.day21;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArmoryTest {

    @Test
    void stock() {
        final Armory armory = new Armory();

        assertEquals(16, armory.stock().length);
        assertEquals(5, armory.stock(Armament.Type.WEAPON).length);
        assertEquals(5, armory.stock(Armament.Type.ARMOR).length);
        assertEquals(6, armory.stock(Armament.Type.RING).length);
        assertEquals(10, armory.stock(Set.of(Armament.Type.WEAPON, Armament.Type.ARMOR)).length);
    }
}