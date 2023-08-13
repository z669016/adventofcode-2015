package com.putoet.day14;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

public class Day14 {
    public static void main(String[] args) {
        final var reindeer = ResourceLines.stream("/day14.txt")
                .map(Reindeer::fromDescription)
                .toList();
        final var race = new ReindeerRace(reindeer);

        Timer.run(() -> {
            final var distanceScoreSystem = new DistanceScoreSystem();
            final var winnerOnDistance = race.race(2503, distanceScoreSystem);
            System.out.println("The race was won by " + winnerOnDistance.name() + " with a distance of " + distanceScoreSystem.score(winnerOnDistance));
            return null;
        });

        Timer.run(() -> {
            final var leadScoreSystem = new LeadScoreSystem();
            final var winnerOnLead = race.race(2503, leadScoreSystem);
            System.out.println("The race was won by " + winnerOnLead.name() + " with a distance of " + leadScoreSystem.score(winnerOnLead));
            return null;
        });
    }
}
