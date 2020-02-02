package com.putoet.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightInstructionProcessorFactoryTest {
    @Test
    public void valueOf() {
        final LightInstructionProcessor instruction = LightInstructionFactory.valueOf("turn on 489,959 through 759,964");
        assertTrue(instruction instanceof TurnOnLightInstruction);
    }
}