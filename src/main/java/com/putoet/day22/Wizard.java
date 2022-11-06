package com.putoet.day22;

public class Wizard extends Player {
    private int spend;
    private int mana;
    private int armor;

    public Wizard(int hitPoints, int mana) {
        super("Wizard", hitPoints);
        this.mana = mana;
        this.armor = 0;
        this.spend = 0;
    }

    public Wizard(Wizard wizard) {
        super(wizard);
        this.mana = wizard.mana;
        this.armor = wizard.armor;
        this.spend = wizard.spend;
    }

    public void charge(int mana) {
        if (mana >= 0)
            spend += mana;

        this.mana -= mana;
    }

    public void armor(int armor) {
        this.armor += armor;
    }

    public int mana() {
        return mana;
    }

    public int spend() { return spend; }

    @Override
    public Player damage(int hitPoints) {
        if (hitPoints < 0 || hitPoints == 1)
            super.damage(hitPoints);
        else
            super.damage(Math.max(0, hitPoints - armor));

        return this;
    }

    @Override
    public void attack(Player opponent) {}

    @Override
    public String toString() {
        return "Wizard{" +
                "name=" + name() +
                ", hitPoints=" + hitPoints() +
                ", spend=" + spend +
                ", mana=" + mana +
                ", armor=" + armor +
                '}';
    }
}
