package com.putoet.day11;

public class Day11 {
    public static void main(String[] args) {
        final Password password = new Password("vzbxkghb");
        final Password nextPassword = password.next();
        System.out.println("Next password after " + password + " is '" + nextPassword + "'");
        System.out.println("Next password after " + nextPassword + " is '" + nextPassword.next() + "'");
    }
}
