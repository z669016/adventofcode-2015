package com.putoet.day22;

public interface Recharge {
    String NAME = "Recharge";
    int COST = 229;
    int MANA = 101;

    static void cast(Combat combat) {
        System.out.println("Casting " + NAME);
        if (combat.addEffect(new Effect() {
            int timer = 5;
            boolean applied = false;

            @Override
            public String name() {
                return NAME;
            }

            @Override
            public void apply(Wizard wizard, Boss boss) {
                applied = true;
                if (timer > 0) {
                    System.out.println(String.format("%s recharges with %d, timer is %d", NAME, MANA, timer));

                    wizard.recharge(MANA);
                }
            }

            @Override
            public void unapply(Wizard wizard, Boss boss) {
                if (applied) {
                    if (timer > 0)
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
