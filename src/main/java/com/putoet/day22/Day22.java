package com.putoet.day22;

import com.putoet.utils.Timer;

import java.util.Optional;
import java.util.PriorityQueue;

class Day22 {
    public static void main(String[] args) {
        Timer.run(() -> {
            var game = partOne();
            play("Part 1: ", game);
        });

        Timer.run(() -> {
            var game = partTwo();
            play("Part 2: ", game);
        });
    }

    private static Game partOne() {
        final var wizard = new Wizard(50, 500);
        final var boss = new Boss(71, 10);
        return new Game(wizard, boss);
    }

    private static Game partTwo() {
        final var wizard = new Wizard(50, 500);
        final var boss = new Boss(71, 10);
        return new Game(wizard, boss, true);
    }

    private static void play(String label, Game game) {
        final var winner = findCheapestWin(game);
        if (winner.isEmpty())
            System.out.println(label + "Wizard lost!");
        else
            System.out.println(label + "Wizard won, spending " + winner.get().wizard().spend());
    }

    public static Optional<Game> findCheapestWin(Game initialGame) {
        final var queue = new PriorityQueue<Game>();
        queue.offer(initialGame);

        Optional<Game> cheapest = Optional.empty();
        while (!queue.isEmpty()) {
            var game = queue.poll();
            if (cheapest.isEmpty() || game.wizard().spend() < cheapest.get().wizard().spend()) {
                if (!game.done()) {
                    var nextGames = game.turn();
                    for (var nextGame : nextGames) {
                        queue.offer(nextGame);
                    }
                } else {
                    if (game.boss().lost()) {
                        if (cheapest.isEmpty())
                            cheapest = Optional.of(game);
                        else if (game.wizard().spend() < cheapest.get().wizard().spend())
                            cheapest = Optional.of(game);
                    }
                }
            }
        }

        return cheapest;
    }
}
