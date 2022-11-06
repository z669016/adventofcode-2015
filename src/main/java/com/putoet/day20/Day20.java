package com.putoet.day20;

import java.util.function.Function;

import com.putoet.math.Factors;

public class Day20 {
    public static void main(String[] args) {
        int input = 36_000_000;

        visits(input, Day20::presentsInfinite);
        visits(input, Day20::presentsFinite);
    }

    private static void visits(int input, Function<Integer, Long> presents) {
        int houseNumber = 0;
        boolean found = false;
        long presentsDelivered = 0L;
        while (!found) {
            houseNumber++;
            System.out.print("\r" + houseNumber);
            presentsDelivered = presents.apply(houseNumber);
            found = presentsDelivered >= input;
        }
        System.out.println("\rHouse number " + houseNumber + " receives " + presentsDelivered);
    }

    public static long presentsInfinite(int houseNumber) {
        return Factors.list(houseNumber).stream()
                .mapToLong(i -> i)
                .sum() * 10;
    }

    public static long presentsFinite(int houseNumber) {
        return Factors.list(houseNumber).stream()
                .filter(i -> i * 50 >= houseNumber)
                .mapToLong(i -> i)
                .sum() * 11;
    }
}
