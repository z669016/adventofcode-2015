package com.putoet.day7;

import java.util.function.Supplier;

public class Wire implements NamedSupplier {
    private final String name;
    private Supplier<Integer> from = null;
    private Integer value = null;

    public Wire(String name, Supplier<Integer> from) {
        assert name != null;

        if (from == null)
            throw new IllegalArgumentException("No supplier provided for " + name);

        this.name = name.toLowerCase();
        this.from = from;
    }

    @Override
    public String name() { return name; }

    @Override
    public Integer get() {
        if (value == null)
            this.value = from.get();

        return value;
    }
}
