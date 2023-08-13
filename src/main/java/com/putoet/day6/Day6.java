package com.putoet.day6;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

public class Day6 {

    public static final String INPUT = "/day6.txt";

    public static void main(String[] args) {
        Timer.run(Day6::part1);
        Timer.run(Day6::part2);
    }

    private static Void part1() {
        final var factoryOne = onOffInstructionFactory();
        final var processorOne = new LightInstructionProcessor<>(false, () -> new Boolean[1000][1000], b -> b ? 1 : 0);

        ResourceLines.stream(INPUT)
                .map(factoryOne::of)
                .forEach(processorOne::execute);

        System.out.println("Number of lights burning: " + processorOne.count());

        return null;
    }

    private static Void part2() {
        final var factoryTwo = intensityInstructionFactory();
        final var processorTwo = new LightInstructionProcessor<>(0, () -> new Integer[1000][1000], v -> v);

        ResourceLines.stream(INPUT)
                .map(factoryTwo::of)
                .forEach(processorTwo::execute);

        System.out.println("Number of lights burning: " + processorTwo.count());

        return null;
    }

    static LightInstructionFactory<Boolean> onOffInstructionFactory() {
        return new LightInstructionFactory<>(b -> true, b -> false, b -> !b);
    }

    static LightInstructionFactory<Integer> intensityInstructionFactory() {
        return new LightInstructionFactory<>(i -> i + 1, i -> (i > 0 ? i - 1 : 0), i -> i + 2);
    }
}
