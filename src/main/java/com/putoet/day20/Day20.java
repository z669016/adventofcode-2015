package com.putoet.day20;

import java.util.function.Function;

import com.putoet.math.Factors;
import com.putoet.utils.Timer;

public class Day20 {
    public static void main(String[] args) {
        int input = 36_000_000;

        Timer.run(() -> visits(input, Day20::presentsInfinite));
        Timer.run(() -> visits(input, Day20::presentsFinite));
    }

    private static void visits(int input, Function<Integer, Long> presents) {
        var houseNumber = 0;
        var found = false;
        var presentsDelivered = 0L;
        while (!found) {
            houseNumber++;
            System.out.print("\r" + houseNumber);
            presentsDelivered = presents.apply(houseNumber);
            found = presentsDelivered >= input;
        }
        System.out.println("\rHouse number " + houseNumber + " receives " + presentsDelivered);
    }

    static long presentsInfinite(int houseNumber) {
        return Factors.list(houseNumber).stream()
                .mapToLong(i -> i)
                .sum() * 10;
    }

    static long presentsFinite(int houseNumber) {
        return Factors.list(houseNumber).stream()
                .filter(i -> i * 50 >= houseNumber)
                .mapToLong(i -> i)
                .sum() * 11;
    }
}
