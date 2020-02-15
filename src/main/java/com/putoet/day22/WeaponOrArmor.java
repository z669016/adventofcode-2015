package com.putoet.day22;

public class WeaponOrArmor implements Armament {
    private final Type type;
    private final String name;
    private final int cost;
    private final int damage;
    private final int armor;

    public WeaponOrArmor(Type type, String name, int cost, int damage, int armor) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
    }

    @Override
    public Type type() { return type; }
    @Override
    public String name() { return name; }
    @Override
    public int damage() { return damage; }
    @Override
    public int armor() { return armor; }
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
    @Override
    public int cost() { return cost; }
}
