package com.putoet.day6;

import com.putoet.resources.ResourceLines;

public class Day6 {
    public static void main(String[] args) {
        final LightInstructionFactory<Boolean> factoryOne = new OnOffLightInstructionFactory();
        final LightInstructionProcessor<Boolean> processorOne = new LightInstructionProcessor<>(false, () -> new Boolean[1000][1000]);

        ResourceLines.stream("/day6.txt")
                .map(factoryOne::valueOf)
                .forEach(processorOne::execute);

        System.out.println("Number of lights burning: " + processorOne.count(b -> b));

        final LightInstructionFactory<Integer> factoryTwo = new IntensityLightInstructionFactory();
        final IntensityLightInstructionProcessor processorTwo = new IntensityLightInstructionProcessor(0, () -> new Integer[1000][1000]);

        ResourceLines.stream("/day6.txt")
                .map(factoryTwo::valueOf)
                .forEach(processorTwo::execute);

        System.out.println("Number of lights burning: " + processorTwo.sum());
    }
}
