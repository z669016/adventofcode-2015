package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {
    @Test
    void game1() {
        final Wizard wizard = new Wizard(10, 250);
        final Boss boss = new Boss(13, 8);
        final Game game = new Game(wizard, boss);

        final Optional<Game> winner = Day22.findCheapestWin(game);
        assertTrue(winner.isPresent());
        assertEquals(226, winner.get().wizard().spend());
        assertEquals(24, winner.get().wizard().mana());
        assertEquals(2, winner.get().wizard().hitPoints());
        assertEquals(0, winner.get().boss().hitPoints());
    }

    @Test
    void game2() {
        Wizard wizard = new Wizard(10, 250);
        Boss boss = new Boss(14, 8);
        Game game = new Game(wizard, boss);

        final Optional<Game> winner = Day22.findCheapestWin(game);
        assertEquals(641, winner.get().wizard().spend());
        assertEquals(114, winner.get().wizard().mana());
        assertEquals(1, winner.get().wizard().hitPoints());
        assertEquals(-1, winner.get().boss().hitPoints());
    }

    @Test
    void queue() {
        final PriorityQueue<Game> queue = new PriorityQueue<>();
        final Wizard spend100 = mock(Wizard.class);
        final Wizard spend10 = mock(Wizard.class);
        final Wizard spend1 = mock(Wizard.class);
        final Boss boss = mock(Boss.class);

        when(spend100.spend()).thenReturn(100);
        when(spend10.spend()).thenReturn(10);
        when(spend1.spend()).thenReturn(1);

        queue.offer(new Game(spend100, boss));
        queue.offer(new Game(spend10, boss));
        queue.offer(new Game(spend1, boss));

        assertEquals(spend1, queue.poll().wizard());
        assertEquals(spend10, queue.poll().wizard());
        assertEquals(spend100, queue.poll().wizard());
    }
}