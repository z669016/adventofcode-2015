package com.putoet.day22;

public class Poison {
    static String name() { return "Poison"; }
    static int costs() { return 173; }
    static int damage() { return 3; }
    static int timer() { return 6; };
    static int totalDamage() { return damage() * timer(); }
    static int turns() { return timer() + 1; } // add one because this spell is only active at the start of a turn

    static int castCount;
    static int castCount() { return castCount; }

    static void cast(Combat combat) {
        System.out.println("Casting " + name());

        if (combat.wizard().charge(costs())) {
            castCount++;
            combat.addEffect(effect());
        }
    }

    static Effect effect() {
        return new Effect() {
            int timer = timer();
            boolean applied = false;

            @Override
            public String name() {
                return Poison.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                if (!ended()) {
                    System.out.println(String.format("%s deals with %d damage, timer is %d", name(), damage(), timer));
                    boss.defend(damage());
                    applied = true;
                }
            }

            @Override
            public void unapply(Wizard wizard, Boss boss) {
                if (applied) {
                    if (timer == 1) {
                        System.out.println(String.format("%s effect ended", name()));
                        applied = false;
                    }
                    timer--;
                }
            }

            @Override
            public boolean active() {
                return timer > 1;
            }

            @Override
            public boolean ended() {
                return timer == 0;
            }
        };
    }
}
