package com.putoet.day23;

import java.util.List;
import java.util.Map;

class Processor implements Runnable {
    public static final String IP = "ip";
    public static final String A = "a";
    public static final String B = "b";

    private Map<String,Register> regs = Map.of(IP, new Register(IP), A, new Register(A), B, new Register(B));
    private List<Runnable> instr = List.of();
    private Register ip = regs.get((IP));
    private boolean verbose = false;

    @Override
    public void run() {
        while (ip.get() < instr.size()) {
            final var runnable = instr.get(ip.get());
            if (verbose)
                System.out.println(runnable);

            runnable.run();
        }
    }

    public void compile(List<String> program) {
        regs = Map.of(IP, new Register(IP), A, new Register(A), B, new Register(B));
        ip = regs.get(IP);

        instr =  program.stream()
                .map(this::compile)
                .toList();
    }

    private Runnable compile(String line) {
        final var words = line.split(" ");
        return switch (words[0]) {
            case "hlf" -> new HLF(ip, register(clean(words[1])));
            case "tpl" -> new TPL(ip, register(clean(words[1])));
            case "inc" -> new INC(ip, register(clean(words[1])));
            case "jmp" -> new JMP(ip, offset(clean(words[1])));
            case "jie" -> new JIE(ip, register(clean(words[1])), offset(clean(words[2])));
            case "jio" -> new JIO(ip, register(clean(words[1])), offset(clean(words[2])));
            default -> throw new IllegalArgumentException("Invalid instruction '" + line + "'");
        };
    }

    private int offset(String word) {
        return Integer.parseInt(word);
    }

    public Register register(String word) {
        if (!regs.containsKey(word))
            throw new IllegalArgumentException("Invalid register name '" + word + "'");

        return regs.get(word);
    }

    private static String clean(String word) {
        return word.strip().replace(",", "");
    }

    public void setVerbose(boolean value) {
        verbose = value;
    }

    public void dump() {
        System.out.println("REGISTERS");
        regs.values().forEach(System.out::println);

        System.out.println();
        System.out.println("PROGRAM");
        instr.forEach(System.out::println);
    }
}
