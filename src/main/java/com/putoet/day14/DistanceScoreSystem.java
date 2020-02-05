package com.putoet.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceScoreSystem implements ScoreSystem {
    final Map<Reindeer,Integer> distance = new HashMap<>();

    @Override
    public void update(int elapsedTime, List<Reindeer> reindeer) {
        reindeer.forEach(r -> this.distance.put(r, r.distance(elapsedTime)));;
    }

    @Override
    public Reindeer winner() {
        if (distance.size() == 0)
            throw new IllegalStateException("No contenders on the race");

        return distance.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    @Override
    public int score(Reindeer reindeer) {
        return distance.get(reindeer);
    }
}
