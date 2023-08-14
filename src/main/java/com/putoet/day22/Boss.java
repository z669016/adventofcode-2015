package com.putoet.day22;

class Boss extends Player {
    private final int damage;

    public Boss(int hitPoints, int damage) {
        super("Boss", hitPoints);
        this.damage = damage;
    }

    public Boss(Boss boss) {
        super(boss);
        this.damage = boss.damage;
    }

    @Override
    public void attack(Player opponent) {
        opponent.damage(damage);
    }

    @Override
    public String toString() {
        return "Boss{" +
                "name=" + name() +
                ", hitPoints=" + hitPoints() +
                ", damage=" + damage +
                '}';
    }
}
