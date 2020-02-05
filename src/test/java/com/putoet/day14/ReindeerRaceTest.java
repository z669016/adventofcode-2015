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
        final ReindeerRace race = new ReindeerRace(list);
        final Reindeer winner = race.race(1, new DistanceScoreSystem());
        assertEquals(dancer, winner);
    }

    @Test
    void scoreDistance() {
        final ReindeerRace race = new ReindeerRace(list);
        final ScoreSystem distanceScoreSystem = new DistanceScoreSystem();
        final Reindeer winner = race.race(1, distanceScoreSystem);
        assertEquals(16, distanceScoreSystem.score(winner));
    }

    @Test
    void scoreLead() {
        final ReindeerRace race = new ReindeerRace(list);
        final ScoreSystem leadScoreSystem = new LeadScoreSystem();
        final Reindeer winner = race.race(2, leadScoreSystem);
        assertEquals(2, leadScoreSystem.score(winner));
    }
}