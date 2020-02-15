package com.putoet.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArmedPlayer extends BasePlayer {
    private final List<Armament> armaments = new ArrayList<>();

    public ArmedPlayer(String name) {
        super(name);
    }

    public void pick(Armament armament) {
        armaments.add(armament);
    }

    public void pick(List<Armament> armament) {
        armaments.addAll(armament);
    }

    @Override
    public void attack(Player player) {
        final int damage = damage();
        player.defend(Armament.Type.WEAPON, damage);
    }

    public List<Armament> armaments() { return armaments; }

    public int damage() {
        return armaments.stream().mapToInt(Armament::damage).sum();
    }

    public int armor() {
        return armaments.stream().mapToInt(Armament::armor).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArmedPlayer)) return false;
        if (!super.equals(o)) return false;
        ArmedPlayer that = (ArmedPlayer) o;
        return armaments.equals(that.armaments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), armaments);
    }

    @Override
    public String toString() {
        return "ArmedPlayer{" +
                "armaments=" + armaments +
                ", name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                '}';
    }
}
