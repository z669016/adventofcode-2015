package com.putoet.day22;

public interface Armament {
    Type type();

    String name();

    int damage();

    int armor();

    int cost();

    public enum Type { HANDS, WEAPON, ARMOR, RING, SPELL }
}
