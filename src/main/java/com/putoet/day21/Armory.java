package com.putoet.day21;

import utilities.Permutator;

import java.util.*;
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

    static class Combination {
        private final int costs;
        private final List<Armament> armaments;

        Combination(int costs, List<Armament> armaments) {
            this.costs = costs;
            this.armaments = armaments;
        }

        public int costs() { return costs; }
        public List<Armament> armaments() { return armaments; }

        @Override
        public String toString() {
            return "Combination{" +
                    "costs=" + costs +
                    ", armaments=" + armaments +
                    '}';
        }
    }

    public List<Combination> combinations() {
        final List<Combination> combinations = new ArrayList<>();

        // Exactly one weapon
        for (Armament weapon : stock(Armament.Type.WEAPON)) {
            // Try without armor, and without rings
            combinations.add(new Combination(weapon.cost(), List.of(weapon)));

            // Try with a single ring
            for (Armament ring : stock(Armament.Type.RING)) {
                combinations.add(new Combination(weapon.cost() + ring.cost(), List.of(weapon, ring)));

                // And a single ring with a piece of armor
                for (Armament armor : stock(Armament.Type.ARMOR))
                    combinations.add(new Combination(weapon.cost() + ring.cost() + armor.cost(),
                            List.of(weapon, ring, armor)));
            }

            // Try with two rings
            final Permutator<Armament> permutator = new Permutator<>();
            final List<List<Armament>> ringCombinations = permutator.combinations(stock(Armament.Type.RING));
            for (List<Armament> rings : ringCombinations) {
                combinations.add(new Combination(weapon.cost() + rings.get(0).cost() + rings.get(1).cost(),
                        List.of(weapon, rings.get(0), rings.get(1))));

                // And a double ring with a piece of armor
                for (Armament armor : stock(Armament.Type.ARMOR))
                    combinations.add(new Combination(weapon.cost() + rings.get(0).cost() + rings.get(1).cost() + armor.cost(),
                            List.of(weapon, rings.get(0), rings.get(1), armor)));
            }
        }

        return combinations;
    }
}
