package com.putoet.day23;

public class INC extends Instruction implements Runnable {
    private final Register r;

    public INC(Register ip, Register r) {
        super("inc", ip);
        this.r = r;
    }

    @Override
    public void run() {
        r.accept(r.get() + 1);
        increment();
    }

    @Override
    public String toString() {
        return super.toString() + " " + r;
    }
}
