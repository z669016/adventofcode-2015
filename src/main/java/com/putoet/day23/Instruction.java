package com.putoet.day23;

abstract class Instruction {
    private final String name;
    private final Register ip;

    protected Instruction(String name, Register ip) {
        assert name != null;
        assert ip != null;

        this.name = name;
        this.ip = ip;
    }

    protected void increment() {
        ip.accept(ip.get() + 1);
    }

    protected void jumpRelative(int offset) {
        ip.accept(ip.get() + offset);
    }

    protected void jumpAbsolute(int value) {
        assert value >= 0;

        ip.accept(value);
    }

    @Override
    public String toString() {
        return ip.get() + " " + name;
    }
}
