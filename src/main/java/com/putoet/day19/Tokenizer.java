package com.putoet.day19;

import java.util.Iterator;

public class Tokenizer implements Iterator<String> {
    private final String line;
    private int ptr = 0;
    private int tokenCount = 0;
    private int parenthesisCount = 0;
    private int commaCount = 0;

    public Tokenizer(String line) {
        this.line = line;
    }

    @Override
    public boolean hasNext() {
        return ptr < line.length();
    }

    @Override
    public String next() {
        final var len = tokenLength();
        final var next = line.substring(ptr, ptr + len);
        ptr += len;

        return token(next);
    }

    private int tokenLength() {
        if (ptr == line.length() - 1)
            return 1;

        return Character.isUpperCase(line.charAt(ptr)) && Character.isUpperCase(line.charAt(ptr + 1)) ? 1 : 2;
    }

    private String token(String next) {
        tokenCount++;
        return switch (next) {
            case "Rn" -> {
                parenthesisCount++;
                yield "(";
            }
            case "Ar" -> {
                parenthesisCount++;
                yield ")";
            }
            case "Y" -> {
                commaCount++;
                yield ",";
            }
            default -> next;
        };
    }

    public int parenthesesCount() { return parenthesisCount; }
    public int commaCount() { return commaCount; }
    public int tokenCount() { return tokenCount; }
}
