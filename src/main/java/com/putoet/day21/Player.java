package com.putoet.day21;

import java.util.ArrayList;
import java.util.List;

class Player {
    private final String name;
    private int hitPoints = 100;
    private int damage = -1;
    private int armor = -1;
    private final List<Armament> armaments = new ArrayList<>();

    public Player(String name) {
        assert name != null;

        this.name = name;
    }

    public void pick(Armament armament) {
        armaments.add(armament);
        damage = armor = -1;
    }

    public void pick(List<Armament> armament) {
        armaments.addAll(armament);
        damage = armor = -1;
    }

    public String name() { return name; }
    public List<Armament> armaments() { return armaments; }
    public int hitPoints() { return hitPoints; }

    public void attack(Player player) {
        final var damage = damage();
        player.defend(damage);
    }

    public int damage() {
        if (damage == -1)
            damage = armaments.stream().mapToInt(Armament::damage).sum();
        return damage;
    }

    public void defend(int damage) {
        final var defence = defence();
        final var impact = defence > damage ? 1 : damage - defence;

        hitPoints -= impact;
    }

    public int defence() {
        if (armor == -1)
            armor = armaments.stream().mapToInt(Armament::armor).sum();

        return armor;
    }

    public boolean defeated() {
        return hitPoints <= 0;
    }

    public static Player boss(List<String> descriptions) {
        final var player = boss();

        try {
            descriptions.forEach(description -> {
                final var words = description.split(":");
                if (words[0].startsWith("Hit Points")) player.hitPoints = Integer.parseInt(words[1].trim());
                else if (words[0].startsWith("Damage")) player.damage = Integer.parseInt(words[1].trim());
                else if (words[0].startsWith("Armor")) player.armor = Integer.parseInt(words[1].trim());
            });
        } catch (NumberFormatException exc) {
            throw new IllegalArgumentException("Invalid player description..." + descriptions, exc);
        }

        return player;
    }

    public static Player boss() {
        final Player player = new Player("Boss");
        player.hitPoints = 100;
        player.damage = 6;
        player.armor = 1;

        return player;
    }

    @Override
    public String toString() {
        return name + armaments;
    }
}
