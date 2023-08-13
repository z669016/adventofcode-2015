package com.putoet.day7;

class Constant extends Wire {
    public Constant(String name) {
        super(name, () -> Integer.parseInt(name));
    }
}
