package com.putoet.day6;

import java.util.Arrays;
import java.util.function.Supplier;

public class IntensityLightInstructionProcessor extends LightInstructionProcessor<Integer> {
    public IntensityLightInstructionProcessor(Integer initialValue, Supplier<Integer[][]> supplier) {
        super(initialValue, supplier);
    }

    public long sum() {
        return Arrays.stream(grid).flatMap(Arrays::stream).mapToLong(i -> i).sum();
    }
}
