package com.putoet.day14;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args) {
        final List<Reindeer> reindeer = ResourceLines.stream("/day14.txt")
                .map(Reindeer::fromDescription)
                .collect(Collectors.toList());

        final ReindeerRace race = new ReindeerRace(reindeer);
        final ScoreSystem distanceScoreSystem = new DistanceScoreSystem();
        final Reindeer winnerOnDistance = race.race(2503, distanceScoreSystem);

        System.out.println("The race was won by " + winnerOnDistance.name() + " with a distance of " + distanceScoreSystem.score(winnerOnDistance));

        final ScoreSystem leadScoreSystem = new LeadScoreSystem();
        final Reindeer winnerOnLead = race.race(2503, leadScoreSystem);

        System.out.println("The race was won by " + winnerOnLead.name() + " with a distance of " + leadScoreSystem.score(winnerOnLead));
    }
}
