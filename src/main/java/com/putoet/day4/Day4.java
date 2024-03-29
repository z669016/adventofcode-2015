package com.putoet.day4;

import com.putoet.security.MD5;
import com.putoet.utils.Timer;

public class Day4 {
    public static void main(String[] args) {
        final var input = "bgvyzdsv";

        Timer.run(() -> {
            long code = 0;
            while (!MD5.hash(input + code).startsWith("00000")) {
                code++;
            }
            System.out.println("Code " + code + " provides hash " + MD5.hash(input + code));
        });

        Timer.run(() -> {
            long code = 0;
            while (!MD5.hash(input + code).startsWith("000000")) {
                code++;
            }
            System.out.println("Code " + code + " provides hash " + MD5.hash(input + code));
        });
    }
}
