package com.putoet.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightInstructionProcessorTest {
    private LightInstructionFactory<Boolean> factory = null;
    private LightInstructionProcessor<Boolean> processor = null;

    @BeforeEach
    public void setup() {
        factory = new OnOffLightInstructionFactory();
        processor = new LightInstructionProcessor<Boolean>(false, () -> new Boolean[10][10]);
    }

    @Test
    public void executeAll() {
        final LightInstruction<Boolean> instruction = factory.valueOf("turn on 0,0 through 9,9");
        processor.execute(instruction);
        assertEquals(100, processor.count(b -> b));
    }

    @Test
    public void executeOne() {
        final LightInstruction instruction = factory.valueOf("turn on 1,1 through 1,1");
        processor.execute(instruction);
        assertEquals(1, processor.count(b -> b));
    }

    @Test
    public void sum() {
        final LightInstructionFactory<Integer> factory = new IntensityLightInstructionFactory();
        final IntensityLightInstructionProcessor processor = new IntensityLightInstructionProcessor(0, () -> new Integer[10][10]);

        processor.execute(factory.valueOf("turn off 0,0 through 9,9"));
        assertEquals(0, processor.sum());

        processor.execute(factory.valueOf("turn on 0,0 through 9,9"));
        assertEquals(100, processor.sum());
    }

}