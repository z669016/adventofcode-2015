package com.putoet.day6;

import java.util.function.Function;
import java.util.regex.Pattern;

class LightInstructionFactory<T> {
    private static final Pattern PATTERN = Pattern.compile("([a-z ]+)(\\d{1,3}),(\\d{1,3}) through (\\d{1,3}),(\\d{1,3})");
    public static final int MIN_X = 2;
    public static final int MAX_X = 4;
    public static final int MIN_Y = 3;
    public static final int MAX_Y = 5;

    private final Function<T,T> turnOn;
    private final Function<T,T> turnOff;
    private final Function<T,T> toggle;

    LightInstructionFactory(Function<T, T> turnOn, Function<T, T> turnOff, Function<T, T> toggle) {
        this.turnOn = turnOn;
        this.turnOff = turnOff;
        this.toggle = toggle;
    }

    LightInstruction<T> of(String instruction) {
        final var type = InstructionType.of(instruction);
        final var matcher = PATTERN.matcher(instruction);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction encoding '" + instruction + "'");

        final var minX = Integer.parseInt(matcher.group(MIN_X));
        final var maxX = Integer.parseInt(matcher.group(MAX_X));
        final var minY = Integer.parseInt(matcher.group(MIN_Y));
        final var maxY = Integer.parseInt(matcher.group(MAX_Y));

        if (maxX < minX || maxY < minY)
            throw new IllegalArgumentException("Invalid instruction coordinates '" + instruction + "'");

        return switch (type) {
            case TOGGLE -> new LightInstruction<>(minX, maxX, minY, maxY, toggle);
            case TURN_OFF -> new LightInstruction<>(minX, maxX, minY, maxY, turnOff);
            case TURN_ON -> new LightInstruction<>(minX, maxX, minY, maxY, turnOn);
        };
    }
}
