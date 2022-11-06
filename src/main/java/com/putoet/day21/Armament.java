package com.putoet.day21;

public record Armament(Armament.Type type, String name, int cost, int damage, int armor) {
    public enum Type {WEAPON, ARMOR, RING}
}
