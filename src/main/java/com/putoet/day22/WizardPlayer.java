package com.putoet.day22;

import java.util.List;
import java.util.Objects;

public class WizardPlayer extends BasePlayer {
    private final List<Armament> armaments;
    private int mana = 500;

    public WizardPlayer(String name) {
        super(name);

        armaments = List.of(
                new Spell("Magic Missile", 53, 4, 0, 0),
                new Spell("Drain", 73, 2, 0, 2),
                new EffectSpell("Shield", 113, 0, 7, 0, 0, 6),
                new EffectSpell("Poison", 173, 3, 0, 0, 0, 6),
                new EffectSpell("Recharge", 229, 0, 0, 0, 101, 5)
        );
    }

    public List<Armament> armaments() {
        return armaments;
    }

    public int damage() {
        return armaments.stream().mapToInt(Armament::damage).sum();
    }

    public int armor() {
        return armaments.stream().mapToInt(Armament::armor).sum();
    }

    @Override
    public void heal(int healing) {
        hitPoints += healing;
    }

    public void recharge(int mana) {
        this.mana += mana;
    }

    @Override
    public void attack(Player player) {
        if (payForSpells()) {
            final int damage = damage();
            player.defend(Armament.Type.SPELL, damage);
            castAllSpells();
        }
    }

    private boolean payForSpells() {
        int costs = armaments.stream()
                .filter(armament -> armament instanceof EffectSpell)
                .mapToInt(Armament::cost)
                .sum();

        if (costs > mana) {
            hitPoints = 0;
            return false;
        }

        mana -= costs;
        return true;
    }

    private void castAllSpells() {
        armaments.stream()
                .filter(armament -> armament instanceof EffectSpell)
                .map(armament -> (Spell) armament)
                .forEach(spell -> spell.casted(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WizardPlayer)) return false;
        if (!super.equals(o)) return false;
        WizardPlayer that = (WizardPlayer) o;
        return mana == that.mana &&
                armaments.equals(that.armaments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mana, armaments);
    }

    @Override
    public String toString() {
        return "WizardPlayer{" +
                "mana=" + mana +
                ", armaments=" + armaments +
                ", name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                '}';
    }
}
