package com.putoet.day6;

public class IntensityLightInstructionFactory extends LightInstructionFactory<Integer> {
    public IntensityLightInstructionFactory() {
        super(i -> i + 1, i -> (i > 0? i - 1 : 0), i -> i + 2);
    }
}
