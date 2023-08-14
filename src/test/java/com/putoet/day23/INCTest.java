package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class INCTest {

    @Test
    void run() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        a.accept(9);

        final var inc = new INC(ip, a);
        inc.run();

        assertEquals(1, ip.get());
        assertEquals(10, a.get());
    }

    @Test
    void runOnZero() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        final var inc = new INC(ip, a);
        inc.run();

        assertEquals(1, ip.get());
        assertEquals(1, a.get());
    }
}