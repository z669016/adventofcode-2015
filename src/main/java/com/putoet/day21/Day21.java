package com.putoet.day21;

import utilities.Permutator;
import utilities.ResourceLines;

import java.util.*;

public class Day21 {
    public static void main(String[] args) {
        final List<String> descriptions = ResourceLines.list("/day21.txt");
        final Armory armory = new Armory();

        // Create a map of armament combinations to compare, use the costs as the key for the map
        final List<Armory.Combination> combinations = armory.combinations();

        // Get an ordered list of the keys, which means the first key that defeats the boss is the cheapest armament to win
        combinations.sort(Comparator.comparing(Armory.Combination::costs));
        combinations.forEach(System.out::println);

        // Now, battle with the combinations until you have the player that wins (i.e. not the boss)
        for (Armory.Combination combi : combinations) {
            final Player you = new Player("You");
            you.pick(combi.armaments());

            final Player boss = Player.boss(descriptions);

            final Player winner = battle(you, boss);
            if (winner.name().equals(you.name())) {
                System.out.println("You won: " + you);
                System.out.println("Costs have been " + combi.costs());
                break;
            }
        }
    }

    private static Player battle(Player you, Player boss) {
        System.out.println("Battle between " + you + " and " + boss);
        boolean defeated = false;
        Player winner = null;
        while (!defeated) {
            you.attack(boss);
            defeated = boss.defeated();
            if (defeated) {
                winner = you;
            }
            else {
                boss.attack(you);

                defeated = you.defeated();
                if (defeated)
                    winner = boss;
            }
        }

        System.out.println("Won by " + winner);
        return winner;
    }
}
