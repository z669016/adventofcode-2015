package com.putoet.day22;

import java.util.Optional;
import java.util.PriorityQueue;

public class Day22 {
    public static void main(String[] args) {
        Game game = partOne();
        play("Part 1: ", game);

        game = partTwo();
        play("Part 2: ", game);
    }

    private static void play(String label, Game game) {
        final Optional<Game> winner = Day22.findCheapestWin(game);
        if (winner.isEmpty())
            System.out.println(label + "Wizard lost !");
        else
            System.out.println(label + "Wizard won, spending " + winner.get().wizard().spend());
    }

    private static Game partOne() {
        final Wizard wizard = new Wizard(50, 500);
        final Boss boss = new Boss(71, 10);
        return new Game(wizard, boss);
    }

    private static Game partTwo() {
        final Wizard wizard = new Wizard(50, 500);
        final Boss boss = new Boss(71, 10);
        return new Game(wizard, boss, true);
    }

    public static Optional<Game> findCheapestWin(Game initialGame) {
        final PriorityQueue<Game> queue = new PriorityQueue<>();
        queue.offer(initialGame);

        Game game = initialGame;
        Game cheapest = null;
        while(!queue.isEmpty()) {
            game = queue.poll();
            if (cheapest == null || game.wizard().spend() < cheapest.wizard().spend()) {
                if (!game.done()) {
                    Game[] nextGames = game.turn();
                    for (Game nextGame : nextGames) {
                        queue.offer(nextGame);
                    }
                } else {
                    if (game.boss().lost()) {
                        if (cheapest == null)
                            cheapest = game;
                        else
                            cheapest = game.wizard().spend() < cheapest.wizard().spend() ? game : cheapest;
                    }
                }
            }
        }

        return Optional.ofNullable(cheapest);
    }
}
