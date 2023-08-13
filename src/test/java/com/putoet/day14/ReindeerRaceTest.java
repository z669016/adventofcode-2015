package com.putoet.day14;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReindeerRaceTest {
    private final Reindeer comet = Reindeer.fromDescription("Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.");
    private final Reindeer dancer = Reindeer.fromDescription("Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.");
    private final List<Reindeer> list = List.of(comet, dancer);

    @Test
    void race() {
        final var race = new ReindeerRace(list);
        final var winner = race.race(1, new DistanceScoreSystem());
        assertEquals(dancer, winner);
    }

    @Test
    void scoreDistance() {
        final var race = new ReindeerRace(list);
        final var distanceScoreSystem = new DistanceScoreSystem();
        final var winner = race.race(1, distanceScoreSystem);
        assertEquals(16, distanceScoreSystem.score(winner));
    }

    @Test
    void scoreLead() {
        final var race = new ReindeerRace(list);
        final var leadScoreSystem = new LeadScoreSystem();
        final var winner = race.race(2, leadScoreSystem);
        assertEquals(2, leadScoreSystem.score(winner));
    }
}