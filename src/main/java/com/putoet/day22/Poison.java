package com.putoet.day22;

public class Poison {
    static String name() { return "Poison"; }
    static int costs() { return 173; }
    static int damage() { return 3; }
    static int timer() { return 6; };

    public static Effect cast(Wizard wizard, Boss boss) {
        if (wizard.mana() >= costs()) {
            wizard.charge(costs());
            return effect();
        }

        throw new IllegalStateException("Wizard cannot afford another " + name());
    }

    private static Effect effect() {
        return effect(timer());
    }

    private static Effect effect(int initialTimer) {
        return new Effect() {
            int timer = initialTimer;

            @Override
            public String name() {
                return Poison.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                if (active()) {
                    // System.out.printf("%s deals with %d damage, timer is %d%n", name(), damage(), timer);
                    boss.damage(damage());
                    timer--;
                }
            }

            @Override
            public Effect duplicate() {
                return effect(timer);
            }

            @Override
            public int timer() {
                return timer;
            }
        };
    }
}
