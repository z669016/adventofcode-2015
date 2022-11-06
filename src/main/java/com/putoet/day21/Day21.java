package com.putoet.day21;

import com.putoet.resources.ResourceLines;

import java.util.*;

public class Day21 {
    public static void main(String[] args) {
        final List<String> descriptions = ResourceLines.list("/day21.txt");
        final Armory armory = new Armory();

        List<Armory.ArmamentCombination> armamentCombinations = armory.combinations();
        armamentCombinations.sort(Comparator.comparing(Armory.ArmamentCombination::costs));
        tryWinningCombinations(descriptions, armamentCombinations);

        armamentCombinations = armory.combinations();
        armamentCombinations.sort(Comparator.comparing(Armory.ArmamentCombination::costs).reversed());
        tryLoosingCombinations(descriptions, armamentCombinations);
    }

    protected static void tryWinningCombinations(List<String> descriptions, List<Armory.ArmamentCombination> armamentCombinations) {
        // Now, battle with the combinations until you have the player that wins (i.e. not the boss)
        for (Armory.ArmamentCombination combi : armamentCombinations) {
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

    protected static void tryLoosingCombinations(List<String> descriptions, List<Armory.ArmamentCombination> armamentCombinations) {
        // Now, battle with the combinations until you have the player that wins (i.e. not the boss)
        for (Armory.ArmamentCombination combi : armamentCombinations) {
            final Player you = new Player("You");
            you.pick(combi.armaments());

            final Player boss = Player.boss(descriptions);

            final Player winner = battle(you, boss);
            if (winner.name().equals(boss.name())) {
                System.out.println("Boss won: " + you);
                System.out.println("Costs have been " + combi.costs());
                break;
            }
        }
    }

    private static Player battle(Player you, Player boss) {
        // System.out.println("Battle between " + you + " and " + boss);
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

        // System.out.println("Won by " + winner);
        return winner;
    }
}
