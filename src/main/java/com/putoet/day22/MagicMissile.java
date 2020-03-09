package com.putoet.day22;

public class MagicMissile {
    static String name() { return "Magic Missile"; }
    static int costs() { return 53; }
    static int damage() { return 4; }
    static int timer() { return 1; };
    static int totalDamage() { return damage() * timer(); }
    static int turns() { return timer(); }

    static int castCount;
    static int castCount() { return castCount; }

    static void cast(Combat combat) {
        if (combat.wizard().charge(costs())) {
            castCount++;
            System.out.println(String.format("%s deals with %d damage", name(), damage()));
            effect().apply(combat.wizard(), combat.boss());
        }
    }

    static Effect effect() {
        return new Effect() {

            @Override
            public String name() {
                return MagicMissile.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                boss.defend(damage());
            }

            @Override
            public void unapply(Wizard wizard, Boss boss) {
            }

            @Override
            public boolean active() {
                return false;
            }

            @Override
            public boolean ended() {
                return true;
            }
        };
    }
}
