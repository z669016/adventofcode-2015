package com.putoet.day22;

public class MagicMissile {
    static String name() { return "Magic Missile"; }
    static int costs() { return 53; }
    static int damage() { return 4; }
    static int timer() { return 1; };

    public static Effect cast(Wizard wizard, Boss boss) {
        if (wizard.mana() >= costs()) {
            wizard.charge(costs());
            final Effect effect = effect();
            effect.apply(wizard, boss);
            return effect;
        }

        throw new IllegalStateException("Wizard cannot afford another " + name());
    }

    private static Effect effect() {
        return effect(timer());
    }

    private static Effect effect(int initialTimer) {
        return new Effect() {
            private int timer = initialTimer;

            @Override
            public String name() {
                return MagicMissile.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                if (active()) {
                    // System.out.printf("%s deals with %d damage%n", name(), damage());
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
