package com.putoet.day22;

public interface Effect {
    String name();
    void apply(Wizard wizard, Boss boss);
    void unapply(Wizard wizard, Boss boss);
    boolean active();
    boolean ended();
}
