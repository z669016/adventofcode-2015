package com.putoet.day19;

import java.util.Iterator;

public class Tokenizer implements Iterator<String> {
    private final String line;
    private int ptr = 0;
    private int tokenCount = 0;
    private int parentesesCount = 0;
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
        int len = tokenLength();
        final String next = line.substring(ptr, ptr + len);
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
        switch(next) {
            case "Rn": parentesesCount++; return "(";
            case "Ar": parentesesCount++; return ")";
            case "Y": commaCount++; return ",";
            default: return next;
        }
    }

    public int parenthesesCount() { return parentesesCount; }
    public int commaCount() { return commaCount; }
    public int tokenCount() { return tokenCount; }
}
