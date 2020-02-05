package com.putoet.day14;

import java.util.List;

public class ReindeerRace {
    private final List<Reindeer> reindeer;

    public ReindeerRace(List<Reindeer> reindeer) {
        assert reindeer != null;

        this.reindeer = reindeer;
    }

    public Reindeer race(int raceDurationInSeconds, ScoreSystem scoreSystem) {
        assert raceDurationInSeconds > 0;
        assert scoreSystem != null;

        for (int idx = 1; idx <= raceDurationInSeconds; idx++) {
            scoreSystem.update(idx, reindeer);
        }

        return scoreSystem.winner();
    }
}
