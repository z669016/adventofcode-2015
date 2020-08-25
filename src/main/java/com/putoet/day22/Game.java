package com.putoet.day22;

import java.util.ArrayList;
import java.util.List;

public class Game implements Comparable<Game> {
    private final Wizard wizard;
    private final Boss boss;
    private final List<Effect> activeEffects = new ArrayList<>();

    private boolean bossTurn = false;
    private boolean hard = false;

    public Game(Wizard wizard, Boss boss) {
        this(wizard, boss, false);
    }

    public Game(Wizard wizard, Boss boss, boolean hard) {
        assert wizard != null;
        assert boss != null;

        this.wizard = wizard;
        this.boss = boss;
        this.hard = hard;
    }

    public Wizard wizard() { return wizard; }
    public Boss boss() { return boss; }

    public Game[] turn() {
        if (!done() && !bossTurn && hard) {
            wizard.damage(1);
        }

        if (!done()) {
            activeEffects.forEach(effect -> {
                effect.apply(wizard, boss);
            });
            activeEffects.removeIf(Effect::ended);
        }

        if (!done()) {
            if (bossTurn) {
                boss.attack(wizard);
                bossTurn = !bossTurn;
                return wizard.lost() ? new Game[]{} : new Game[] {this};
            }
        }

        if (!done()) {
            final List<Game> turns = new ArrayList<>();
            if (canCast(MagicMissile.name(), MagicMissile.costs())) {
                turns.add(duplicate().castMagicMissile());
            }
            if (canCast(Drain.name(), Drain.costs())) {
                turns.add(duplicate().castDrain());
            }
            if (canCast(Shield.name(), Shield.costs())) {
                turns.add(duplicate().castShield());
            }
            if (canCast(Recharge.name(), Recharge.costs())) {
                turns.add(duplicate().castRecharge());
            }
            if (canCast(Poison.name(), Poison.costs())) {
                turns.add(duplicate().castPoison());
            }

            return turns.toArray(Game[]::new);
        }

        return wizard.lost() ? new Game[]{} : new Game[] {this};
    }

    private Game castMagicMissile() {
        activeEffects.add(MagicMissile.cast(wizard, boss));
        return this;
    }

    private Game castDrain() {
        activeEffects.add(Drain.cast(wizard, boss));
        return this;
    }

    private Game castShield() {
        activeEffects.add(Shield.cast(wizard, boss));
        return this;
    }

    private Game castPoison() {
        activeEffects.add(Poison.cast(wizard, boss));
        return this;
    }

    private Game castRecharge() {
        activeEffects.add(Recharge.cast(wizard, boss));
        return this;
    }

    private boolean canCast(String spell, int costs) {
        return activeEffects.stream().noneMatch(effect -> effect.name().equals(spell) && effect.active())
                && wizard.mana() >= costs;
    }

    public Game duplicate() {
        final Game copy = new Game(new Wizard(wizard), new Boss(boss), hard);
        activeEffects.forEach(effect -> copy.activeEffects.add(effect.duplicate()));
        copy.bossTurn = !this.bossTurn;

        return copy;
    }

    public boolean done() { return wizard.lost() || boss.lost(); }

    @Override
    public int compareTo(Game other) {
        return Integer.compare(this.wizard.spend(), other.wizard.spend());
    }

    @Override
    public String toString() {
        return "Game{" +
                "wizard=" + wizard +
                ", boss=" + boss +
                // ", activeEffects=" + activeEffects +
                ", bossTurn=" + bossTurn +
                '}';
    }
}
