package com.putoet.day4;

import com.putoet.security.MD5;

import java.security.NoSuchAlgorithmException;

public class Day4 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        final String input = "bgvyzdsv";
        long code = 0;
        while (!MD5.hash(input + code).startsWith("00000")) {
            System.out.print("\rCode: " + code);
            code++;
        }
        System.out.println();
        System.out.println("Code " + code + " provides hash " + MD5.hash(input + code));

        code = 0;
        while (!MD5.hash(input + code).startsWith("000000")) {
            System.out.print("\rCode: " + code);
            code++;
        }
        System.out.println();
        System.out.println("Code " + code + " provides hash " + MD5.hash(input + code));
    }
}
