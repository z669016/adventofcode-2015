package com.putoet.day22;

public interface Combattant {
    String name();
    void attack(Combat combat, Combattant opponent);
    void defend(int damage);
    boolean defeated();
    int hitPoints();
}
