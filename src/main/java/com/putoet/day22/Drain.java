package com.putoet.day22;

class Drain {
    static String name() { return "Drain"; }
    static int costs() { return 73; }
    static int damage() { return 2; }
    static int healing() { return 2; }
    static int timer() { return 1; }

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
            public Effect duplicate() {
                return effect(timer);
            }

            @Override
            public int timer() {
                return timer;
            }

            @Override
            public String name() {
                return Drain.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                if (active()) {
                    // System.out.printf("%s deals with %d damage and heals for %d%n", name(), damage(), healing());
                    wizard.damage(-healing());
                    boss.damage(damage());
                    timer--;
                }
            }
        };
    }
}
