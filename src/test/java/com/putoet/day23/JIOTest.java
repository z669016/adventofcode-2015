package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JIOTest {

    @Test
    void runOne() {
        final Register ip = new Register(Processor.IP);
        final Register a = new Register(Processor.A);
        ip.accept(11);
        a.accept(1);

        final JIO jio = new JIO(ip, a, -3);
        jio.run();

        assertEquals(8, ip.get());
    }

    @Test
    void runAny() {
        final Register ip = new Register(Processor.IP);
        final Register a = new Register(Processor.A);
        ip.accept(11);
        a.accept(3);

        final JIO jio = new JIO(ip, a, -3);
        jio.run();

        assertEquals(12, ip.get());
    }
}