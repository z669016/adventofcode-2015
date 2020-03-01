package com.putoet.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class INCTest {

    @Test
    void run() {
        final Register ip = new Register(Processor.IP);
        final Register a = new Register(Processor.A);
        a.accept(9);

        final INC inc = new INC(ip, a);
        inc.run();

        assertEquals(1, ip.get());
        assertEquals(10, a.get());
    }

    @Test
    void runOnZero() {
        final Register ip = new Register(Processor.IP);
        final Register a = new Register(Processor.A);

        final INC inc = new INC(ip, a);
        inc.run();

        assertEquals(1, ip.get());
        assertEquals(1, a.get());
    }
}