package com.putoet.day21;

import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private final static List<Armament> ARMAMENTS = List.of(
            new Armament(Armament.Type.WEAPON,"Dagger",8,4,0),
            new Armament(Armament.Type.WEAPON,"Shortsword", 10,5,0),
            new Armament(Armament.Type.WEAPON,"Warhammer", 25,6,0),
            new Armament(Armament.Type.WEAPON,"Longsword", 40,7,0),
            new Armament(Armament.Type.WEAPON,"Greataxe",74,8,0),
            new Armament(Armament.Type.ARMOR,"Leather", 13, 0, 1),
            new Armament(Armament.Type.ARMOR,"Chainmail", 31, 0, 2),
            new Armament(Armament.Type.ARMOR,"Splintmail", 53, 0, 3),
            new Armament(Armament.Type.ARMOR,"Bandedmail", 75, 0, 4),
            new Armament(Armament.Type.ARMOR,"Platemail", 102, 0, 5),
            new Armament(Armament.Type.RING,"Damage +1", 25, 1, 0),
            new Armament(Armament.Type.RING,"Damage +2", 50, 2, 0),
            new Armament(Armament.Type.RING,"Damage +3", 100, 3, 0),
            new Armament(Armament.Type.RING,"Defense +1", 20, 0, 1),
            new Armament(Armament.Type.RING,"Defense +2", 40, 0, 2),
            new Armament(Armament.Type.RING,"Defense +3", 80, 0, 3)
    );

    private final Map<String, Armament> stock;
    private int cash;

    public Store() {
        stock = ARMAMENTS.stream().collect(Collectors.toMap(Armament::name, armament -> armament));
    }

    public void sell(Player player, String armamentName) {
//        if (!stock.containsKey(armamentName))
//            throw new IllegalArgumentException(armamentName + " not in stock.");

        final Armament armament = stock.get(armamentName);
        player.pay(armament);

        cash += armament.cost();
//        stock.remove(armamentName);
    }

    public List<Armament> stock() { return new ArrayList<>(stock.values()); }
    public List<Armament> stock(Armament.Type type) {
        return stock.values().stream()
                .filter(armament -> armament.type() == type)
                .collect(Collectors.toList());
    }
    public List<Armament> stock(Set<Armament.Type> types) {
        return stock.values().stream()
                .filter(armament -> types.contains(armament.type()))
                .collect(Collectors.toList());
    }

    public int cash() { return cash; }
}
