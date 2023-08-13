package com.putoet.day14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.putoet.day14.Reindeer.State.FLYING;
import static com.putoet.day14.Reindeer.State.RESTING;
import static org.junit.jupiter.api.Assertions.*;

class ReindeerTest {
    private Reindeer reindeer = null;

    @BeforeEach
    void setup() {
        reindeer = Reindeer.fromDescription("Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.");
    }

    @Test
    void fromDescription() {
        assertEquals("Vixen", reindeer.name());
        assertEquals(19, reindeer.flyingSpeed());
        assertEquals(7, reindeer.flyingTime());
        assertEquals(124, reindeer.requiredRestingTime());
    }

    @Test
    void distance() {
        assertEquals(19, reindeer.distance(1));
        assertEquals(2*19, reindeer.distance(2));
        assertEquals(7*19, reindeer.distance(7));
        assertEquals(7*19, reindeer.distance(8));
        assertEquals(7*19, reindeer.distance(7 + 123));
        assertEquals(7*19, reindeer.distance(7 + 124));
        assertEquals(8*19, reindeer.distance(7 + 124 + 1));
        assertEquals(2*7*19 + 19, reindeer.distance(2 * (7 + 124) + 1));
    }

    @Test
    void state() {
        assertEquals(FLYING, reindeer.state(1));
        assertEquals(FLYING, reindeer.state(2));
        assertEquals(FLYING, reindeer.state(7));
        assertEquals(RESTING, reindeer.state(8));
        assertEquals(RESTING, reindeer.state(7 + 123));
        assertEquals(RESTING, reindeer.state(7 + 124));
        assertEquals(FLYING, reindeer.state(7 + 124 + 1));
        assertEquals(FLYING, reindeer.state(2 * (7 + 124) + 1));
    }

    @Test
    void samples() {
        final var comet = Reindeer.fromDescription("Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.");
        final var dancer = Reindeer.fromDescription("Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.");

        assertEquals(1120, comet.distance(1000));
        assertEquals(1056, dancer.distance(1000));
    }
}