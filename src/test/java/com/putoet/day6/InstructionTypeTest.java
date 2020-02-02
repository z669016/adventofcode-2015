package com.putoet.day6;

import org.junit.jupiter.api.Test;

import static com.putoet.day6.InstructionType.*;
import static org.junit.jupiter.api.Assertions.*;

class InstructionTypeTest {

    @Test
    public void ofNull() {
        assertThrows(AssertionError.class, () -> InstructionType.of(null));
    }

    @Test
    void ofToggle() {
        assertEquals(TOGGLE, InstructionType.of("toggle 756,965 through 812,992"));
        assertEquals(TOGGLE, InstructionType.of("TOGGLE 756,965 THROUGH 812,992"));
    }

    @Test
    public void ofTurnOn() {
        assertEquals(TURN_ON, InstructionType.of("turn on 489,959 through 759,964"));
        assertEquals(TURN_ON, InstructionType.of("TURN on 489,959 THROUGH 759,964"));
    }

    @Test
    public void ofTurnOff() {
        assertEquals(TURN_OFF, InstructionType.of("turn off 820,516 through 871,914"));
        assertEquals(TURN_OFF, InstructionType.of("turn OFF 820,516 THROUGH 871,914"));
    }

    @Test
    public void ofInvalid() {
        assertThrows(IllegalArgumentException.class, () -> InstructionType.of(""));
        assertThrows(IllegalArgumentException.class, () -> InstructionType.of("turnon "));
        assertThrows(IllegalArgumentException.class, () -> InstructionType.of("toggle"));
        assertThrows(IllegalArgumentException.class, () -> InstructionType.of("turn offbla"));
    }
}