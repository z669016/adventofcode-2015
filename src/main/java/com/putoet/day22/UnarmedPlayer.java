package com.putoet.day22;

import java.util.List;
import java.util.Objects;

public class UnarmedPlayer extends BasePlayer {
    private int damage;
    private int armor;

    public UnarmedPlayer(String name) {
        super(name);
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public int armor() {
        return armor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnarmedPlayer)) return false;
        if (!super.equals(o)) return false;
        UnarmedPlayer that = (UnarmedPlayer) o;
        return damage == that.damage &&
                armor == that.armor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), damage, armor);
    }

    @Override
    public String toString() {
        return "UnarmedPlayer{" +
                "damage=" + damage +
                ", armor=" + armor +
                ", name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                '}';
    }

    public static Player boss(List<String> desscriptions) {
        final UnarmedPlayer player = boss();

        try {
            desscriptions.forEach(description -> {
                final String[] words = description.split(":");
                if (words[0].startsWith("Hit Points")) player.hitPoints = Integer.parseInt(words[1].trim());
                else if (words[0].startsWith("Damage")) player.damage = Integer.parseInt(words[1].trim());
                else if (words[0].startsWith("Armor")) player.armor = Integer.parseInt(words[1].trim());
            });
        } catch (NumberFormatException exc) {
            throw new IllegalArgumentException("Invalid player description..." + desscriptions, exc);
        }

        return player;
    }

    public static UnarmedPlayer boss() {
        final UnarmedPlayer player = new UnarmedPlayer("Boss");
        player.hitPoints = 100;
        player.damage = 6;
        player.armor = 1;

        return player;
    }
}
