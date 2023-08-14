package com.putoet.day23;

class HLF extends Instruction implements Runnable {
    private final Register r;

    public HLF(Register ip, Register r) {
        super("hlf", ip);
        this.r = r;
    }

    @Override
    public void run() {
        r.accept(r.get() / 2);
        increment();
    }

    @Override
    public String toString() {
        return super.toString() + " " + r;
    }
}
