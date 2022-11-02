package com.putoet.day1;

import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {
    public static Pair<Integer,Integer> transform(final int symbol, final AtomicInteger counter) {

        return Pair.with(switch (symbol) {
            case '(' -> +1;
            case ')' -> -1;
            default -> throw new IllegalArgumentException("Illegal symbol '" + (char) symbol + "' at position " + counter.get());
        }, counter.getAndIncrement());
    }

    public static int finalFloor(final String input, final AtomicInteger counter) {
        return input.chars().mapToObj(c -> transform(c, counter)).mapToInt(Pair::getValue0).sum();
    }

    public static Optional<Integer> basement(final String input, final AtomicInteger counter) {
        var basement = input.chars()
                .mapToObj(c -> transform(c, counter))
                .reduce(Pair.with(0, 0), (identity, pair) -> {
                    if (identity.getValue0() < 0)
                        return identity;

                    return Pair.with(identity.getValue0() + pair.getValue0(), pair.getValue1());
                });
        return basement.getValue0() >= 0 ? Optional.empty() : Optional.of(basement.getValue1());
    }

    public static void main(final String[] args) {
        final String input = ResourceLines.line("/day1.txt");

        System.out.println("Solution day 1: " + finalFloor(input, new AtomicInteger(1)));
        final Optional<Integer> basement = basement(input, new AtomicInteger(1));
        if (basement.isPresent())
            System.out.println("Entered the basement at position " + basement.get());
        else
            System.out.println("Never entered the basement");
    }
}
