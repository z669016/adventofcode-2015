package com.putoet.day22;

public interface Effect {
    String name();
    void apply(Wizard wizard, Boss boss);
    Effect duplicate();

    int timer();

    default boolean active() {
        return timer() > 0;
    }

    default boolean ended() {
        return timer() <= 0;
    }
}
