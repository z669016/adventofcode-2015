package com.putoet.day21;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

import java.util.*;

public class Day21 {
    public static void main(String[] args) {
        final var descriptions = ResourceLines.list("/day21.txt");
        final var armory = new Armory();
        final var armamentCombinations = armory.combinations();
        armamentCombinations.sort(Comparator.comparing(ArmamentCombination::costs));

        Timer.run(() -> tryWinningCombinations(descriptions, armamentCombinations));

        armamentCombinations.sort(Comparator.comparing(ArmamentCombination::costs).reversed());
        Timer.run(() -> tryLoosingCombinations(descriptions, armamentCombinations));
    }

    static void tryWinningCombinations(List<String> descriptions, List<ArmamentCombination> armamentCombinations) {
        // Now, battle with the combinations until you have the combination that wins (i.e. not the boss)
        for (var combination : armamentCombinations) {
            final var you = new Player("You");
            you.pick(combination.armaments());

            final var boss = Player.boss(descriptions);
            final var winner = battle(you, boss);
            if (winner.name().equals(you.name())) {
                System.out.println("You won: " + you);
                System.out.println("Costs have been " + combination.costs());
                break;
            }
        }
    }

    static void tryLoosingCombinations(List<String> descriptions, List<ArmamentCombination> armamentCombinations) {
        // Now, battle with the combinations until you have the player that wins (i.e. not the boss)
        for (var  combination : armamentCombinations) {
            final var you = new Player("You");
            you.pick(combination.armaments());

            final var boss = Player.boss(descriptions);
            final var winner = battle(you, boss);
            if (winner.name().equals(boss.name())) {
                System.out.println("Boss won: " + you);
                System.out.println("Costs have been " + combination.costs());
                break;
            }
        }
    }

    private static Player battle(Player you, Player boss) {
        // System.out.println("Battle between " + you + " and " + boss);
        var defeated = false;
        Player winner = null;
        while (!defeated) {
            you.attack(boss);
            defeated = boss.defeated();
            if (defeated) {
                winner = you;
            } else {
                boss.attack(you);
                defeated = you.defeated();
                if (defeated)
                    winner = boss;
            }
        }

        return winner;
    }
}
