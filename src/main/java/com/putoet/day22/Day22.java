package com.putoet.day22;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Day22 {
    public static void main(String[] args) {
        final Boss boss = new Boss(71, 10);
        final Wizard wizard = new Wizard(50, 500, new Strategy(boss));
        final Combat combat = new Combat(wizard, boss);

        final Combattant winner = combat.start();

        System.out.println("The after " + combat.turnCount() + " turns, the winner is " + winner);
        if (winner == wizard) {
            System.out.println("Wizard used " + wizard.charged() + " mana.");
        }

        System.out.println("Used spells:");
        System.out.println(MagicMissile.name() + ": " + MagicMissile.castCount());
        System.out.println(Drain.name() + ": " + Drain.castCount());
        System.out.println(Shield.name() + ": " + Shield.castCount());
        System.out.println(Poison.name() + ": " + Poison.castCount());
        System.out.println(Recharge.name() + ": " + Recharge.castCount());
    }

    // 498 is too low ... bug in Shield which made it last too long
    // 2575 is too high ... Shield + Recharge + Potion + Missile (if all are active)
    // 2455 is not right ... limited posions used ((71 / 18) + 1) (just enough to kill the boss)
    // 2226 is not right ... Shield + Recharge (if boss hitpoints > 10) + Potion + Missile (if all are active)
    // 1997 is not right ...
    // 1937 is not right ...
}

class Strategy implements Supplier<Consumer<Combat>> {
    private int poisonCount;
    private int rechargeCount;

    Strategy(Boss boss) {
        this.rechargeCount = 3;
        this.poisonCount = (boss.hitPoints() / Poison.totalDamage());
    }

    @Override
    public Consumer<Combat> get() {
        return combat -> {
            if (!combat.isEffective(Recharge.name()) && (rechargeCount-- > 0))
                Recharge.cast(combat);
            else if (!combat.isEffective(Shield.name()))
                Shield.cast(combat);
            else if (!combat.isEffective(Poison.name()) && (poisonCount-- > 0))
                Poison.cast(combat);
            else
                MagicMissile.cast(combat);
        };
    }
}