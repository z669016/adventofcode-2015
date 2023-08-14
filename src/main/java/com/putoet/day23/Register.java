package com.putoet.day23;

import java.util.function.Consumer;
import java.util.function.Supplier;

class Register implements Supplier<Integer>, Consumer<Integer> {
    private final String name;
    private Integer value = 0;

    public Register(String name) {
        this.name = name;
    }

    @Override
    public void accept(Integer value) {
        this.value = Math.abs(value);
    }

    @Override
    public Integer get() {
        return value;
    }

    @Override
    public String toString() {
        return name + "(=" + value + ")";
    }
}
