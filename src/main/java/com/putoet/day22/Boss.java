package com.putoet.day22;

public class Boss implements Combattant {
    private int hitPoints;
    private int damage;

    public Boss(int hitPoints, int damage) {
        this.hitPoints = hitPoints;
        this.damage = damage;
    }

    @Override
    public String name() {
        return "Boss";
    }

    @Override
    public void attack(Combat combat, Combattant opponent) {
        assert combat != null;
        assert opponent != null;

        System.out.println(name() + " attacks with " + damage);
        opponent.defend(damage);
    }

    @Override
    public void defend(int damage) {
        hitPoints -= damage;
    }

    @Override
    public boolean defeated() {
        return hitPoints <= 0;
    }

    @Override
    public int hitPoints() {
        return hitPoints;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "hitPoints=" + hitPoints +
                ", damage=" + damage +
                '}';
    }
}
