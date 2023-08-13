package com.putoet.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightInstructionProcessorTest {
    private LightInstructionFactory<Boolean> factory = null;
    private LightInstructionProcessor<Boolean> processor = null;

    @BeforeEach
    public void setup() {
        factory = Day6.onOffInstructionFactory();
        processor = new LightInstructionProcessor<>(false, () -> new Boolean[10][10], b -> b ? 1 : 0);
    }

    @Test
    public void executeAll() {
        final var instruction = factory.of("turn on 0,0 through 9,9");
        processor.execute(instruction);
        assertEquals(100, processor.count());
    }

    @Test
    public void executeOne() {
        final var instruction = factory.of("turn on 1,1 through 1,1");
        processor.execute(instruction);
        assertEquals(1, processor.count());
    }

    @Test
    public void sum() {
        final var factory = Day6.intensityInstructionFactory();
        final var processor = new LightInstructionProcessor<>(0, () -> new Integer[10][10], v -> v);

        processor.execute(factory.of("turn off 0,0 through 9,9"));
        assertEquals(0, processor.count());

        processor.execute(factory.of("turn on 0,0 through 9,9"));
        assertEquals(100, processor.count());
    }
}