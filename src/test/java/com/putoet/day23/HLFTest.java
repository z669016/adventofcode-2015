package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HLFTest {
    @Test
    void run() {
        final Register ip = new Register(Processor.IP);
        final Register a = new Register(Processor.A);
        a.accept(9);

        final HLF hlf = new HLF(ip, a);
        hlf.run();

        assertEquals(1, ip.get());
        assertEquals(4, a.get());
    }

    @Test
    void runOnZero() {
        final Register ip = new Register(Processor.IP);
        final Register a = new Register(Processor.A);

        final HLF hlf = new HLF(ip, a);
        hlf.run();

        assertEquals(1, ip.get());
        assertEquals(0, a.get());
    }
}