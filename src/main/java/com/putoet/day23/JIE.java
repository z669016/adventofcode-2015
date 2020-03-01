package com.putoet.day23;

public class JIE extends Instruction implements Runnable {
    private final int offset;
    private final Register r;

    public JIE(Register ip, Register r, int offset) {
        super("jie", ip);
        this.r = r;
        this.offset = offset;
    }

    @Override
    public void run() {
        if (r.get() % 2 == 0)
            jumpRelative(offset);
        else
            increment();
    }

    @Override
    public String toString() {
        return super.toString() + " " + r + ", " + offset;
    }
}
