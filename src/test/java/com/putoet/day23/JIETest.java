package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JIETest {

    @Test
    void runEven() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        ip.accept(11);
        a.accept(4);

        final var jie = new JIE(ip, a, -3);
        jie.run();

        assertEquals(8, ip.get());
    }

    @Test
    void runOdd() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        ip.accept(11);
        a.accept(3);

        final var jie = new JIE(ip, a, -3);
        jie.run();

        assertEquals(12, ip.get());
    }
}