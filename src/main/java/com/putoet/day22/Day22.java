package com.putoet.day22;

import java.util.List;

public class Day22 {
    public static void main(String[] args) {
        final Wizard wizard = new Wizard(50, 500, List.of());
        final Boss boss = new Boss(71,10);
        final Combat combat = new Combat(wizard, boss);

        final Combattant winner = combat.start();

        System.out.println("The winner is " + winner);
    }
}
