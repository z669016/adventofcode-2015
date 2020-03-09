package com.putoet.day22;

public class Recharge {
    static String name() { return "Recharge"; }
    static int costs() { return 229; }
    static int mana() { return 101; }
    static int timer() { return 5; };
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
                return Recharge.name();
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                applied = true;
                if (timer > 0) {
                    System.out.println(String.format("%s recharges with %d, timer is %d", name(), mana(), timer));

                    wizard.recharge(mana());
                }
            }

            @Override
            public void unapply(Wizard wizard, Boss boss) {
                if (applied) {
                    if (timer == 1) {
                        System.out.println(String.format("%s ended", name()));
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
