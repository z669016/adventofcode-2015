package com.putoet.day21;

public class Armament {
    public enum Type { WEAPON, ARMOR, RING };

    private final Type type;
    private final String name;
    private final int cost;
    private final int damage;
    private final int armor;

    public Armament(Type type, String name, int cost, int damage, int armor) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
    }

    public Type type() { return type; }
    public String name() { return name; }
    public int cost() { return cost; }
    public int damage() { return damage; }
    public int armor() { return armor; }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
