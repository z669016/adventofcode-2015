package com.putoet.day23;

class JMP extends Instruction implements Runnable {
    private final int offset;

    public JMP(Register ip, int offset) {
        super("jmp", ip);

        assert offset != 0;
        this.offset = offset;
    }

    @Override
    public void run() {
        jumpRelative(offset);
    }


    @Override
    public String toString() {
        return super.toString() + " " + offset;
    }
}
