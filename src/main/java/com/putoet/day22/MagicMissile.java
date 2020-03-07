package com.putoet.day22;

public interface MagicMissile {
    String NAME = "Magic Missile";
    int COST = 53;
    int DAMAGE = 4;

    static void cast(Combat combat) {
        if (combat.wizard().charge(COST)) {
            System.out.println(String.format("%s deals with %d damage", NAME, DAMAGE));

            combat.boss().defend(DAMAGE);
        }
    }
}
