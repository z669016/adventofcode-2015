package com.putoet.day6;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LightInstructionFactory<T> {
    private static final Pattern PATTERN = Pattern.compile("([a-z ]+)(\\d{1,3}),(\\d{1,3}) through (\\d{1,3}),(\\d{1,3})");
    public static final int MIN_X = 2;
    public static final int MAX_X = 4;
    public static final int MIN_Y = 3;
    public static final int MAX_Y = 5;

    private final Function<T,T> turnOn;
    private final Function<T,T> turnOff;
    private final Function<T,T> toggle;

    public LightInstructionFactory(Function<T, T> turnOn, Function<T, T> turnOff, Function<T, T> toggle) {
        this.turnOn = turnOn;
        this.turnOff = turnOff;
        this.toggle = toggle;
    }

    public LightInstruction<T> valueOf(String instruction) {
        final InstructionType type = InstructionType.of(instruction);
        final Matcher matcher = PATTERN.matcher(instruction);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction encoding '" + instruction + "'");

        final int minX = Integer.parseInt(matcher.group(MIN_X));
        final int maxX = Integer.parseInt(matcher.group(MAX_X));
        final int minY = Integer.parseInt(matcher.group(MIN_Y));
        final int maxY = Integer.parseInt(matcher.group(MAX_Y));

        if (maxX < minX || maxY < minY)
            throw new IllegalArgumentException("Invalid instruction coordinates '" + instruction + "'");

        switch (type) {
            case TOGGLE: return new LightInstruction<T>(minX, maxX, minY, maxY, toggle);
            case TURN_OFF: return new LightInstruction<T>(minX, maxX, minY, maxY, turnOff);
            case TURN_ON:
            default:
                return new LightInstruction<T>(minX, maxX, minY, maxY, turnOn);
        }
    }
}
