package com.putoet.day21;

import org.paukov.combinatorics3.Generator;

import java.util.*;

class Armory {
    private final static Armament[] ARMAMENTS = {
            new Armament(Armament.Type.WEAPON, "Dagger", 8, 4, 0),
            new Armament(Armament.Type.WEAPON, "Shortsword", 10, 5, 0),
            new Armament(Armament.Type.WEAPON, "Warhammer", 25, 6, 0),
            new Armament(Armament.Type.WEAPON, "Longsword", 40, 7, 0),
            new Armament(Armament.Type.WEAPON, "Greataxe", 74, 8, 0),
            new Armament(Armament.Type.ARMOR, "Leather", 13, 0, 1),
            new Armament(Armament.Type.ARMOR, "Chainmail", 31, 0, 2),
            new Armament(Armament.Type.ARMOR, "Splintmail", 53, 0, 3),
            new Armament(Armament.Type.ARMOR, "Bandedmail", 75, 0, 4),
            new Armament(Armament.Type.ARMOR, "Platemail", 102, 0, 5),
            new Armament(Armament.Type.RING, "Damage +1", 25, 1, 0),
            new Armament(Armament.Type.RING, "Damage +2", 50, 2, 0),
            new Armament(Armament.Type.RING, "Damage +3", 100, 3, 0),
            new Armament(Armament.Type.RING, "Defense +1", 20, 0, 1),
            new Armament(Armament.Type.RING, "Defense +2", 40, 0, 2),
            new Armament(Armament.Type.RING, "Defense +3", 80, 0, 3)
    };

    public Armament[] stock() {
        return ARMAMENTS;
    }

    public Armament[] stock(Armament.Type type) {
        return Arrays.stream(ARMAMENTS)
                .filter(armament -> armament.type() == type)
                .toArray(Armament[]::new);
    }

    public Armament[] stock(Set<Armament.Type> types) {
        return Arrays.stream(ARMAMENTS)
                .filter(armament -> types.contains(armament.type()))
                .toArray(Armament[]::new);
    }

    public List<ArmamentCombination> combinations() {
        final var armamentCombinations = new ArrayList<ArmamentCombination>();

        // Exactly one weapon
        for (var weapon : stock(Armament.Type.WEAPON)) {
            // Try without armor, and without rings
            armamentCombinations.add(new ArmamentCombination(weapon.cost(), List.of(weapon)));

            // Try with a single ring
            for (var ring : stock(Armament.Type.RING)) {
                armamentCombinations.add(new ArmamentCombination(weapon.cost() + ring.cost(), List.of(weapon, ring)));

                // And a single ring with a piece of armor
                for (var armor : stock(Armament.Type.ARMOR))
                    armamentCombinations.add(new ArmamentCombination(weapon.cost() + ring.cost() + armor.cost(),
                            List.of(weapon, ring, armor)));
            }

            // Try with two rings
            Generator.combination(stock(Armament.Type.RING)).simple(2).stream().forEach(rings -> {
                armamentCombinations.add(
                        new ArmamentCombination(weapon.cost() + rings.get(0).cost() + rings.get(1).cost(),
                                List.of(weapon, rings.get(0), rings.get(1)))
                );

                // And a double ring with a piece of armor
                for (var armor : stock(Armament.Type.ARMOR))
                    armamentCombinations.add(
                            new ArmamentCombination(weapon.cost() + rings.get(0).cost() + rings.get(1).cost() + armor.cost(),
                                    List.of(weapon, rings.get(0), rings.get(1), armor))
                    );
            });
        }

        return armamentCombinations;
    }
}
