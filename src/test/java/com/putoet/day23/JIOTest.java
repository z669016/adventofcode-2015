package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JIOTest {

    @Test
    void runOne() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        ip.accept(11);
        a.accept(1);

        final var jio = new JIO(ip, a, -3);
        jio.run();

        assertEquals(8, ip.get());
    }

    @Test
    void runAny() {
        final var ip = new Register(Processor.IP);
        final var a = new Register(Processor.A);
        ip.accept(11);
        a.accept(3);

        final var jio = new JIO(ip, a, -3);
        jio.run();

        assertEquals(12, ip.get());
    }
}