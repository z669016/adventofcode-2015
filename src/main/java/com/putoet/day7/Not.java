package com.putoet.day7;

import static utilities.IntegerUtils.unsigned16Bit;

class Not extends Wire {
    private Integer value = null;

    public Not(String name, NamedSupplier from) {
        super(name, from);
    }

    @Override
    public Integer get() {
        if (value == null)
            value = unsigned16Bit(~ unsigned16Bit(super.get()));

        return value;
    }
}
