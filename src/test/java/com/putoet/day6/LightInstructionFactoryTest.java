package com.putoet.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightInstructionFactoryTest {
    private LightInstructionFactory<Boolean> factory = null;

    @BeforeEach
    public void setup() {
        factory = new OnOffLightInstructionFactory();
    }
    @Test
    public void valueOfAssertionError() {
        assertThrows(IllegalArgumentException.class, () -> factory.valueOf("bla"));
        assertThrows(IllegalArgumentException.class, () -> factory.valueOf("turn on 489,959 through 759,"));
    }

    @Test
    public void valueOfInvalidCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> factory.valueOf("turn on 1,1 through 0,1"));
        assertThrows(IllegalArgumentException.class, () -> factory.valueOf("turn on 1,1 through 1,0"));
        assertThrows(IllegalArgumentException.class, () -> factory.valueOf("turn on 1,1 through 0,0"));
    }

    @Test
    public void valueOf() {
        final LightInstruction<Boolean> instruction = factory.valueOf("turn on 489,959 through 759,964");
        assertEquals(489, instruction.minX());
        assertEquals(959, instruction.minY());
        assertEquals(759, instruction.maxX());
        assertEquals(964, instruction.maxY());
    }
}