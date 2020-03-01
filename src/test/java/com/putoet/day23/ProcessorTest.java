package com.putoet.day23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {
    private static final List<String> TEST = List.of(
            "inc a",
            "jio a, +2",
            "tpl a",
            "inc a"
    );

    private Processor processor;

    @BeforeEach
    void setup() {
        processor = new Processor();
    }

    @Test
    void run() {
        processor.setVerbose(true);
        processor.compile(TEST);
        processor.dump();

        System.out.println();
        processor.run();
        assertEquals(2, processor.register(Processor.A).get());
    }

    @Test
    void compile() {
        assertThrows(IllegalArgumentException.class, () -> processor.compile(List.of("inv a")));
    }

    @Test
    void register() {
        assertThrows(IllegalArgumentException.class, () -> processor.register("x"));

        final Register ip = processor.register(Processor.IP);
        assertEquals(ip, processor.register(Processor.IP));

        final Register a = processor.register(Processor.A);
        assertEquals(a, processor.register(Processor.A));
        assertNotEquals(a, ip);

        final Register b = processor.register(Processor.B);
        assertEquals(b, processor.register(Processor.B));
        assertNotEquals(b, ip);
        assertNotEquals(b, a);
    }
}