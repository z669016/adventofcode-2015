package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {
    @Test
    void game1() {
        final var wizard = new Wizard(10, 250);
        final var boss = new Boss(13, 8);
        final var game = new Game(wizard, boss);

        final var winner = Day22.findCheapestWin(game);
        assertTrue(winner.isPresent());
        assertEquals(226, winner.get().wizard().spend());
        assertEquals(24, winner.get().wizard().mana());
        assertEquals(2, winner.get().wizard().hitPoints());
        assertEquals(0, winner.get().boss().hitPoints());
    }

    @Test
    void game2() {
        final var wizard = new Wizard(10, 250);
        final var boss = new Boss(14, 8);
        final var game = new Game(wizard, boss);

        final var winner = Day22.findCheapestWin(game);
        assertEquals(641, winner.orElseThrow().wizard().spend());
        assertEquals(114, winner.orElseThrow().wizard().mana());
        assertEquals(1, winner.orElseThrow().wizard().hitPoints());
        assertEquals(-1, winner.orElseThrow().boss().hitPoints());
    }

    @Test
    void queue() {
        final var queue = new PriorityQueue<Game>();
        final var spend100 = mock(Wizard.class);
        final var spend10 = mock(Wizard.class);
        final var spend1 = mock(Wizard.class);
        final var boss = mock(Boss.class);

        when(spend100.spend()).thenReturn(100);
        when(spend10.spend()).thenReturn(10);
        when(spend1.spend()).thenReturn(1);

        queue.offer(new Game(spend100, boss));
        queue.offer(new Game(spend10, boss));
        queue.offer(new Game(spend1, boss));

        assertEquals(spend1, Objects.requireNonNull(queue.poll()).wizard());
        assertEquals(spend10, Objects.requireNonNull(queue.poll()).wizard());
        assertEquals(spend100, Objects.requireNonNull(queue.poll()).wizard());
    }
}