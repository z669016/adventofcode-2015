package com.putoet.day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

class Circuit {
    public static final String ASSIGNMENT_PATTERN = "[a-z0-9]+";
    public static final String NOT_PATTERN = "NOT [a-z0-9]+";
    public static final String LSHIFT_PATTERN = "[a-z0-9]+ LSHIFT [a-z0-9]+";
    public static final String RSHIFT_PATTERN = "[a-z0-9]+ RSHIFT [a-z0-9]+";
    public static final String AND_PATTERN = "[a-z0-9]+ AND [a-z0-9]+";
    public static final String OR_PATTERN = "[a-z0-9]+ OR [a-z0-9]+";
    public static final String CONSTANT_PATTERN = "[0-9]+";

    private final Map<String,String> encodings = new HashMap<>();
    private final Map<String, NamedSupplier> namedSuppliers = new HashMap<>();

    public static Circuit from(final List<String> wiring) {
        final var circuit = new Circuit();
        wiring.stream().map(line -> line.split(" -> ")).forEach(s -> circuit.encodings.put(s[1], s[0]));
        return circuit;
    }

    public Optional<String> encoding(String name) {
        return Optional.ofNullable(encodings.get(name));
    }

    public void replaceEncoding(String name, String encoding) {
        if (!encodings.containsKey(name))
            throw new IllegalArgumentException("Encoding '" + name + "' doesn't exist, so cannot be replaced with '" + encoding + "'");

        reset();
        encodings.put(name, encoding);
    }

    public int encodingsCount() {
        return encodings.size();
    }

    public Integer get(String name) {
        return wiring(name).get();
    }

    public void reset() {
        namedSuppliers.clear();
    }

    private NamedSupplier wiring(String name) {
        resolve(name);
        return namedSuppliers.get(name);
    }

    private void resolve(String name) {
        if (!namedSuppliers.containsKey(name)) {
            if (name.matches(CONSTANT_PATTERN)) {
                final NamedSupplier supplier = new Constant(name);
                namedSuppliers.put(name, supplier);
            } else {
                final String code = encodings.get(name);
                if (code == null) {
                    throw new IllegalStateException("Cannot resolve encoding '" + name + "'");
                }

                final NamedSupplier supplier = createWiringFor(code);
                namedSuppliers.put(name, new Wire(name, supplier));
            }
        }
    }

    private NamedSupplier createWiringFor(String code) {
        if (code.matches(ASSIGNMENT_PATTERN)) return forOperand(code, s -> new Wire(code, wiring(s[0])));
        if (code.matches(NOT_PATTERN)) return forOperand(code, s -> new Not(code, wiring(s[1])));
        if (code.matches(LSHIFT_PATTERN)) return forOperand(code, s -> new LShift(code, wiring(s[0]), wiring(s[2])));
        if (code.matches(RSHIFT_PATTERN)) return forOperand(code, s -> new RShift(code, wiring(s[0]), wiring(s[2])));
        if (code.matches(AND_PATTERN)) return forOperand(code, s -> new And(code, wiring(s[0]), wiring(s[2])));
        if (code.matches(OR_PATTERN)) return forOperand(code, s -> new Or(code, wiring(s[0]), wiring(s[2])));

        throw new IllegalArgumentException("Invalid coding '" + code + "'");
    }

    public NamedSupplier forOperand(String code, Function<String[],NamedSupplier> function) {
        final var operands = code.split(" ");
        return function.apply(operands);
    }

    @Override
    public String toString() {
        final var sb = new StringBuffer();
        encodings.forEach((key, value) -> sb.append(value).append(" -> ").append(key).append("\n"));
        return sb.toString();
    }
}
