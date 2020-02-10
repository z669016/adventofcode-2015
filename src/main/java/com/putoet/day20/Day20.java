package com.putoet.day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day20 {
    public static void main(String[] args) {
        int input = 36_000_000;

        infiniteVisits(input);
        finiteVisits(input);
    }

    protected static void infiniteVisits(int input) {
        int i = 0;
        boolean found = false;
        List<Integer> factors = null;
        while (!found){
            i++;
            factors = factors(i);
            found = presentsInfinite(factors) >= input;
        }
        System.out.println("Factors: " + factors);
        System.out.println("House number " + i + " receives " + presentsInfinite(factors));
    }

    protected static void finiteVisits(int input) {
        int i = 0;
        boolean found = false;
        List<Integer> factors = null;
        while (!found){
            i++;
            System.out.print("\r" + i);
            factors = factors(i);
            found = presentsFinite(i, factors) >= input;
        }
        System.out.println();
        System.out.println("Factors: " + factors);
        System.out.println("House number " + i + " receives " + presentsFinite(i, factors));
    }

    public static List<Integer> factors(int num) {
        final List<Integer> factors = new ArrayList<>();

        final int incrementer = num % 2 == 0 ? 1 : 2;
        for (int i = 1; i <= Math.sqrt(num); i += incrementer) {
            if (num % i == 0) {
                factors.add(i);
                if (i != num / i) {
                    factors.add(num / i);
                }
            }
        }

        Collections.sort(factors);
        return factors;
    }

    public static long presentsInfinite(List<Integer> factors) {
        return factors.stream()
                .mapToInt(i -> i)
                .sum() * 10;
    }

    public static long presentsFinite(int houseNumber, List<Integer> factors) {
        return factors.stream()
                .filter(i -> i * 50 >= houseNumber)
                .mapToInt(i -> i)
                .sum() * 11;
    }
}
