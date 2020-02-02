package com.putoet.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnOnLightInstructionTest {
    @Test
    public void apply() {
        final LightInstruction instruction = new TurnOnLightInstruction(0, 0, 0, 0);
        assertTrue(instruction.apply(false));
        assertTrue(instruction.apply(true));
    }
}