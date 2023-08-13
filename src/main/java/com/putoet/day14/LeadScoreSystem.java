package com.putoet.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LeadScoreSystem implements ScoreSystem {
    private final Map<Reindeer,Integer> score = new HashMap<>();

    @Override
    public void update(int elapsedTime, List<Reindeer> reindeer) {
        assert elapsedTime > 0;
        assert reindeer != null && !reindeer.isEmpty();

        final var distance = new HashMap<Reindeer,Integer>();
        reindeer.forEach(r -> distance.put(r, r.distance(elapsedTime)));

        final var max = distance.values().stream().mapToInt(i -> i).max().orElseThrow();
        distance.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .forEach(r -> score.put(r, score.getOrDefault(r, 0) + 1));
    }

    @Override
    public Reindeer winner() {
        if (score.isEmpty())
            throw new IllegalStateException("No contenders on the race");

        return score.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    @Override
    public int score(Reindeer reindeer) {
        return score.getOrDefault(reindeer, 0);
    }
}
