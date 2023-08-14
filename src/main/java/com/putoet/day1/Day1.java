package com.putoet.day1;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {
    public record Move(int floor, int position) {
        public Move add(Move second) {
            return floor < 0 ? this : new Move(floor + second.floor, second.position);
        }

        public static Move of(final int symbol, final AtomicInteger counter) {
            return new Move(symbolToFloor(symbol, counter), counter.getAndIncrement());
        }
    }

    private static int symbolToFloor(int symbol, AtomicInteger counter) {
        return switch (symbol) {
            case '(' -> +1;
            case ')' -> -1;
            default ->
                    throw new IllegalArgumentException("Illegal symbol '" + (char) symbol + "' at position " + counter.get());
        };
    }

    public static int finalFloor(final String input, final AtomicInteger counter) {
        return input.chars().map(c -> symbolToFloor(c, counter)).sum();
    }

    public static Optional<Integer> basement(final String input, final AtomicInteger counter) {
        var basement = input.chars()
                .mapToObj(c -> Move.of(c, counter))
                .reduce(new Move(0, 0), Move::add);

        return basement.floor >= 0 ? Optional.empty() : Optional.of(basement.position);
    }

    public static void main(final String[] args) {
        final var input = ResourceLines.line("/day1.txt");

        Timer.run(() ->
                System.out.println("Solution day 1: " + finalFloor(input, new AtomicInteger(1)))
        );

        Timer.run(() -> {
            final var basement = basement(input, new AtomicInteger(1));
            if (basement.isPresent())
                System.out.println("Entered the basement at position " + basement.get());
            else
                System.out.println("Never entered the basement");
        });
    }
}
