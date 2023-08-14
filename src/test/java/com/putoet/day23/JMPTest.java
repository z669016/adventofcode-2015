package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JMPTest {
    @Test
    void invallid() {
        assertThrows(AssertionError.class, () -> new JMP(new Register(Processor.IP), 0));
    }

    @Test
    void run() {
        final var ip = new Register(Processor.IP);
        ip.accept(3);

        final var jmp = new JMP(ip, 2);
        jmp.run();

        assertEquals(5, ip.get());
    }
}