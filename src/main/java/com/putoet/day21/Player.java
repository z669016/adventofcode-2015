package com.putoet.day21;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private final String name;
    private int gold;
    private int hitPoints = 100;
    private int damage = -1;
    private int armor = -1;
    private final List<Armament> armaments = new ArrayList<>();

    public Player(String name, int gold) {
        assert name != null;

        this.name = name;
        this.gold = gold;
    }

    public void pay(Armament armament) {
        if (armament.cost() > gold)
            throw new IllegalArgumentException("Cannot afford " + armament.name() + " for " + armament.cost() + " gold. I only have " + gold + " gold.");

        gold -= armament.cost();
        armaments.add(armament);
        damage = armor = -1;
    }

    public String name() { return name; }
    public int gold() { return gold; }
    public List<Armament> armaments() { return armaments; }
    public int hitPoints() { return hitPoints; }

    public void attack(Player player) {
        final int damage = damage();
        // System.out.println(name() + " is attacking " + player.name() + " with damage " + damage);
        player.defend(damage);
    }

    public int damage() {
        if (damage == -1)
            damage = armaments.stream().mapToInt(Armament::damage).sum();
        return damage;
    }

    public void defend(int damage) {
        final int defence = defence();
        final int impact = defence > damage ? 1 : damage - defence;

        // System.out.println(name + " is defending an attack with damage " + damage + " with armor " + defence + ". Impact is " + impact);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return gold == player.gold &&
                hitPoints == player.hitPoints &&
                damage == player.damage &&
                armor == player.armor &&
                name.equals(player.name) &&
                armaments.equals(player.armaments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gold, hitPoints, damage, armor, armaments);
    }

    public static Player boss(List<String> desscriptions) {
        final Player player = boss();

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

    public static Player boss() {
        final Player player = new Player("Boss", 0);
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
