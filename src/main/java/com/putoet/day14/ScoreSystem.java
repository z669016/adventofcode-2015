package com.putoet.day14;

import java.util.List;

interface ScoreSystem {
    void update(int elapsedTime, List<Reindeer> reindeer);
    Reindeer winner();
    int score(Reindeer reindeer);
}
