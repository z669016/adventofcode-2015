package com.putoet.day22;

abstract class Player {
    private final String name;
    private int hitPoints;

    public Player(Player player) {
        this.name = player.name;
        this.hitPoints = player.hitPoints;
    }

    public Player(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
    }

    public String name() { return name; }
    public int hitPoints() { return hitPoints; }
    public boolean lost() { return hitPoints <= 0; }

    public Player damage(int hitPoints) {
        this.hitPoints -= hitPoints;
        return this;
    }

    public abstract void attack(Player opponent);
}
