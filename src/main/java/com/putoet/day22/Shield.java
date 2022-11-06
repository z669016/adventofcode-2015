package com.putoet.day22;

public class Shield {
    static String name() { return "Shield"; }
    static int costs() { return 113; }
    static int armor() { return 7; }
    static int timer() { return 7; }

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
            int timer = initialTimer;

            @Override
            public String name() {
                return Shield.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                if (active()) {
                    // System.out.printf("%s deals with %d armor, timer is %d%n", name(), armor(), timer);
                    if (timer == Shield.timer())
                        wizard.armor(armor());

                    timer--;

                    if(ended())
                        wizard.armor(-armor());
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
