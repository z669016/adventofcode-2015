package com.putoet.day22;

public interface Shield {
    String NAME = "Shield";
    int COST = 113;
    int ARMOR = 7;

    static void cast(Combat combat) {
        System.out.println("Casting " + NAME);
        if (combat.addEffect(new Effect() {
            int timer = 7;
            boolean applied = false;

            @Override
            public String name() {
                return NAME;
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                applied = true;
                System.out.println(String.format("%s deals with %d armor, timer is %d", NAME, ARMOR, timer));

                if (timer == 7) {
                    wizard.armor(ARMOR);
                }
            }

            @Override
            public void unapply(Wizard wizard, Boss boss) {
                if (applied) {
                    if (timer == 1) {
                        wizard.armor(-1 * ARMOR);
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
        })) {
            combat.wizard().charge(COST);
        }
    }
}
