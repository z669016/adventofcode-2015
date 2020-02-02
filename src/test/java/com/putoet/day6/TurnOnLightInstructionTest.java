package com.putoet.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToggleLightInstructionTest {
    @Test
    public void apply() {
        final LightInstruction instruction = new ToggleLightInstruction(0, 0, 0, 0);
        assertTrue(instruction.apply(false));
        assertFalse(instruction.apply(true));
    }
}