package com.putoet.day23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {
    private Register ip;
    private Instruction instr;

    @BeforeEach
    void setup() {
        ip = new Register(Processor.IP);
        ip.accept(11);

        instr = new Instruction("instr", ip) {};
    }

    @Test
    void invallid() {
        assertThrows(AssertionError.class, () -> new Instruction("instr", null) {});
    }

    @Test
    void increment() {
        instr.increment();
        assertEquals(12, ip.get());
    }

    @Test
    void jumpRelative() {
        instr.jumpRelative(-5);
        assertEquals(6, ip.get());
    }

    @Test
    void jumpAbsolute() {
        instr.jumpAbsolute(0);
        assertEquals(0, ip.get());
    }
}