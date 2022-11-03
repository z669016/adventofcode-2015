package com.putoet.day11;

import java.util.Arrays;

public class Password {
    final private char[] password;

    public Password(String password) {
        assert password != null && password.length() == 8;
        this.password = password.toCharArray();
    }

    public Password next() {
        char[] newPassword = Arrays.copyOf(password, 8);
        while (hasIncrement(newPassword)) {
            newPassword = increment(newPassword);

            if (isValid(newPassword))
                return new Password(String.valueOf(newPassword));
        }

        throw new IllegalStateException("Cannot generate next valid password for '" + this + "'");
    }

    private static final char[] MAX_PASSWORD = new char[] {'z', 'z', 'z', 'z', 'z', 'z', 'z', 'z'};
    private static boolean hasIncrement(char[] password) {
        return !Arrays.equals(password, MAX_PASSWORD);
    }

    private static char[] increment(char[] password) {
        char[] increment = Arrays.copyOf(password, 8);
        int idx = 7;
        increment[idx] = increment(increment[idx]);
        while((idx > 0) && (increment[idx] > 'z')) {
            increment[idx] = 'a';
            idx--;

            increment[idx] = increment(increment[idx]);
        }
        return increment;
    }

    private static char increment(char c) {
        return (char) (c + 1);
    }

    public static boolean isValid(String password) {
        if (password == null || password.length() != 8)
            throw new IllegalArgumentException("Invalid password '" + password + "'");

        return isValid(password.toCharArray());
    }

    private static boolean isValid(char[] password) {
        return isLengthEight(password)
                && isLowerCase(password)
                && includesStraight(password)
                && onlyContainsValidCharacters(password)
                && hasTwoNonOverlappingPairs(password);
    }

    private static boolean isLengthEight(char[] password) {
        return password != null && password.length == 8;
    }

    private static boolean isLowerCase(char[] password) {
        for (int idx = 0; idx < 8; idx++)
            if (Character.isUpperCase(password[idx])) return false;

        return true;
    }

    private static boolean includesStraight(char[] password) {
        for (int idx = 0; idx < 5; idx++) {
            if ((password[idx] + 1 == password[idx+1]) && (password[idx+1] + 1 == password[idx+2]))
                return true;
        }

        return false;
    }

    private static boolean onlyContainsValidCharacters(char[] password) {
        for (int idx = 0; idx < 8; idx++) {
            if (password[idx] == 'i' || password[idx] == 'o' ||password[idx] == 'l') return false;
        }
        return true;
    }

    private static boolean hasTwoNonOverlappingPairs(char[] password) {
        boolean doubleFound = false;

        for (int idx = 0; idx < password.length - 1; idx++) {
            if (password[idx] == password[idx+1]) {
                if (!doubleFound) {
                    doubleFound = true;
                    idx++; // skip one character to ensure the doubles don't overlap
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(password);
    }

}
