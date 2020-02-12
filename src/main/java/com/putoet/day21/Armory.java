package com.putoet.day21;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Armory {
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

    public List<Armament> stock() { return Collections.unmodifiableList(ARMAMENTS); }
    public List<Armament> stock(Armament.Type type) {
        return ARMAMENTS.stream()
                .filter(armament -> armament.type() == type)
                .collect(Collectors.toList());
    }
    public List<Armament> stock(Set<Armament.Type> types) {
        return ARMAMENTS.stream()
                .filter(armament -> types.contains(armament.type()))
                .collect(Collectors.toList());
    }
}
