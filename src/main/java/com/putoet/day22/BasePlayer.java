package com.putoet.day22;

import java.util.Objects;

public abstract class BasePlayer implements Player {
    protected final String name;
    protected int hitPoints = 100;

    public BasePlayer(String name) {
        assert name != null && name.trim().length() > 0;
        this.name = name.trim();
    }

    @Override
    public String name() { return name; }

    @Override
    public int hitPoints() { return hitPoints; }

    @Override
    public void attack(Player player) {
        final int damage = damage();
        player.defend(Armament.Type.HANDS, damage);
    }

    @Override
    public void heal(int healing) {
        throw new IllegalStateException("Healing not possible for this player '" + name + "'");
    }

    @Override
    public abstract int damage();

    @Override
    public void defend(Armament.Type type, int damage) {
        final int armor = (type != Armament.Type.SPELL ? armor() : 0);
        final int impact = armor > damage ? 1 : damage - armor;

        hitPoints -= impact;
    }

    @Override
    public abstract int armor();

    @Override
    public boolean defeated() {
        return hitPoints <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasePlayer)) return false;
        BasePlayer that = (BasePlayer) o;
        return hitPoints == that.hitPoints &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hitPoints);
    }

    @Override
    public String toString() {
        return "BasePlayer{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                '}';
    }
}
