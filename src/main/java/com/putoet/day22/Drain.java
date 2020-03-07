package com.putoet.day22;

public interface Drain {
    String NAME = "Drain";
    int COST = 73;
    int DAMAGE = 2;
    int HIT_POINTS = 2;

    static void cast(Combat combat) {
        if (combat.wizard().charge(COST)) {
            System.out.println(String.format("%s deals with %d damage and heals for %d", NAME, DAMAGE, HIT_POINTS));

            combat.wizard().heal(HIT_POINTS);
            combat.boss().defend(DAMAGE);
        }
    }
}
