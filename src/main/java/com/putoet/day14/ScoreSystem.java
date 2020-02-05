package com.putoet.day14;

import java.util.List;

public interface ScoreSystem {
    void update(int elapsedTime, List<Reindeer> reindeer);
    Reindeer winner();
    int score(Reindeer reindeer);
}
