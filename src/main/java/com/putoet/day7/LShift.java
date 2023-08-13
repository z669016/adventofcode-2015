package com.putoet.day7;

import static utilities.IntegerUtils.unsigned16Bit;

class LShift implements NamedSupplier {
    private final String name;
    private final NamedSupplier left;
    private final NamedSupplier right;
    private Integer value = null;

    public LShift(String name, NamedSupplier left, NamedSupplier right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Integer get() {
        if(value == null)
            value = unsigned16Bit(unsigned16Bit(left.get()) << unsigned16Bit(right.get()));

        return value;
    }
}
