package com.putoet.day11;

import com.putoet.Timer;

public class Day11 {
    public static void main(String[] args) {
        final Password password = new Password("vzbxkghb");

        final Password nextPassword = Timer.run(() -> {
            final var next = password.next();
            System.out.println("Next password after " + password + " is '" + next + "'");
            return next;
        });

        Timer.run(() -> {
            System.out.println("Next password after " + nextPassword + " is '" + nextPassword.next() + "'");
            return null;
        });
    }
}
