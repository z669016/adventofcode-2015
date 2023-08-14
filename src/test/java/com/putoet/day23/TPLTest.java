package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TPLTest {

    @Test
    void run() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        a.accept(9);

        final var tpl = new TPL(ip, a);
        tpl.run();

        assertEquals(1, ip.get());
        assertEquals(27, a.get());
    }

    @Test
    void runOnZero() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        final var tpl = new TPL(ip, a);
        tpl.run();

        assertEquals(1, ip.get());
        assertEquals(0, a.get());
    }
}