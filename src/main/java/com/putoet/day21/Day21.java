package com.putoet.day21;

import utilities.Permutator;
import utilities.ResourceLines;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Day21 {
    public static void main(String[] args) {
        final List<String> descriptions = ResourceLines.list("/day21.txt");

        final List<Player> winners = new ArrayList<>();
        battleNoWeapons(winners, descriptions);
        battleSingleWeapon(winners, descriptions);
        battleSingleWeaponAndOneRing(winners, descriptions);
        battleSingleWeaponAndTwoRings(winners, descriptions);

        System.out.println(winners);
    }

    private static void battleSingleWeaponAndTwoRings(List<Player> winners, List<String> descriptions) {
        final Store store = new Store();
        final List<Armament> ringList = store.stock(Armament.Type.RING);
        final Permutator<Armament> permutator = new Permutator<>();
        final List<List<Armament>> ringCombinations = permutator.combinations(ringList);

        for (Armament weapon : store.stock(Set.of(Armament.Type.WEAPON, Armament.Type.ARMOR))) {
            for (List<Armament> rings : ringCombinations) {
                Player boss = Player.boss(descriptions);
                Player you = new Player("You", 500);
                store.sell(you, weapon.name());
                store.sell(you, rings.get(0).name());
                store.sell(you, rings.get(1).name());

                winners.add(battle(you, boss));
            }
        }
    }

    private static void battleSingleWeaponAndOneRing(List<Player> winners, List<String> descriptions) {
        final Store store = new Store();

        for (Armament weapon : store.stock(Set.of(Armament.Type.WEAPON, Armament.Type.ARMOR))) {
            for (Armament ring : store.stock(Armament.Type.RING)) {
                Player boss = Player.boss(descriptions);
                Player you = new Player("You", 500);
                store.sell(you, weapon.name());
                store.sell(you, ring.name());

                winners.add(battle(you, boss));
            }
        }
    }

    private static void battleSingleWeapon(List<Player> winners, List<String> descriptions) {
        final Store store = new Store();
        for (Armament weapon : store.stock()) {
            Player boss = Player.boss(descriptions);
            Player you = new Player("You", 500);
            store.sell(you, weapon.name());
            if (isPossibleCheaper(winners, you))
                winners.add(battle(you, boss));
        }
    }

    private static boolean isPossibleCheaper(List<Player> winners, Player you) {
        int price = you.armaments().stream().mapToInt(Armament::cost).sum();
        //int
        return false;
    }

    private static void battleNoWeapons(List<Player> winners, List<String> descriptions) {
        Player boss = Player.boss(descriptions);
        Player you = new Player("You", 500);
        winners.add(battle(you, boss));
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
