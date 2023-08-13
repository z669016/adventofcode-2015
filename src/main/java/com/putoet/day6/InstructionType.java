package com.putoet.day6;

enum InstructionType {
    TURN_ON,
    TURN_OFF,
    TOGGLE;

    public static InstructionType of(String text) {
        assert text != null;

        text = text.toLowerCase();
        if (text.startsWith("toggle ")) return TOGGLE;
        if (text.startsWith("turn on ")) return TURN_ON;
        if (text.startsWith("turn off ")) return TURN_OFF;

        throw new IllegalArgumentException("Invalid instruction encoding '" + text + "'");
    }
}
