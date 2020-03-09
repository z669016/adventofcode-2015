package com.putoet.day22;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Wizard implements Combattant {
    private final Supplier<Consumer<Combat>> assaultPlan;
    private int charged;
    private int hitPoints;
    private int armor;
    private int mana;

    public Wizard(int hitPoints, int mana, Supplier<Consumer<Combat>> assaultPlan) {
        assert assaultPlan != null;

        this.assaultPlan = assaultPlan;
        this.hitPoints = hitPoints;
        this.mana = mana;
        this.armor = 0;
        this.charged = 0;
    }

    @Override
    public String name() {
        return "Wizard";
    }

    @Override
    public void attack(Combat combat, Combattant opponent) {
        assert combat != null;
        assert opponent != null;

        final Consumer<Combat> attack = assaultPlan.get();
        if (attack != null)
            attack.accept(combat);
    }

    @Override
    public void defend(int damage) {
        hitPoints -= (armor >= damage ? 1 : damage - armor);
    }

    @Override
    public boolean defeated() {
        return (hitPoints <= 0) || (mana <= 0);
    }

    @Override
    public int hitPoints() {
        return hitPoints;
    }

    public void heal(int hitPoints) {
        this.hitPoints += hitPoints;
    }

    public boolean charge(int mana) {
        if (mana > this.mana)
            return false;

        this.mana -= mana;
        this.charged += mana;
        return true;
    }

    public int charged() {
        return charged;
    }

    public void recharge(int mana) {
        this.mana += mana;
    }

    public void armor(int armor) {
        this.armor += armor;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "hitPoints=" + hitPoints +
                ", armor=" + armor +
                ", mana=" + mana +
                '}';
    }
}
