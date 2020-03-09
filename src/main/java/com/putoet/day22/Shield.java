package com.putoet.day22;

public class Shield {
    static String name() { return "Shield"; }
    static int costs() { return 113; }
    static int armor() { return 7; }
    static int timer() { return 6; };
    static int turns() { return timer(); }

    static int castCount;
    static int castCount() { return castCount; }

    static void cast(Combat combat) {
        System.out.println("Casting " + name());

        if (combat.wizard().charge(costs())) {
            castCount++;
            final Effect effect = effect();
            combat.addEffect(effect);
            effect.apply(combat.wizard(), combat.boss());
        }
    }

    static Effect effect() {
        return new Effect() {
            int timer = timer();
            boolean applied = false;

            @Override
            public String name() {
                return Shield.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                if (!ended()) {
                    if (!applied) {
                        System.out.println(String.format("%s deals with %d armor, timer is %d", name(), armor(), timer));
                        wizard.armor(armor());
                        applied = true;
                    } else {
                        System.out.println(String.format("%s active, timer is %d", name(), timer));
                    }
                }
            }

            @Override
            public void unapply(Wizard wizard, Boss boss) {
                if (applied) {
                    if (timer == 1) {
                        System.out.println(String.format("%s ending, reducing armor with %d", name(), armor()));
                        wizard.armor(-1 * armor());
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
