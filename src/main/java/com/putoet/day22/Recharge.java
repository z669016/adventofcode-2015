package com.putoet.day22;

public class Recharge {
    static String name() { return "Recharge"; }
    static int costs() { return 229; }
    static int mana() { return 101; }
    static int timer() { return 5; }

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
                return Recharge.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                if (active()) {
                    // System.out.printf("%s recharges with %d, timer is %d%n", name(), mana(), timer);
                    wizard.charge(-mana());
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
