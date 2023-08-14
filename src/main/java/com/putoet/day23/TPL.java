package com.putoet.day23;

class TPL extends Instruction implements Runnable {
    private final Register r;

    public TPL(Register ip, Register r) {
        super("tpl", ip);
        this.r = r;
    }

    @Override
    public void run() {
        r.accept(r.get() * 3);
        increment();
    }

    @Override
    public String toString() {
        return super.toString() + " " + r;
    }
}
