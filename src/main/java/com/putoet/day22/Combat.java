package com.putoet.day22;

import java.util.ArrayList;
import java.util.List;

public class Combat {
    private final List<Effect> effects = new ArrayList<>();
    private final Wizard wizard;
    private final Boss boss;

    public Combat(Wizard wizard, Boss boss) {
        assert wizard != null;
        assert boss != null;

        this.wizard = wizard;
        this.boss = boss;
    }

    public Boss boss() { return boss; }
    public Wizard wizard() { return wizard; }

    public Combattant start() {
        Combattant attacker = wizard;
        Combattant opponent = boss;

        while (!wizard.defeated() && !boss.defeated()) {
            System.out.println(wizard);
            System.out.println(boss);

            effects.forEach(effect -> effect.apply(wizard, boss));
            if (!attacker.defeated())
                attacker.attack(this, opponent);

            effects.forEach(effect -> effect.unapply(wizard, boss));
            effects.removeIf(Effect::ended);

            final Combattant temp = attacker;
            attacker = opponent;
            opponent = temp;

            System.out.println();
        }
        if (wizard.defeated()) System.out.println("Wizzard is defeated");
        if (boss.defeated()) System.out.println("Boss is defeated");

        return wizard.defeated() ? boss : wizard;
    }

    boolean isEffective(String name) {
        assert name != null;

        return effects.stream().anyMatch(listedEffect -> listedEffect.name().equals(name) && !listedEffect.ended());
    }

    boolean addEffect(Effect effect) {
        assert effect != null;

        if (isEffective(effect.name()))
            return false;

        return effects.add(effect);
    }
}
