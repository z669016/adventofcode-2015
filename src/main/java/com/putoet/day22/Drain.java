package com.putoet.day22;

public class Drain {
    static String name() { return "Drain"; }
    static int costs() { return 73; }
    static int damage() { return 2; }
    static int healing() { return 2; }
    static int timer() { return 1; };
    static int totalDamage() { return damage() * timer(); }
    static int turns() { return timer(); }

    static int castCount;
    static int castCount() { return castCount; }

    static void cast(Combat combat) {
        if (combat.wizard().charge(costs())) {
            castCount++;
            System.out.println("Casting " + name());
            effect().apply(combat.wizard(), combat.boss());
        }
    }

    static Effect effect() {
        return new Effect() {

            @Override
            public String name() {
                return Drain.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                System.out.println(String.format("%s deals with %d damage and heals for %d", name(), damage(), healing()));
                wizard.heal(healing());
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
