package com.putoet.day1;

import com.putoet.resources.ResourceLines;

import java.util.Optional;

public class Day1 {
    public static int transform(int symbol) {
        return switch (symbol) {
            case '(' -> +1;
            case ')' -> -1;
            default -> throw new IllegalArgumentException("Illegal symbol '" + (char) symbol + "'");
        };
    }

    public static int finalFloor(String input) {
        return input.chars().map(Day1::transform).sum();
    }

    public static Optional<Integer> basement(String input) {
        int idx = 0;
        int floor = 0;
        do {
            floor += transform(input.charAt(idx++));
        } while (floor >= 0 && idx < input.length());

        return (floor < 0 ? Optional.of(idx) : Optional.empty());
    }

    public static void main(String[] args) {
        final String input = ResourceLines.line("/day1.txt");

        System.out.println("Solution day 1: " + finalFloor(input));

        final Optional<Integer> basement = basement(input);
        if (basement.isPresent())
            System.out.println("Entered the basement at position " + basement.get());
        else
            System.out.println("Never entered the basement");
    }
}
