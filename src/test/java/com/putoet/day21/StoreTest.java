package com.putoet.day21;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

//    @Test
//    void sell() {
//        final Store store = new Store();
//        final Player player = new Player("Margot", 100);
//
//        store.sell(player, "Greataxe");
//        assertEquals(26, player.gold());
//        assertEquals(74, store.cash());
//        store.sell(player, "Dagger");
//        assertEquals(18, player.gold());
//        assertEquals(82, store.cash());
//
//        assertThrows(IllegalArgumentException.class, () -> store.sell(player, "Dagger"));
//    }
//
    @Test
    void stock() {
        final Store store = new Store();
        assertEquals(16, store.stock().size());
        assertEquals(5, store.stock(Armament.Type.WEAPON).size());
        assertEquals(5, store.stock(Armament.Type.ARMOR).size());
        assertEquals(6, store.stock(Armament.Type.RING).size());
        assertEquals(10, store.stock(Set.of(Armament.Type.WEAPON, Armament.Type.ARMOR)).size());
    }
}