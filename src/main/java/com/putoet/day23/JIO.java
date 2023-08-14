package com.putoet.day23;

class JIO extends Instruction implements Runnable {
    private final int offset;
    private final Register r;

    public JIO(Register ip, Register r, int offset) {
        super("jio", ip);
        this.r = r;
        this.offset = offset;
    }

    @Override
    public void run() {
        if (r.get() == 1)
            jumpRelative(offset);
        else
            increment();
    }

    @Override
    public String toString() {
        return super.toString() + " " + r + ", " + offset;
    }
}
