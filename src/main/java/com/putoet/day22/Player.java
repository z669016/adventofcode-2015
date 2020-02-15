package com.putoet.day22;

public interface Player {
    String name();

    int hitPoints();

    void attack(Player player);

    int damage();

    void defend(Armament.Type type, int damage);

    int armor();

    boolean defeated();

    void heal(int healing);
}
